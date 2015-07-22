package com.minnymin.zephyr.api;

import java.util.ArrayList;
import java.util.List;

import com.minnymin.zephyr.api.item.ItemManager;
import com.minnymin.zephyr.api.projectile.ProjectileManager;
import com.minnymin.zephyr.api.spell.SpellManager;
import com.minnymin.zephyr.api.user.UserManager;

/**
 * An access class to the Zephyr implementation on a given platform
 *
 * @author minnymin3
 */
public class Zephyr {

	private static ZephyrAPI api;
	private static List<Manager> cachedManagers;
	
	/**
	 * Adds a manager to the ZephyrAPI
	 * @param manager The manager to add
	 */
	public static void addManager(Manager manager) {
		if (Zephyr.api != null) {
			Zephyr.api.addManager(manager);
		} else {
			if (cachedManagers == null) {
				cachedManagers = new ArrayList<Manager>();
			}
			cachedManagers.add(manager);
		}
	}
	
	/**
	 * Gets the {@link ZephyrAPI} for this instance
	 * @return The ZephyrAPI singleton
	 */
	public static ZephyrAPI getAPI() {
		return Zephyr.api;
	}
	
	/**
	 * Gets the {@link ItemManager} for the implementation
	 * @return The ItemManager singleton
	 */
	public static ItemManager getItemManager() {
		return Zephyr.api.getManager(ItemManager.class);
	}
	
	/**
	 * Gets the {@link ProjectileManager} for the implementation
	 * @return The ProjectileManager singleton
	 */
	public static ProjectileManager getProjectileManager() {
		return Zephyr.api.getManager(ProjectileManager.class);
	}
	
	/**
	 * Gets the {@link SpellManager} for the implementation
	 * @return The SpellManager singleton
	 */
	public static SpellManager getSpellManager() {
		return Zephyr.api.getManager(SpellManager.class);
	}
	
	/**
	 * Gets the {@link UserManager} for the implementation
	 * @return The UserManager singleton
	 */
	public static UserManager getUserManager() {
		return Zephyr.api.getManager(UserManager.class);
	}
	
	/**
	 * Sets the API singleton. Will only execute once
	 * @param api The api to set
	 */
	public static void setAPISingleton(ZephyrAPI api) {
		if (Zephyr.api != null) {
			return;
		}
		Zephyr.api = api;
		
		if (Zephyr.cachedManagers != null) {
			for (Manager manager : Zephyr.cachedManagers) {
				Zephyr.api.addManager(manager);
			}
		}
	}
	
}
