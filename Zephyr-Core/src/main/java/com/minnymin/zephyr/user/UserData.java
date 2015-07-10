package com.minnymin.zephyr.user;

public interface UserData {

	//public int getLevel();
	//public void setLevel();
	
	/**
	 * Gets maximum mana
	 */
	public int getMana();
	
	/**
	 * Sets maximum mana
	 */
	public void setMana(int mana);
	
	/**
	 * Gets mana regeneration rate per second
	 */
	public int getManaRegeneration();
	
	/**
	 * Sets mana regeneration rate per second
	 */
	public void setManaRegeneration(int mana);
	
	//public List<String> getSpells();
	//public void addSpell(String spellName);
	
	//public String getData(String key);
	//public void setData(String key, String data);
	
}
