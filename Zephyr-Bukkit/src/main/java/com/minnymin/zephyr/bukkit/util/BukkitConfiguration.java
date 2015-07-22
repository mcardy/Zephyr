package com.minnymin.zephyr.bukkit.util;

import org.bukkit.configuration.ConfigurationSection;

import com.minnymin.zephyr.api.util.Configuration;

public class BukkitConfiguration implements Configuration {

	private YmlConfigFile config;
	private String section;
	
	public BukkitConfiguration(YmlConfigFile config, String section) {
		this.config = config;
		this.section = section;
	}
	
	public ConfigurationSection getConfig() {
		if (!config.getConfig().contains(section)) {
			return config.getConfig().createSection(section);
		}
		return config.getConfig().getConfigurationSection(section);
	}
	
	public void saveConfig() {
		config.saveConfig();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <T> T get(String key) {
		try {
			return (T) this.getConfig().get(key);
		} catch (Exception ex) {
			return null;
		}
	}

	@Override
	public boolean has(String key) {
		return this.getConfig().contains(key);
	}

	@Override
	public void set(String key, Object value) {
		this.getConfig().set(key, value);
		this.saveConfig();
	}

	@Override
	public void setDefault(String key, Object value) {
		if (!this.getConfig().contains(key)) {
			this.getConfig().set(key, value);
			this.saveConfig();
		}
	}
	
}
