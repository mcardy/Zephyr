package com.minnymin.zephyr.bukkit.spell;

import java.util.Collection;

import org.bukkit.entity.Monster;

import com.minnymin.zephyr.spell.CastResult;
import com.minnymin.zephyr.spell.ContinuousSpell;
import com.minnymin.zephyr.spell.SpellContext;
import com.minnymin.zephyr.spell.target.Targeted;
import com.minnymin.zephyr.spell.target.Targeted.TargetType;

@Targeted(type = TargetType.MONSTER_AREA)
public class TestContinuousSpell extends ContinuousSpell {

	public TestContinuousSpell() {
		super("continuous", "A continuous spell", 1, 10, 10, 10, 5);
	}

	@Override
	public CastResult castOnTick(SpellContext context) {
		for (Monster m : context.<Collection<Monster>>getTarget().get()) {
			m.damage(1);
		}
		return CastResult.SUCCESS;
	}

}
