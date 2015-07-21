package com.minnymin.zephyr.common.user;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.minnymin.zephyr.api.Zephyr;
import com.minnymin.zephyr.api.spell.ContinuousSpell;
import com.minnymin.zephyr.api.spell.Spell;
import com.minnymin.zephyr.api.spell.SpellContext;
import com.minnymin.zephyr.api.user.User;
import com.minnymin.zephyr.api.user.UserData;
import com.minnymin.zephyr.api.user.UserState;

public abstract class AbstractUser implements User {

	private ContinuousSpell currentlyCasting;
	private SpellContext currentlyCastingContext;
	
	private int mana;
	private Map<UserState, Integer> stateMap;

	private int tickTime = 0;

	private UserData userData;

	protected AbstractUser(UserData data) {
		this.userData = data;
		this.mana = data.getMaximumMana();
		this.stateMap = new HashMap<UserState, Integer>();
	}
	
	/* (non-Javadoc)
	 * @see com.minnymin.zephyr.api.user.User#addLevelProgress(int)
	 */
	@Override
	public int addLevelProgress(int progress) {
		int currentProgress = this.getUserData().addLevelProgress(progress);
		int requiredProgress = getRequiredLevelProgress();
		if (currentProgress >= requiredProgress) {
			currentProgress -= requiredProgress;
			this.getUserData().setLevel(this.getUserData().getLevel()+1);
			this.getUserData().setLevelProgress(currentProgress);
			sendMessage("You have levelled up to level " + this.getUserData().getLevel());
			// Spells on level up
			StringBuilder spellString = new StringBuilder();
			for (Spell spell : Zephyr.getSpellManager().getSpellsForLevel(this.userData.getLevel())) {
				this.getUserData().teachSpell(spell);
				if (spellString.length() == 0) {
					spellString.append(spell.getName());
				} else {
					spellString.append(", " + spell.getName());
				}
			}
			if (spellString.length() != 0) {
				sendMessage("You have learned: " + spellString.toString());
			}
		}
		return currentProgress;
	}
	
	/* (non-Javadoc)
	 * @see com.minnymin.zephyr.api.user.User#addState(com.minnymin.zephyr.api.user.UserState, int)
	 */
	@Override
	public void addState(UserState userState, int duration) {
		if (!this.stateMap.containsKey(userState)) {
			userState.onApplied(this);
		}
		this.stateMap.put(userState, duration);
	}

	/* (non-Javadoc)
	 * @see com.minnymin.zephyr.api.user.User#drainMana(int)
	 */
	@Override
	public void drainMana(int toDrain) {
		if (mana < 0) {
			return;
		}
		setMana(getMana() - toDrain);
	}
	
	/* (non-Javadoc)
	 * @see com.minnymin.zephyr.api.user.User#getMana()
	 */
	@Override
	public int getMana() {
		return this.mana;
	}
	
	/* (non-Javadoc)
	 * @see com.minnymin.zephyr.api.user.User#getMaximumMana()
	 */
	@Override
	public int getMaximumMana() {
		return this.userData.getMaximumMana();
	}

	/* (non-Javadoc)
	 * @see com.minnymin.zephyr.api.user.User#getRequiredLevelProgress()
	 */
	@Override
	public int getRequiredLevelProgress() {
		return this.getUserData().getLevel() ^ 2 * 10 + 100;
	}

	/* (non-Javadoc)
	 * @see com.minnymin.zephyr.api.user.User#getUserData()
	 */
	@Override
	public UserData getUserData() {
		return this.userData;
	}


	/* (non-Javadoc)
	 * @see com.minnymin.zephyr.api.user.User#hasMana(int)
	 */
	@Override
	public boolean hasMana(int toDrain) {
		return getMana() - toDrain >= 0;
	}

	/* (non-Javadoc)
	 * @see com.minnymin.zephyr.api.user.User#isCasting()
	 */
	@Override
	public boolean isCasting() {
		return this.currentlyCasting != null;
	}
	
	/* (non-Javadoc)
	 * @see com.minnymin.zephyr.api.user.User#setCasting(com.minnymin.zephyr.api.spell.ContinuousSpell, com.minnymin.zephyr.api.spell.SpellContext)
	 */
	@Override
	public void setCasting(ContinuousSpell spell, SpellContext context) {
		this.currentlyCasting = spell;
		this.currentlyCastingContext = context;
	}

	/* (non-Javadoc)
	 * @see com.minnymin.zephyr.api.user.User#setMana(int)
	 */
	@Override
	public void setMana(int mana) {
		if (mana > getUserData().getMaximumMana()) {
			this.mana = getUserData().getMaximumMana();
		} else {
			this.mana = mana;
		}
	}
	
	/* (non-Javadoc)
	 * @see com.minnymin.zephyr.api.user.User#tick()
	 */
	@Override
	public void tick() {
		tickTime++;
		
		if (isCasting() && tickTime%currentlyCasting.getUpdateTime()==0) {
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
