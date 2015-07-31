package com.minnymin.zephyr.common.spell.condition;

import com.minnymin.zephyr.api.spell.CastCondition;
import com.minnymin.zephyr.api.spell.SpellContext;

public class LearnedCondition implements CastCondition {
	
	@Override
	public boolean canCast(SpellContext context) {
		return context.getUser().isSpellKnown(context.getSpell());
	}
	
	@Override
	public String getMessage(SpellContext context) {
		return "You have not learned " + context.getSpell().getName() + "!";
	}

}
