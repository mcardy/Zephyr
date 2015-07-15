package com.minnymin.zephyr.bukkit.user;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.configuration.file.FileConfiguration;

import com.minnymin.zephyr.Zephyr;
import com.minnymin.zephyr.bukkit.ZephyrPlugin;
import com.minnymin.zephyr.bukkit.util.YmlConfigFile;
import com.minnymin.zephyr.spell.Spell;
import com.minnymin.zephyr.user.UserData;

public class BukkitUserData extends UserData {

	private YmlConfigFile data;

	public BukkitUserData(UUID playerId) {
		this.data = new YmlConfigFile(playerId.toString() + ".yml", new File(ZephyrPlugin.getInstance().getDataFolder(),
				"players"));

		loadDefaultConfiguration();
	}

	@Override
	@SuppressWarnings("unchecked")
	public <T> T getData(String key) {
		try {
			return (T) data.getConfig().get(key);
		} catch (ClassCastException ex) {
			return null;
		}
	}

	@Override
	public void setData(String key, Object value) {
		data.getConfig().set(key, value);
		data.saveConfig();
	}

	private void loadDefaultConfiguration() {
		FileConfiguration config = this.data.getConfig();

		if (!config.contains("learned") || !config.contains("mana") || !config.contains("regeneration")
				|| !config.contains("level") || !config.contains("progress")) {
			
			List<String> spellList = new ArrayList<String>();
			for (Spell spell : Zephyr.getSpellManager().getSpellsForLevel(1)) {
				spellList.add(spell.getName());
			}
			
			this.data.addDefaults("learned", spellList);
			this.data.addDefaults("mana", 100);
			this.data.addDefaults("regeneration", 1);
			this.data.addDefaults("level", 1);
			this.data.addDefaults("progress", 0);
			
			this.data.saveConfig();
		}

	}

}
