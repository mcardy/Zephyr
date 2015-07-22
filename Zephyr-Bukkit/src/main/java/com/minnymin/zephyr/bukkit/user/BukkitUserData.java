package com.minnymin.zephyr.bukkit.user;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.configuration.file.FileConfiguration;

import com.minnymin.zephyr.api.Zephyr;
import com.minnymin.zephyr.api.spell.Spell;
import com.minnymin.zephyr.bukkit.ZephyrPlugin;
import com.minnymin.zephyr.bukkit.util.YmlConfigFile;
import com.minnymin.zephyr.common.user.AbstractUserData;

public class BukkitUserData extends AbstractUserData {

	private YmlConfigFile data;

	public BukkitUserData(UUID playerId) {
		this.data = new YmlConfigFile(playerId.toString() + ".yml", new File(ZephyrPlugin.getInstance().getDataFolder(),
				"players"));

		loadDefaultConfiguration();
	}

	@Override
	@SuppressWarnings("unchecked")
	public <T> T get(String key) {
		try {
			return (T) data.getConfig().get(key);
		} catch (ClassCastException ex) {
			return null;
		}
	}
	
	@Override
	public boolean has(String key) {
		return data.getConfig().contains(key);
	}

	@Override
	public void set(String key, Object value) {
		data.getConfig().set(key, value);
		data.saveConfig();
	}
	
	@Override
	public void setDefault(String key, Object value) {
		if (!data.getConfig().contains(key)) {
			data.getConfig().set(key, value);
			data.saveConfig();
		}
	}

	private void loadDefaultConfiguration() {
		FileConfiguration config = this.data.getConfig();

		if (!config.contains("learned") || !config.contains("mana") || !config.contains("regeneration")
				|| !config.contains("level") || !config.contains("progress")) {
			
			List<String> spellList = new ArrayList<String>();
			for (Spell spell : Zephyr.getSpellManager().getSpellsForLevel(1)) {
				spellList.add(spell.getName());
			}
			
			this.setDefault("learned", spellList);
			this.setDefault("mana", 100);
			this.setDefault("regeneration", 1);
			this.setDefault("level", 1);
			this.setDefault("progress", 0);
			
			this.data.saveConfig();
		}

	}

}
