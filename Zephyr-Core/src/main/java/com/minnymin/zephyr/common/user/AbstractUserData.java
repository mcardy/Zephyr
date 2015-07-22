package com.minnymin.zephyr.common.user;

import java.util.List;

import com.minnymin.zephyr.api.spell.Spell;
import com.minnymin.zephyr.api.user.UserData;

public abstract class AbstractUserData implements UserData {
	
	@Override
	public int getLevel() {
		return this.<Integer> get("level");
	}

	@Override
	public void setLevel(int level) {
		this.set("level", level);
	}

	@Override
	public int addLevelProgress(int progress) {
		int newProgress = progress + getLevelProgress();
		setLevelProgress(newProgress);
		return newProgress;
	}

	@Override
	public int getLevelProgress() {
		return this.<Integer> get("progress");
	}

	@Override
	public void setLevelProgress(int progress) {
		this.set("progress", progress);
	}

	@Override
	public int getMaximumMana() {
		return this.<Integer> get("mana");
	}

	@Override
	public void setMaximumMana(int mana) {
		this.set("mana", mana);
	}

	@Override
	public int getManaRegeneration() {
		return this.<Integer> get("regeneration");
	}

	@Override
	public void setManaRegeneration(int rate) {
		this.set("regeneration", rate);
	}

	@Override
	public List<String> getKnownSpells() {
		return this.<List<String>> get("learned");
	}

	@Override
	public void teachSpell(Spell spell) {
		List<String> learned = getKnownSpells();
		learned.add(spell.getName());
		set("learned", learned);
	}
	
}
