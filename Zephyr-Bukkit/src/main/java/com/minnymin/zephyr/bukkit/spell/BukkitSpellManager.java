package com.minnymin.zephyr.bukkit.spell;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;

import com.minnymin.zephyr.api.spell.Spell;
import com.minnymin.zephyr.bukkit.ZephyrPlugin;
import com.minnymin.zephyr.bukkit.spell.attack.ButcherSpell;
import com.minnymin.zephyr.bukkit.spell.attack.FireballSpell;
import com.minnymin.zephyr.bukkit.spell.attack.ProjectileSpell;
import com.minnymin.zephyr.common.spell.AbstractSpellManager;
import com.minnymin.zephyr.common.spell.BaseSpell;

public class BukkitSpellManager extends AbstractSpellManager {
	
	@Override
	public void onEnable() {
		super.onEnable();
		registerSpell(new FireballSpell());
		registerSpell(new ButcherSpell());
		registerSpell(new ProjectileSpell());
	}
	
	@Override
	public void onSpellAdded(Spell spell) {
		if (spell instanceof BaseSpell && ((BaseSpell)spell).isListener() && spell instanceof Listener) {
			Bukkit.getPluginManager().registerEvents((Listener) spell, ZephyrPlugin.getInstance());
		}
	}

	
	
}
