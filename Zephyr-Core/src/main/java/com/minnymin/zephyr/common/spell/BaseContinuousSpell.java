package com.minnymin.zephyr.common.spell;

import com.minnymin.zephyr.api.spell.CastResult;
import com.minnymin.zephyr.api.spell.ContinuousSpell;
import com.minnymin.zephyr.api.spell.SpellContext;

public abstract class BaseContinuousSpell extends BaseSpell implements ContinuousSpell {

	private int tickTime;
	private int manaCostPerTick;
	
	public BaseContinuousSpell(String name, String description, int requiredLevel, int manaCost, int experienceReward) {
		this(name, description, requiredLevel, manaCost, experienceReward, 5);
	}
	
	public BaseContinuousSpell(String name, String description, int requiredLevel, int manaCost, int experienceReward, int tickTime) {
		this(name, description, requiredLevel, manaCost, experienceReward, tickTime, manaCost);
	}
	
	public BaseContinuousSpell(String name, String description, int requiredLevel, int manaCost, int experienceReward, int tickTime, int manaCostPerTick) {
		super(name, description, requiredLevel, manaCost, experienceReward);
		this.tickTime = tickTime;
		this.manaCostPerTick = manaCostPerTick;
	}
	
	@Override
	public int getUpdateTime() {
		return this.tickTime;
	}
	
	@Override
	public int getManaPerUpdate() {
		return this.manaCostPerTick;
	}
	
	@Override
	public CastResult cast(SpellContext context) {
		context.getUser().setCasting(this, context);
		update(context);
		return CastResult.SUCCESS;
	}

}
