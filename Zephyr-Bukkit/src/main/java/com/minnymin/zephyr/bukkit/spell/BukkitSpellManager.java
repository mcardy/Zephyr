package com.minnymin.zephyr.bukkit.spell;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;

import com.minnymin.zephyr.api.spell.Spell;
import com.minnymin.zephyr.api.util.Configuration;
import com.minnymin.zephyr.bukkit.ZephyrPlugin;
import com.minnymin.zephyr.bukkit.spell.attack.ButcherSpell;
import com.minnymin.zephyr.bukkit.spell.attack.FireballSpell;
import com.minnymin.zephyr.bukkit.spell.attack.ProjectileSpell;
import com.minnymin.zephyr.bukkit.spell.buff.RegenerationSpell;
import com.minnymin.zephyr.bukkit.util.BukkitConfiguration;
import com.minnymin.zephyr.bukkit.util.YmlConfigFile;
import com.minnymin.zephyr.common.spell.AbstractSpellManager;
import com.minnymin.zephyr.common.spell.BaseSpell;

public class BukkitSpellManager extends AbstractSpellManager {
	
	private YmlConfigFile spellFile;
	
	@Override
	public void onEnable() {
		ZephyrPlugin.logger().info("Registering spells...");
		super.onEnable();
		
		this.spellFile = new YmlConfigFile("spells.yml");
		
		registerSpell(new FireballSpell());
		registerSpell(new ButcherSpell());
		registerSpell(new ProjectileSpell());
		
		registerSpell(new RegenerationSpell());
	}
	
	@Override
	public void onSpellAdded(Spell spell) {
		if (spell instanceof BaseSpell && ((BaseSpell)spell).isListener() && spell instanceof Listener) {
			Bukkit.getPluginManager().registerEvents((Listener) spell, ZephyrPlugin.getInstance());
		}
		Configuration config = new BukkitConfiguration(spellFile, spell.getName());
		spell.defaultConfiguration(config);
		spell.loadConfiguration(config);
	}

	
	
}
