package com.minnymin.zephyr.common.spell;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.minnymin.zephyr.api.Zephyr;
import com.minnymin.zephyr.api.spell.CastCondition;
import com.minnymin.zephyr.api.spell.Spell;
import com.minnymin.zephyr.api.spell.target.Targeted;
import com.minnymin.zephyr.api.spell.target.Targeted.TargetType;
import com.minnymin.zephyr.api.util.Configuration;
import com.minnymin.zephyr.common.spell.condition.LearnedCondition;
import com.minnymin.zephyr.common.spell.condition.ManaCondition;
import com.minnymin.zephyr.common.spell.condition.RequiredTargetCondition;
import com.minnymin.zephyr.common.spell.condition.WorldGuardTargetBlockCondition;
import com.minnymin.zephyr.common.spell.condition.WorldGuardTargetEntityCondition;

public abstract class BaseSpell implements Spell {

	protected boolean listener = false;

	private String name;
	private String description;

	private int requiredLevel;
	private int manaCost;
	private int experienceReward;
	
	private List<CastCondition> conditions;

	public BaseSpell(String name, String description, int requiredLevel, int manaCost) {
		// Don't want experience reward to surpass level * 10 to avoid OP spells
		this(name, description, requiredLevel, manaCost, (manaCost / 5) > (requiredLevel * 10) ? (requiredLevel * 10)
				: (manaCost / 5));
	}

	public BaseSpell(String name, String description, int requiredLevel, int manaCost, int experienceReward) {
		this.name = name;
		this.description = description;

		this.requiredLevel = requiredLevel;
		this.manaCost = manaCost;
		this.experienceReward = experienceReward;
		
		conditions = new ArrayList<CastCondition>();
		conditions.add(new ManaCondition());
		conditions.add(new LearnedCondition());
		
		if (this.getClass().isAnnotationPresent(Targeted.class)) {
			Targeted targeted = this.getClass().getAnnotation(Targeted.class);
			if (!targeted.optional()) {
				conditions.add(new RequiredTargetCondition());
			}
			
			if (Zephyr.getHookManager().getWorldGuardHook() != null) {
				if (targeted.type() == TargetType.BLOCK) {
					conditions.add(new WorldGuardTargetBlockCondition());
				} else {
					conditions.add(new WorldGuardTargetEntityCondition());
				}
			}
		}
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
	public int getLevel() {
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
	
	@Override
	public void addCondition(CastCondition condition) {
		this.conditions.add(condition);
	}
	
	@Override
	public Collection<CastCondition> getConditions() {
		Set<CastCondition> cloned = new HashSet<CastCondition>();
		cloned.addAll(this.conditions);
		return cloned;
	}

}
