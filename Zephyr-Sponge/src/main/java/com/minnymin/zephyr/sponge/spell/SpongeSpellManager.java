package com.minnymin.zephyr.sponge.spell;

import java.io.File;
import java.io.IOException;

import ninja.leaping.configurate.commented.CommentedConfigurationNode;
import ninja.leaping.configurate.hocon.HoconConfigurationLoader;
import ninja.leaping.configurate.loader.ConfigurationLoader;

import com.minnymin.zephyr.api.spell.Spell;
import com.minnymin.zephyr.api.util.Configuration;
import com.minnymin.zephyr.common.spell.AbstractSpellManager;
import com.minnymin.zephyr.common.spell.BaseSpell;
import com.minnymin.zephyr.sponge.ZephyrPlugin;
import com.minnymin.zephyr.sponge.spell.attack.PunchSpell;
import com.minnymin.zephyr.sponge.util.SpongeConfiguration;

public class SpongeSpellManager extends AbstractSpellManager {
	
	private ConfigurationLoader<CommentedConfigurationNode> loader;
	
	@Override
	public void onEnable() {
		super.onEnable();
		
		File potentialFile = new File(ZephyrPlugin.getConfigDirectory(), "spells.cfg");
		if (!potentialFile.exists()) {
			potentialFile.getParentFile().mkdirs();
			try {
				potentialFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		this.loader = HoconConfigurationLoader.builder().setFile(potentialFile).build();
		
		registerSpell(new PunchSpell());
	}
	
	@Override
	public void onSpellAdded(Spell spell) {
		if (spell instanceof BaseSpell && ((BaseSpell)spell).isListener()) {
			ZephyrPlugin.getGame().getEventManager().register(ZephyrPlugin.getInstance(), spell);
		}
		Configuration config = new SpongeConfiguration(loader, spell.getName());
		spell.defaultConfiguration(config);
		spell.loadConfiguration(config);
	}

}
