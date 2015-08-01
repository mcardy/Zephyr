package com.minnymin.zephyr.common.hook;

import java.util.HashSet;
import java.util.Set;

import com.minnymin.zephyr.api.hook.Hook;
import com.minnymin.zephyr.api.hook.HookManager;
import com.minnymin.zephyr.api.hook.WorldGuardHook;

public abstract class AbstractHookManager implements HookManager {

	protected Set<Hook> hooks = new HashSet<Hook>();
	
	public abstract void registerHooks();
	
	@Override
	public void onDisable() {	
		for (Hook hook : hooks) {
			if (hook.isProvided()) {
				hook.unload();
			}
		}
	}

	@Override
	public void onEnable() {	
		registerHooks();
		for (Hook hook : hooks) {
			if (hook.isProvided()) {
				hook.load();
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <T extends Hook> T getHook(Class<T> cls) {
		for (Hook hook : hooks) {
			if (cls.isAssignableFrom(hook.getClass())) {
				return (T) hook;
			}
		}
		return null;
	}
	
	@Override
	public WorldGuardHook getWorldGuardHook() {
		return this.getHook(WorldGuardHook.class);
	}

}
