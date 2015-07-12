package com.minnymin.zephyr.sponge.spell;

import com.minnymin.zephyr.spell.CastResult;
import com.minnymin.zephyr.spell.ContinuousSpell;
import com.minnymin.zephyr.spell.SpellContext;

public class TestContinuousSpell extends ContinuousSpell {

	public TestContinuousSpell() {
		super("continuous", "A continuous spell test", 1, 10, 10, 5, 1);
	}

	@Override
	public CastResult castOnTick(SpellContext context) {
		context.getUser().sendMessage("Casting!");
		return CastResult.SUCCESS;
	}

}
