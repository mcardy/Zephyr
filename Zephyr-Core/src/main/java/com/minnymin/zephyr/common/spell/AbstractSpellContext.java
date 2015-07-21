package com.minnymin.zephyr.common.spell;

import com.minnymin.zephyr.api.spell.Spell;
import com.minnymin.zephyr.api.spell.SpellContext;
import com.minnymin.zephyr.api.user.User;

public abstract class AbstractSpellContext implements SpellContext {

	protected String[] args;
	protected User user;
	protected Spell spell;
	protected int power;
	
	public AbstractSpellContext(Spell spell, User user, String[] args) {
		this.args = args;
		this.user = user;
		this.spell = spell;
		this.power = 1;
	}
	
	@Override
	public String[] getArguments() {
		return this.args;
	}
	
	@Override
	public User getUser() {
		return this.user;
	}
	
	@Override
	public <T> T getPlayer() {
		return this.user.<T>getPlayerObject();
	}
	
	@Override
	public int getPower() {
		return power;
	}
	
	@Override
	public Spell getSpell() {
		return this.spell;
	}
	
}
