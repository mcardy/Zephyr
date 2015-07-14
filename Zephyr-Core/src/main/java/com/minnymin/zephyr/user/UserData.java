package com.minnymin.zephyr.user;

import java.util.List;

import com.minnymin.zephyr.spell.Spell;

public abstract class UserData {
	
	public int getLevel() {
		return this.<Integer> getData("level");
	}

	public void setLevel(int level) {
		this.setData("level", level);
	}

	public int addLevelProgress(int progress) {
		int newProgress = progress + getLevelProgress();
		setLevelProgress(newProgress);
		return newProgress;
	}

	public int getLevelProgress() {
		return this.<Integer> getData("progress");
	}

	public void setLevelProgress(int progress) {
		this.setData("progress", progress);
	}

	public int getMaximumMana() {
		return this.<Integer> getData("mana");
	}

	public void setMaximumMana(int mana) {
		this.setData("mana", mana);
	}

	public int getManaRegeneration() {
		return this.<Integer> getData("regeneration");
	}

	public void setManaRegeneration(int rate) {
		this.setData("regeneration", rate);
	}

	public List<String> getKnownSpells() {
		return this.<List<String>> getData("learned");
	}

	public void teachSpell(Spell spell) {
		List<String> learned = getKnownSpells();
		learned.add(spell.getName());
		setData("learned", learned);
	}
	
	/**
	 * Gets data from the user's data file
	 * @param key The key of the data
	 * @return The result of the data
	 */
	public abstract <T> T getData(String key);
	
	/**
	 * Sets data in the user's data file
	 * @param key The key of the data
	 * @param data The data to set
	 */
	public abstract void setData(String key, Object data);
	
}
