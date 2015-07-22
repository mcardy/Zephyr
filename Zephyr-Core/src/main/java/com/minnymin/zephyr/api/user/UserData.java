package com.minnymin.zephyr.api.user;

import java.util.List;

import com.minnymin.zephyr.api.spell.Spell;
import com.minnymin.zephyr.api.util.Configuration;

public interface UserData extends Configuration {

	public int addLevelProgress(int progress);
	
	public List<String> getKnownSpells();

	public int getLevel();

	public int getLevelProgress();

	public int getManaRegeneration();

	public int getMaximumMana();

	public void setLevel(int level);

	public void setLevelProgress(int progress);

	public void setManaRegeneration(int rate);

	public void setMaximumMana(int mana);

	public void teachSpell(Spell spell);

}