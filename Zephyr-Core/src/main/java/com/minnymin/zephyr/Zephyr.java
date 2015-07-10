package com.minnymin.zephyr;

import com.minnymin.zephyr.spell.SpellManager;
import com.minnymin.zephyr.user.UserManager;

public class Zephyr {

	private static ZephyrAPI api;
	
	public static SpellManager getSpellManager() {
		return Zephyr.api.getSpellManager();
	}
	
	public static UserManager getUserManager() {
		return Zephyr.api.getUserManager();
	}
	
	public static ZephyrAPI getAPI() {
		return Zephyr.api;
	}
	
	public static void setAPISingleton(ZephyrAPI api) {
		Zephyr.api = api;
	}
	
}
