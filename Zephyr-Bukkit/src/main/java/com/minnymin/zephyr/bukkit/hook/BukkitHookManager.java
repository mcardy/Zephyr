package com.minnymin.zephyr.bukkit.hook;

import com.minnymin.zephyr.common.hook.AbstractHookManager;

public class BukkitHookManager extends AbstractHookManager {
	
	@Override
	public void registerHooks() {
		hooks.add(new BukkitWorldGuardHook());
	}
	
}
