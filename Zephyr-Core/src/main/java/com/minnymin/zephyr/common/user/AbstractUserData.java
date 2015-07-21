package com.minnymin.zephyr.common.user;

import java.util.List;

import com.minnymin.zephyr.api.spell.Spell;
import com.minnymin.zephyr.api.user.UserData;

public abstract class AbstractUserData implements UserData {
	
	@Override
	public int getLevel() {
		return this.<Integer> getData("level");
	}

	@Override
	public void setLevel(int level) {
		this.setData("level", level);
	}

	@Override
	public int addLevelProgress(int progress) {
		int newProgress = progress + getLevelProgress();
		setLevelProgress(newProgress);
		return newProgress;
	}

	@Override
	public int getLevelProgress() {
		return this.<Integer> getData("progress");
	}

	@Override
	public void setLevelProgress(int progress) {
		this.setData("progress", progress);
	}

	@Override
	public int getMaximumMana() {
		return this.<Integer> getData("mana");
	}

	@Override
	public void setMaximumMana(int mana) {
		this.setData("mana", mana);
	}

	@Override
	public int getManaRegeneration() {
		return this.<Integer> getData("regeneration");
	}

	@Override
	public void setManaRegeneration(int rate) {
		this.setData("regeneration", rate);
	}

	@Override
	public List<String> getKnownSpells() {
		return this.<List<String>> getData("learned");
	}

	@Override
	public void teachSpell(Spell spell) {
		List<String> learned = getKnownSpells();
		learned.add(spell.getName());
		setData("learned", learned);
	}
	
}
