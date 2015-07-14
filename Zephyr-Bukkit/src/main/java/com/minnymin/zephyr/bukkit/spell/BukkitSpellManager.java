package com.minnymin.zephyr.bukkit.spell;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;

import com.minnymin.zephyr.bukkit.ZephyrPlugin;
import com.minnymin.zephyr.spell.Spell;
import com.minnymin.zephyr.spell.SpellManager;

public class BukkitSpellManager extends SpellManager {

	public BukkitSpellManager() {
		addSpell(new FireballSpell());
		addSpell(new TestContinuousSpell());
		addSpell(new ButcherSpell());
	}
	
	@Override
	public void onSpellAdded(Spell spell) {
		if (spell.isListener() && spell instanceof Listener) {
			Bukkit.getPluginManager().registerEvents((Listener) spell, ZephyrPlugin.getInstance());
		}
	}

	
	
}
