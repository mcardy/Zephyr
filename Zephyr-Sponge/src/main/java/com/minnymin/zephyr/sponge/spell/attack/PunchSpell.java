package com.minnymin.zephyr.sponge.spell.attack;

import com.minnymin.zephyr.api.spell.CastResult;
import com.minnymin.zephyr.api.spell.SpellContext;
import com.minnymin.zephyr.api.spell.target.Targeted;
import com.minnymin.zephyr.api.spell.target.Targeted.TargetType;
import com.minnymin.zephyr.common.spell.BaseSpell;

@Targeted(type = TargetType.LIVING, range = 10)
public class PunchSpell extends BaseSpell {
	
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
