package com.minnymin.zephyr.user;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;

import com.minnymin.zephyr.Zephyr;
import com.minnymin.zephyr.spell.ContinuousSpell;
import com.minnymin.zephyr.spell.SpellContext;

public abstract class User {

	private ContinuousSpell currentlyCasting;
	private SpellContext currentlyCastingContext;
	
	private int mana;
	private Map<UserState, Integer> stateMap;

	private int tickTime = 0;

	private UserData userData;

	protected User(UserData data) {
		this.userData = data;
		this.mana = data.getMaximumMana();
		this.stateMap = new HashMap<UserState, Integer>();
	}
	
	/**
	 * Gets the player object from the implementation
	 */
	public abstract <T> T getPlayerObject();
	
	/**
	 * Gets the UUID of the user
	 */
	public abstract UUID getUUID();
	
	/**
	 * Sends a message to the user
	 */
	public abstract void sendMessage(String... message);
	
	/**
	 * Adds level progress and levels up the player if required
	 */
	public int addLevelProgress(int progress) {
		int currentProgress = this.getUserData().addLevelProgress(progress);
		int requiredProgress = getRequiredLevelProgress();
		if (currentProgress >= requiredProgress) {
			currentProgress -= requiredProgress;
			this.getUserData().setLevel(this.getUserData().getLevel()+1);
			this.getUserData().setLevelProgress(currentProgress);
			sendMessage("You have levelled up to level " + this.getUserData().getLevel());
		}
		return currentProgress;
	}
	
	/**
	 * Adds a state to the user's state map
	 */
	public void addState(UserState userState, int duration) {
		if (!this.stateMap.containsKey(userState)) {
			userState.onApplied(this);
		}
		this.stateMap.put(userState, duration);
	}

	/**
	 * Drains mana from user's mana. Only accepts positive values
	 */
	public void drainMana(int toDrain) {
		if (mana < 0) {
			return;
		}
		setMana(getMana() - toDrain);
	}
	
	/**
	 * Gets the user's mana
	 */
	public int getMana() {
		return this.mana;
	}
	
	/**
	 * Gets user's maximum mana
	 */
	public int getMaximumMana() {
		return this.userData.getMaximumMana();
	}

	/**
	 * Gets the progress required for the user to level up
	 */
	public int getRequiredLevelProgress() {
		return this.getUserData().getLevel() ^ 2 * 10 + 100;
	}

	/**
	 * Get user's UserData
	 */
	public UserData getUserData() {
		return this.userData;
	}


	/**
	 * Checks if user has more than specified drain amount
	 * 
	 * @return True if user has enough mana, false otherwise
	 */
	public boolean hasMana(int toDrain) {
		return getMana() - toDrain >= 0;
	}

	/**
	 * Checks if user is currently casting a spell
	 */
	public boolean isCasting() {
		return this.currentlyCasting != null;
	}
	
	/**
	 * Sets the spell the user should currently cast
	 */
	public void setCasting(ContinuousSpell spell, SpellContext context) {
		this.currentlyCasting = spell;
		this.currentlyCastingContext = context;
	}

	/**
	 * Sets the user's mana
	 */
	public void setMana(int mana) {
		if (mana > getUserData().getMaximumMana()) {
			this.mana = getUserData().getMaximumMana();
		} else {
			this.mana = mana;
		}
	}
	
	public void tick() {
		tickTime++;
		
		if (isCasting() && tickTime%currentlyCasting.getTickTime()==0) {
			Zephyr.getSpellManager().castContinuous(currentlyCasting, currentlyCastingContext);
		}
		
		for (Entry<UserState, Integer> entry : this.stateMap.entrySet()) {
			if (entry.getKey().getTickTime() != 0 && tickTime%entry.getKey().getTickTime()==0) {
				entry.getKey().tick(this);
			}
			if (tickTime%20==0) {
				if (entry.getValue() - 1 != 0) {
					this.stateMap.put(entry.getKey(), entry.getValue() - 1);
				} else {
					entry.getKey().onRemoved(this);
					this.stateMap.remove(entry.getKey());
				}
			}
		}
				
		if (tickTime%20==0) {
			tickTime = 0;
			if (getMana() < getUserData().getMaximumMana()) {
				setMana(getMana() + getUserData().getManaRegeneration());
			}
		}
	}

}
