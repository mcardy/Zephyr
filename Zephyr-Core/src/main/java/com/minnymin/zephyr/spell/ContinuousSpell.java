package com.minnymin.zephyr.spell;

public abstract class ContinuousSpell extends Spell{

	private int tickTime;
	private int manaCostPerTick;
	
	public ContinuousSpell(String name, String description, int requiredLevel, int manaCost, int experienceReward) {
		this(name, description, requiredLevel, manaCost, experienceReward, 5);
	}
	
	public ContinuousSpell(String name, String description, int requiredLevel, int manaCost, int experienceReward, int tickTime) {
		this(name, description, requiredLevel, manaCost, experienceReward, tickTime, manaCost);
	}
	
	public ContinuousSpell(String name, String description, int requiredLevel, int manaCost, int experienceReward, int tickTime, int manaCostPerTick) {
		super(name, description, requiredLevel, manaCost, experienceReward);
		this.tickTime = tickTime;
		this.manaCostPerTick = manaCostPerTick;
	}
	
	public int getTickTime() {
		return this.tickTime;
	}
	
	public int getManaCostPerTick() {
		return this.manaCostPerTick;
	}
	
	@Override
	public CastResult cast(SpellContext context) {
		context.getUser().setCasting(this, context);
		castOnTick(context);
		return CastResult.SUCCESS;
	}
	
	public abstract CastResult castOnTick(SpellContext context);

}
