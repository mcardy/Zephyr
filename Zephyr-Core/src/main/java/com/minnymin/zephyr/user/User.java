package com.minnymin.zephyr.user;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;

import com.minnymin.zephyr.Zephyr;
import com.minnymin.zephyr.spell.ContinuousSpell;
import com.minnymin.zephyr.spell.SpellContext;

public abstract class User {

	private UserData userData;
	private Map<UserState, Integer> stateMap;
	
	private ContinuousSpell currentlyCasting;
	private SpellContext currentlyCastingContext;

	private int mana;

	protected User(UserData data) {
		this.userData = data;
		this.mana = data.getMana();
		this.stateMap = new HashMap<UserState, Integer>();
	}

	public abstract UUID getUUID();

	public abstract <T> T getPlayerObject();

	public abstract void sendMessage(String... message);

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
	 * Checks if user has more than specified drain amount
	 * 
	 * @return True if user has enough mana, false otherwise
	 */
	public boolean hasMana(int toDrain) {
		return getMana() - toDrain >= 0;
	}

	/**
	 * Sets the user's mana
	 */
	public void setMana(int mana) {
		if (mana > getUserData().getMana()) {
			this.mana = getUserData().getMana();
		} else {
			this.mana = mana;
		}
	}

	/**
	 * Gets user's maximum mana
	 */
	public int getMaximumMana() {
		return this.userData.getMana();
	}

	/**
	 * Get user's UserData
	 */
	public UserData getUserData() {
		return this.userData;
	}
	
	public boolean isCasting() {
		return this.currentlyCasting != null;
	}
	
	public void setCasting(ContinuousSpell spell, SpellContext context) {
		this.currentlyCasting = spell;
		this.currentlyCastingContext = context;
	}

	private int tickTime = 0;
	
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
			if (getMana() < getUserData().getMana()) {
				setMana(getMana() + getUserData().getManaRegeneration());
			}
		}
	}

}
