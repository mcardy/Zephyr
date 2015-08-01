package com.minnymin.zephyr.api.hook;

import com.minnymin.zephyr.api.spell.SpellContext;

public interface WorldGuardHook extends Hook {

	public boolean canUserTargetBlock(SpellContext context);
	public boolean canUserTargetEntity(SpellContext context);
	
}
