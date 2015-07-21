package com.minnymin.zephyr.bukkit;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.plugin.java.JavaPlugin;

import com.minnymin.zephyr.Manager;
import com.minnymin.zephyr.Zephyr;
import com.minnymin.zephyr.ZephyrAPI;
import com.minnymin.zephyr.bukkit.aspect.BukkitAspectRegister;
import com.minnymin.zephyr.bukkit.command.BukkitCommandManager;
import com.minnymin.zephyr.bukkit.projectile.BukkitProjectileHandler;
import com.minnymin.zephyr.bukkit.spell.BukkitSpellManager;
import com.minnymin.zephyr.bukkit.user.BukkitUserManager;

/**
 * Using Bukkit 1.8.7
 * 
 * @author minnymin3
 */
public class ZephyrPlugin extends JavaPlugin implements ZephyrAPI {
	
	private static ZephyrPlugin INSTANCE;
	
	public static ZephyrPlugin getInstance() {
		return ZephyrPlugin.INSTANCE;
	}
		
	private List<Manager> managerList = new ArrayList<Manager>();

	public ZephyrPlugin() {
		ZephyrPlugin.INSTANCE = this;
		
		Zephyr.addManager(new BukkitAspectRegister());
		Zephyr.addManager(new BukkitCommandManager());
		Zephyr.addManager(new BukkitProjectileHandler());
		Zephyr.addManager(new BukkitSpellManager());
		Zephyr.addManager(new BukkitUserManager());
	}
	
	@Override
	public void onEnable() {
		Zephyr.setAPISingleton(this);
		for (Manager manager : managerList) {
			manager.onEnable();
		}
	}
	
	@Override
	public void onDisable() {
		for (Manager manager : managerList) {
			manager.onDisable();
		}
		managerList.clear();
	}
	
	@Override
	public void addManager(Manager manager) {
		this.managerList.add(manager);
		if (this.isEnabled()) {
			manager.onEnable();
		}
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
