package com.minnymin.zephyr.bukkit.util;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import com.minnymin.zephyr.bukkit.ZephyrPlugin;

/**
 * Zephyr - YmlConfigFile.java<br> Represents a config file that can be created
 * within the Zephyr folder. Allows for add-ons to have all configs in one
 * place.
 * 
 * @author minnymin3
 * 
 */

public class YmlConfigFile {

	private File configFile;
	private File dataFolder;
	private FileConfiguration fileConfiguration;
	private String fileName;

	/**
	 * Creates a new yml config file with the given file name existing in
	 * Zephyrus's data folder
	 * 
	 * @param fileName The name of the file in plugins/Zephyrus/
	 */
	public YmlConfigFile(String fileName) {
		this(fileName, ZephyrPlugin.getInstance().getDataFolder());
	}

	/**
	 * Creates a new yml config file with the given file name existing in the
	 * provided data folder
	 * 
	 * @param fileName The name of the file in the data folder
	 * @param dataFolder The data folder for the file to reside in
	 */
	public YmlConfigFile(String fileName, File dataFolder) {
		this.fileName = fileName;
		this.dataFolder = dataFolder;
	}

	/**
	 * Gets the configuration
	 * 
	 * @return A FileConfiguration object pointing to the config file
	 */
	public FileConfiguration getConfig() {
		if (fileConfiguration == null) {
			this.reloadConfig();
		}
		return fileConfiguration;
	}

	/**
	 * The current file of this configuration
	 */
	public File getFile() {
		return configFile;
	}

	/**
	 * The name of this configuration
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * Reloads the config form the file
	 */
	public void reloadConfig() {
		if (configFile == null) {
			if (!dataFolder.exists())
				dataFolder.mkdirs();
			configFile = new File(dataFolder, fileName);
		}
		fileConfiguration = YamlConfiguration.loadConfiguration(configFile);
	}

	/**
	 * Saves the config to the file
	 */
	public void saveConfig() {
		if (!dataFolder.exists())
			dataFolder.mkdirs();
		configFile = new File(dataFolder, fileName);
		if (fileConfiguration == null || configFile == null) {
			return;
		}
		try {
			getConfig().save(configFile);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * Saves the default config to the file
	 */
	public void saveDefaultConfig() {
		if (!dataFolder.exists())
			dataFolder.mkdirs();
		configFile = new File(dataFolder, fileName);
		if (!configFile.exists() && this.dataFolder == ZephyrPlugin.getInstance().getDataFolder()
				&& ZephyrPlugin.getInstance().getResource(fileName) != null) {
			ZephyrPlugin.getInstance().saveResource(fileName, false);
		} else if (!configFile.exists()) {
			saveConfig();
		}
	}

}
