package com.minnymin.zephyr.bukkit.spell;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;

import com.minnymin.zephyr.api.spell.Spell;
import com.minnymin.zephyr.api.util.Configuration;
import com.minnymin.zephyr.bukkit.ZephyrPlugin;
import com.minnymin.zephyr.bukkit.spell.attack.ArrowRainSpell;
import com.minnymin.zephyr.bukkit.spell.attack.ArrowSpell;
import com.minnymin.zephyr.bukkit.spell.attack.ArrowStormSpell;
import com.minnymin.zephyr.bukkit.spell.attack.BoltSpell;
import com.minnymin.zephyr.bukkit.spell.attack.FireballSpell;
import com.minnymin.zephyr.bukkit.spell.attack.FirechargeSpell;
import com.minnymin.zephyr.bukkit.spell.attack.HomingArrowSpell;
import com.minnymin.zephyr.bukkit.spell.attack.PoisonCloudSpell;
import com.minnymin.zephyr.bukkit.spell.attack.PoisonSpell;
import com.minnymin.zephyr.bukkit.spell.creation.CraftSpell;
import com.minnymin.zephyr.bukkit.spell.creation.EnderchestSpell;
import com.minnymin.zephyr.bukkit.spell.mobility.BlinkSpell;
import com.minnymin.zephyr.bukkit.spell.mobility.HomeSpell;
import com.minnymin.zephyr.bukkit.spell.mobility.PhaseSpell;
import com.minnymin.zephyr.bukkit.spell.restoration.FeedSpell;
import com.minnymin.zephyr.bukkit.spell.restoration.FeederSpell;
import com.minnymin.zephyr.bukkit.spell.restoration.HealSpell;
import com.minnymin.zephyr.bukkit.spell.restoration.HealerSpell;
import com.minnymin.zephyr.bukkit.spell.state.SpeedSpell;
import com.minnymin.zephyr.bukkit.spell.world.DigSpell;
import com.minnymin.zephyr.bukkit.spell.world.MineSpell;
import com.minnymin.zephyr.bukkit.spell.world.ProspectSpell;
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
				
		// Attack spells
		registerSpell(new ArrowSpell());
		registerSpell(new ArrowStormSpell());
		registerSpell(new ArrowRainSpell());
		registerSpell(new BoltSpell());
		registerSpell(new FireballSpell());
		registerSpell(new FirechargeSpell());
		registerSpell(new HomingArrowSpell());
		registerSpell(new PoisonCloudSpell());
		registerSpell(new PoisonSpell());
		
		// Creation spells
		registerSpell(new CraftSpell());
		registerSpell(new EnderchestSpell());
		
		// Mobility spells
		registerSpell(new BlinkSpell());
		registerSpell(new HomeSpell());
		registerSpell(new PhaseSpell());
		
		// Restoration spells
		registerSpell(new FeederSpell());
		registerSpell(new FeedSpell());
		registerSpell(new HealerSpell());
		registerSpell(new HealSpell());
		
		// State spells
		registerSpell(new SpeedSpell());
		
		// World spells
		registerSpell(new DigSpell());
		registerSpell(new MineSpell());
		registerSpell(new ProspectSpell());
	}
	
	@Override
	public void onSpellAdded(Spell spell) {
		if (spell instanceof BaseSpell && ((BaseSpell)spell).isListener() && spell instanceof Listener) {
			Bukkit.getPluginManager().registerEvents((Listener) spell, ZephyrPlugin.getInstance());
		}
		Configuration config = new BukkitConfiguration(spellFile, spell.getName());
		spell.defaultConfiguration(config);
		spell.loadConfiguration(config);
		
		if (ZephyrPlugin.DEBUG && spell.getRecipe() == null) {
			ZephyrPlugin.getInstance().getLogger().warning(spell.getName() + " does not have a recipe!");
		}
	}
	
}
