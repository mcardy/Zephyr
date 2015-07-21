package com.minnymin.zephyr.api.user;

import java.util.List;

import com.minnymin.zephyr.api.spell.Spell;

public interface UserData {

	public int addLevelProgress(int progress);

	/**
	 * Gets data from the user's data file
	 * @param key The key of the data
	 * @return The result of the data
	 */
	public <T> T getData(String key);

	public List<String> getKnownSpells();

	public int getLevel();

	public int getLevelProgress();

	public int getManaRegeneration();

	public int getMaximumMana();

	/**
	 * Sets data in the user's data file
	 * @param key The key of the data
	 * @param data The data to set
	 */
	public void setData(String key, Object data);

	public void setLevel(int level);

	public void setLevelProgress(int progress);

	public void setManaRegeneration(int rate);

	public void setMaximumMana(int mana);

	public void teachSpell(Spell spell);

}