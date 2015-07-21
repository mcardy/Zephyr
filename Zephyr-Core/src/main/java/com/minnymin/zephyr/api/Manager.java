package com.minnymin.zephyr.api;

/**
 * Represents a class that should be enabled and disabled along with the rest of the plugin
 * @author minnymin3
 */
public interface Manager {

	/**
	 * Called when the plugin is disabled
	 */
	public void onDisable();
	
	/**
	 * Called when the plugin is enabled (or when this manager is added to the plugin if already enabled)
	 */
	public void onEnable();
	
}
