package com.minnymin.zephyr.common.user;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

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
	private int coolingTicks;

	private UserData userData;

	protected AbstractUser(UserData data) {
		this.userData = data;
		this.mana = data.has("logoutmana") ? data.<Integer>get("logoutmana") : data.getMaximumMana();
		this.stateMap = new HashMap<UserState, Integer>();
	}

	@Override
	public void addAlias(String key, Spell value) {
		List<String> aliases = this.userData.<List<String>> get("aliases");
		aliases = (aliases == null) ? new ArrayList<String>() : aliases;
		for (String s : aliases) {
			if (s.startsWith(key)) {
				aliases.remove(s);
				break;
			}
		}
		aliases.add(key + " " + value.getName());
		this.userData.set("aliases", aliases);
	}

	@Override
	public int addLevelProgress(int progress) {
		int currentProgress = this.getUserData().addLevelProgress(progress);
		int requiredProgress = getRequiredLevelProgress();
		if (currentProgress >= requiredProgress) {
			currentProgress -= requiredProgress;
			this.getUserData().setLevel(this.getUserData().getLevel() + 1);
			this.getUserData().setLevelProgress(currentProgress);
			this.getUserData().setMaximumMana(this.getUserData().getMaximumMana() + 100);
			if (this.getLevel()%2==0) {
				this.getUserData().setManaRegeneration(this.getUserData().getManaRegeneration()+1);
			}
			sendMessage("You have levelled up to level " + this.getUserData().getLevel());
			// Spells on level up
			// StringBuilder spellString = new StringBuilder();
			// for (Spell spell :
			// Zephyr.getSpellManager().getSpellsForLevel(this.userData.getLevel()))
			// {
			// this.getUserData().teachSpell(spell);
			// if (spellString.length() == 0) {
			// spellString.append(spell.getName());
			// } else {
			// spellString.append(", " + spell.getName());
			// }
			// }
			// if (spellString.length() != 0) {
			// sendMessage("You have learned: " + spellString.toString());
			// }
		}
		return currentProgress;
	}

	@Override
	public void addState(UserState userState, int duration) {
		if (!this.stateMap.containsKey(userState)) {
			userState.onApplied(this);
		}
		this.stateMap.put(userState, duration);
	}

	@Override
	public void drainMana(int toDrain) {
		if (mana < 0) {
			return;
		}
		setMana(getMana() - toDrain);
	}

	@Override
	public Map<String, String> getAliases() {
		List<String> aliases = this.userData.<List<String>> get("aliases");
		aliases = (aliases == null) ? new ArrayList<String>() : aliases;
		Map<String, String> aliasMap = new HashMap<String, String>();
		for (String entry : aliases) {
			String[] split = entry.split(" ");
			String key = split[0];
			String value = split[1];
			aliasMap.put(key, value);
		}
		return aliasMap;
	}

	@Override
	public Set<UserState> getAppliedStates() {
		return this.stateMap.keySet();
	}

	@Override
	public int getLevel() {
		return this.userData.getLevel();
	}

	@Override
	public int getMana() {
		return this.mana;
	}

	@Override
	public int getMaximumMana() {
		return this.userData.getMaximumMana();
	}

	@Override
	public List<String> getKnownSpells() {
		return this.userData.getKnownSpells();
	}

	@Override
	public int getRequiredLevelProgress() {
		return (int) ((Math.pow(this.getUserData().getLevel(), 2)) * 10 + 100);
	}

	@Override
	public UserData getUserData() {
		return this.userData;
	}

	@Override
	public boolean hasMana(int toDrain) {
		return getMana() - toDrain >= 0;
	}

	@Override
	public boolean isCasting() {
		return this.currentlyCasting != null;
	}
	
	@Override
	public boolean isCooling() {
		return this.coolingTicks > 0;
	}

	@Override
	public boolean isSpellKnown(Spell spell) {
		return getKnownSpells().contains(spell.getName());
	}
	
	@Override
	public void logout() {
		for (UserState state : getAppliedStates()) {
			state.onRemoved(this);
		}
		this.stateMap.clear();
		this.getUserData().set("logoutmana", this.mana);
	}

	@Override
	public void removeState(UserState state) {
		this.stateMap.remove(state);
		state.onRemoved(this);
	}

	@Override
	public void setCasting(ContinuousSpell spell, SpellContext context) {
		this.currentlyCasting = spell;
		this.currentlyCastingContext = context;
	}

	@Override
	public void setCooling(boolean cooling) {
		this.coolingTicks = cooling ? 5 : 0;
	}
	
	@Override
	public void setMana(int mana) {
		if (mana > getUserData().getMaximumMana()) {
			this.mana = getUserData().getMaximumMana();
		} else {
			this.mana = mana;
		}
	}

	@Override
	public void teachSpell(Spell spell) {
		this.getUserData().teachSpell(spell);
	}

	@Override
	public void tick() {
		tickTime++;
		boolean reset = tickTime % 20 == 0;

		if (isCasting() && tickTime % currentlyCasting.getUpdateTime() == 0) {
			Zephyr.getSpellManager().castContinuous(currentlyCasting, currentlyCastingContext);
		}

		for (Entry<UserState, Integer> entry : this.stateMap.entrySet()) {
			if (entry.getKey().getTickTime() != 0 && tickTime % entry.getKey().getTickTime() == 0) {
				entry.getKey().tick(this);
			}
			if (reset) {
				if (entry.getValue() - 1 != 0) {
					this.stateMap.put(entry.getKey(), entry.getValue() - 1);
				} else {
					this.removeState(entry.getKey());
				}
			}
		}

		if (reset) {
			tickTime = 0;
			if (getMana() < getUserData().getMaximumMana()) {
				setMana(getMana() + getUserData().getManaRegeneration());
			}
			if (this.isCooling()) {
				this.setCooling(false);
			}
		}
		
		if (this.isCooling()) {
			coolingTicks--;
		}
	}

}
