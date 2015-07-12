package com.minnymin.zephyr.sponge.spell;

import org.spongepowered.api.entity.living.Living;

import com.minnymin.zephyr.spell.CastResult;
import com.minnymin.zephyr.spell.Spell;
import com.minnymin.zephyr.spell.SpellContext;
import com.minnymin.zephyr.spell.TargetHolder;
import com.minnymin.zephyr.spell.Targeted;
import com.minnymin.zephyr.spell.Targeted.TargetType;

@Targeted(type = TargetType.LIVING)
public class TestSpell extends Spell {

	public TestSpell() {
		super("test", "This is a test", 1, 10, 5);
	}

	@Override
	public CastResult cast(SpellContext context) {
		TargetHolder<Living> target = context.<Living>getTarget();
		Living entity = target.get();
		context.getUser().sendMessage(entity.getType().getName());
		return CastResult.SUCCESS;
	}

}
