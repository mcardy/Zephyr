package com.minnymin.zephyr.spell;

import com.minnymin.zephyr.spell.target.TargetHolder;
import com.minnymin.zephyr.user.User;

public abstract class SpellContext {

	protected String[] args;
	protected User user;
	protected Spell spell;
	protected int power;
	
	public SpellContext(Spell spell, User user, String[] args) {
		this.args = args;
		this.user = user;
		this.spell = spell;
		this.power = 1;
	}
	
	public String[] getArguments() {
		return this.args;
	}
	
	public User getUser() {
		return this.user;
	}
	
	public <T> T getPlayer() {
		return this.user.<T>getPlayerObject();
	}
	
	public int getPower() {
		return power;
	}
	
	public Spell getSpell() {
		return this.spell;
	}
	
	public abstract boolean hasTarget();
	public abstract <T> TargetHolder<T> getTarget();
	
}
