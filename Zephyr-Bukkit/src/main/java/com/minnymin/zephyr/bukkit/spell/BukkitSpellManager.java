package com.minnymin.zephyr.bukkit.spell;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;

import com.minnymin.zephyr.bukkit.ZephyrPlugin;
import com.minnymin.zephyr.bukkit.spell.attack.ButcherSpell;
import com.minnymin.zephyr.bukkit.spell.attack.FireballSpell;
import com.minnymin.zephyr.bukkit.spell.attack.ProjectileSpell;
import com.minnymin.zephyr.spell.Spell;
import com.minnymin.zephyr.spell.SpellManager;

public class BukkitSpellManager extends SpellManager {
	
	@Override
	public void onEnable() {
		super.onEnable();
		addSpell(new FireballSpell());
		addSpell(new ButcherSpell());
		addSpell(new ProjectileSpell());
	}
	
	@Override
	public void onSpellAdded(Spell spell) {
		if (spell.isListener() && spell instanceof Listener) {
			Bukkit.getPluginManager().registerEvents((Listener) spell, ZephyrPlugin.getInstance());
		}
	}

	
	
}
