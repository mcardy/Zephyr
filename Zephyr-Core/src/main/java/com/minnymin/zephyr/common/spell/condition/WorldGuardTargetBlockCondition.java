package com.minnymin.zephyr.common.spell.condition;

import com.minnymin.zephyr.api.Zephyr;
import com.minnymin.zephyr.api.hook.WorldGuardHook;
import com.minnymin.zephyr.api.spell.CastCondition;
import com.minnymin.zephyr.api.spell.SpellContext;
import com.minnymin.zephyr.api.spell.target.Targeted.TargetType;

public class WorldGuardTargetBlockCondition implements CastCondition {

	@Override
	public boolean canCast(SpellContext context) {
		WorldGuardHook hook;
		if ((hook = Zephyr.getHookManager().getWorldGuardHook()) == null || context.getTargetType() != TargetType.BLOCK) {
			return true;
		}
		return hook.canUserTargetBlock(context);
	}

	@Override
	public String getMessage(SpellContext context) {
		return "You cannot target that block";
	}

}
