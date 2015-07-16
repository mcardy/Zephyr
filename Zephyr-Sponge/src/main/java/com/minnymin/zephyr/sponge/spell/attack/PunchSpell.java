package com.minnymin.zephyr.sponge.spell.attack;

import com.minnymin.zephyr.spell.CastResult;
import com.minnymin.zephyr.spell.Spell;
import com.minnymin.zephyr.spell.SpellContext;
import com.minnymin.zephyr.spell.target.Targeted;
import com.minnymin.zephyr.spell.target.Targeted.TargetType;

@Targeted(type = TargetType.LIVING, range = 10)
public class PunchSpell extends Spell {
	
	public PunchSpell() {
		super("punch", "Punches an entity", 1, 10);
	}

	@Override
	public CastResult cast(SpellContext context) {
		// Living target = context.<Living> getTarget().get();
		// TODO Wait for Sponge to implement more entity data stuff
		return CastResult.SUCCESS;
	}

}
