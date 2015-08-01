package com.minnymin.zephyr.api.hook;

import com.minnymin.zephyr.api.Manager;

public interface HookManager extends Manager {

	public <T extends Hook> T getHook(Class<T> cls);
	
	public WorldGuardHook getWorldGuardHook();
		
}
