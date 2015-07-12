package com.minnymin.zephyr.spell;


public abstract class Spell {

	protected boolean listener = false;
	
	private String name;
	private String description;
	
	private int requiredLevel;
	private int manaCost;
	private int experienceReward;
	
	public Spell(String name, String description, int requiredLevel, int manaCost) {
		this(name, description, requiredLevel, manaCost, manaCost/4);
	}
	
	public Spell(String name, String description, int requiredLevel, int manaCost, int experienceReward) {
		this.name = name;
		this.description = description;
		
		this.requiredLevel = requiredLevel;
		this.manaCost = manaCost;
		this.experienceReward = experienceReward;
	}
	
	public abstract CastResult cast(SpellContext context);
	
	public String getName() {
		return this.name;
	}
	
	public String getDescription() {
		return this.description;
	}
	
	public int getExperienceGain() {
		return this.experienceReward;
	}
	
	public int getRequiredLevel() {
		return this.requiredLevel;
	}
	
	public int getManaCost() {
		return this.manaCost;
	}
	
	public boolean isListener() {
		return this.listener;
	}
		
}
