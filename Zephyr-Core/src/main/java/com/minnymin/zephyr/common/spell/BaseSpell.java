package com.minnymin.zephyr.common.spell;

import com.minnymin.zephyr.api.spell.Spell;
import com.minnymin.zephyr.api.util.Configuration;


public abstract class BaseSpell implements Spell {

	protected boolean listener = false;
	
	private String name;
	private String description;
	
	private int requiredLevel;
	private int manaCost;
	private int experienceReward;
	
	public BaseSpell(String name, String description, int requiredLevel, int manaCost) {
		this(name, description, requiredLevel, manaCost, manaCost/4);
	}
	
	public BaseSpell(String name, String description, int requiredLevel, int manaCost, int experienceReward) {
		this.name = name;
		this.description = description;
		
		this.requiredLevel = requiredLevel;
		this.manaCost = manaCost;
		this.experienceReward = experienceReward;
	}
	
	@Override
	public String getName() {
		return this.name;
	}
	
	@Override
	public String getDescription() {
		return this.description;
	}
	
	@Override
	public int getExperienceGain() {
		return this.experienceReward;
	}
	
	@Override
	public int getRequiredLevel() {
		return this.requiredLevel;
	}
	
	@Override
	public int getManaCost() {
		return this.manaCost;
	}
	
	public boolean isListener() {
		return this.listener;
	}
	
	@Override
	public void defaultConfiguration(Configuration config) {		
	}
	
	@Override
	public void loadConfiguration(Configuration config) {		
	}
		
}
