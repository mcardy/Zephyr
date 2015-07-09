package com.minnymin.zephyr.spell;

public abstract class Spell {

	private String name;
	private String description;
	
	public Spell(String name, String description) {
		this.name = name;
		this.description = description;
	}
	
	public abstract void cast(SpellContext context);
	
	public String getName() {
		return this.name;
	}
	
	public String getDescription() {
		return this.description;
	}
	
	public int getExperienceGain() {
		return 0;
	}
	
	public int getRequiredLevel() {
		return 0;
	}
	
	public int getManaCost() {
		return 0;
	}
		
}
