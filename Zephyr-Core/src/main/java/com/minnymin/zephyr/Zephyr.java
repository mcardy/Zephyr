package com.minnymin.zephyr;

import com.minnymin.zephyr.spell.SpellManager;

public class Zephyr {

	private static ZephyrPlugin plugin;
	
	public static SpellManager getSpellManager() {
		return plugin.getSpellManager();
	}
	
	public static void setZephyrPlugin(ZephyrPlugin plugin) {
		Zephyr.plugin = plugin;
	}
	
}
