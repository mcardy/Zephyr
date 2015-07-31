package com.minnymin.zephyr.common.spell.condition;

import com.minnymin.zephyr.api.spell.CastCondition;
import com.minnymin.zephyr.api.spell.SpellContext;

public class ManaCondition implements CastCondition {

	@Override
	public boolean canCast(SpellContext context) {
		return context.getUser().hasMana(context.getSpell().getManaCost());
	}

	@Override
	public String getMessage(SpellContext context) {
		return "You do not have enough mana to cast this spell! " + context.getUser().getMana() + "/"
				+ context.getSpell().getManaCost();
	}

}
