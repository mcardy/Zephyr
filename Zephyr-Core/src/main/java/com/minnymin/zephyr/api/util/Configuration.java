package com.minnymin.zephyr.api.util;

public interface Configuration {

	/**
	 * Gets the value of a key in a configuration file
	 * @param key The key to get
	 * @return The value, if castable to T
	 */
	public <T> T get(String key);
	
	/**
	 * Checks if the configuration contains the specified key
	 * @param key The key to check
	 * @return True if the key is present
	 */
	public boolean has(String key);
	
	/**
	 * Sets the value of a key to the specified object in a configuration file
	 * @param key The key to set
	 * @param value The value to set to
	 */
	public void set(String key, Object value);
	
	/**
	 * Sets the value of a key to the specified object in a configuration file if it has not previously been set
	 * @param key The key to set
	 * @param value The value to set
	 */
	public void setDefault(String key, Object value);
	
}
