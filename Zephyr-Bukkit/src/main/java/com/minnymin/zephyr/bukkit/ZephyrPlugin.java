package com.minnymin.zephyr.bukkit;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.bukkit.plugin.java.JavaPlugin;

import com.minnymin.zephyr.api.Manager;
import com.minnymin.zephyr.api.Zephyr;
import com.minnymin.zephyr.api.ZephyrAPI;
import com.minnymin.zephyr.bukkit.aspect.BukkitAspectRegister;
import com.minnymin.zephyr.bukkit.command.BukkitCommandManager;
import com.minnymin.zephyr.bukkit.item.BukkitItemManager;
import com.minnymin.zephyr.bukkit.projectile.BukkitProjectileHandler;
import com.minnymin.zephyr.bukkit.spell.BukkitSpellManager;
import com.minnymin.zephyr.bukkit.user.BukkitUserManager;

/**
 * Using Bukkit 1.8.7
 * 
 * @author minnymin3
 */
public class ZephyrPlugin extends JavaPlugin implements ZephyrAPI {
	
	public static boolean DEBUG = true;
	
	private static ZephyrPlugin INSTANCE;
	
	public static ZephyrPlugin getInstance() {
		return ZephyrPlugin.INSTANCE;
	}
	
	public static Logger logger() {
		return getInstance().getLogger();
	}
		
	private List<Manager> managerList = new ArrayList<Manager>();
	
	public ZephyrPlugin() {
		ZephyrPlugin.INSTANCE = this;
	}
	
	@Override
	public void onLoad() {
		Zephyr.addManager(new BukkitAspectRegister());
		Zephyr.addManager(new BukkitCommandManager());
		Zephyr.addManager(new BukkitItemManager());
		Zephyr.addManager(new BukkitProjectileHandler());
		Zephyr.addManager(new BukkitSpellManager());
		Zephyr.addManager(new BukkitUserManager());
	}
	
	@Override
	public void onEnable() {
		logger().info("Starting Zephyrus load...");
		Zephyr.setAPISingleton(this);
		loadManagers();
	}
	
	@Override
	public void onDisable() {
		logger().info("Starting Zephyrus unload...");
		unloadManagers();
		managerList.clear();
	}
	
	public void loadManagers() {
		for (Manager manager : managerList) {
			try {
				manager.onEnable();
			} catch (Exception ex) {
				getLogger().warning("Error while enabling manager " + manager.getClass().getName());
				ex.printStackTrace();
			}
		}
	}
	
	public void unloadManagers() {
		for (Manager manager : managerList) {
			try {
				manager.onDisable();
			} catch (Exception ex) {
				getLogger().warning("Error while disabling manager " + manager.getClass().getName());
				ex.printStackTrace();
			}
		}
	}
	
	@Override
	public void addManager(Manager manager) {
		this.managerList.add(manager);
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public <T extends Manager> T getManager(Class<T> cls) {
		for (Manager manager : managerList) {
			if (cls.isAssignableFrom(manager.getClass())) {
				return (T) manager;
			}
		}
		return null;
	}
	
}
