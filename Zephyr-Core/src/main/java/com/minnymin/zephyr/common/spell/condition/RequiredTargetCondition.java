package com.minnymin.zephyr.common.spell.condition;

import com.minnymin.zephyr.api.spell.CastCondition;
import com.minnymin.zephyr.api.spell.SpellContext;

public class RequiredTargetCondition implements CastCondition {

	@Override
	public boolean canCast(SpellContext context) {
		return context.hasTarget();
	}

	@Override
	public String getMessage(SpellContext context) {
		return "You do not have a target!";
	}
	
}
