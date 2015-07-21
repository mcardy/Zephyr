package com.minnymin.zephyr;

import java.util.ArrayList;
import java.util.List;

import com.minnymin.zephyr.projectile.ProjectileHandler;
import com.minnymin.zephyr.spell.SpellManager;
import com.minnymin.zephyr.user.UserManager;

public class Zephyr {

	private static ZephyrAPI api;
	private static List<Manager> cachedManagers;
	
	public static void addManager(Manager manager) {
		if (Zephyr.api != null) {
			Zephyr.api.addManager(manager);
		} else {
			if (cachedManagers == null) {
				cachedManagers = new ArrayList<Manager>();
			}
			cachedManagers.add(manager);
		}
	}
	
	public static ProjectileHandler<?> getProjectileHandler() {
		return Zephyr.api.getManager(ProjectileHandler.class);
	}
	
	public static SpellManager getSpellManager() {
		return Zephyr.api.getManager(SpellManager.class);
	}
	
	public static UserManager getUserManager() {
		return Zephyr.api.getManager(UserManager.class);
	}
	
	public static ZephyrAPI getAPI() {
		return Zephyr.api;
	}
	
	public static void setAPISingleton(ZephyrAPI api) {
		if (Zephyr.api != null) {
			return;
		}
		Zephyr.api = api;
		
		if (Zephyr.cachedManagers != null) {
			for (Manager manager : Zephyr.cachedManagers) {
				Zephyr.api.addManager(manager);
			}
		}
	}
	
}
