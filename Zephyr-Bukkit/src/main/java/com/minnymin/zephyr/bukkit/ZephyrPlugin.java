package com.minnymin.zephyr.bukkit;

import org.bukkit.entity.Entity;
import org.bukkit.plugin.java.JavaPlugin;

import com.minnymin.zephyr.Zephyr;
import com.minnymin.zephyr.ZephyrAPI;
import com.minnymin.zephyr.bukkit.command.SpellCommand;
import com.minnymin.zephyr.bukkit.command.UserCommand;
import com.minnymin.zephyr.bukkit.projectile.BukkitProjectileHandler;
import com.minnymin.zephyr.bukkit.spell.BukkitSpellManager;
import com.minnymin.zephyr.bukkit.user.BukkitUserManager;
import com.minnymin.zephyr.bukkit.util.command.BukkitCommandHandler;
import com.minnymin.zephyr.projectile.ProjectileHandler;
import com.minnymin.zephyr.spell.SpellManager;
import com.minnymin.zephyr.user.UserManager;

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
	
	private ProjectileHandler<Entity> projectileHandler;
	private SpellManager spellManager;
	private UserManager userManager;
	
	public ZephyrPlugin() {
		ZephyrPlugin.INSTANCE = this;
	}
	
	@Override
	public void onEnable() {
		Zephyr.setAPISingleton(this);
		this.projectileHandler = new BukkitProjectileHandler();
		this.spellManager = new BukkitSpellManager();
		this.userManager = new BukkitUserManager();
		
		BukkitCommandHandler handler = new BukkitCommandHandler(this);
		handler.registerCommands(UserCommand.class);
		handler.registerCommands(SpellCommand.class);
		handler.registerHelp();	
	}

	@Override
	public void onDisable() {
	}

	@Override
	public ProjectileHandler<?> getProjectileHandler() {
		return this.projectileHandler;
	}
	
	@Override
	public SpellManager getSpellManager() {
		return this.spellManager;
	}

	@Override
	public UserManager getUserManager() {
		return this.userManager;
	}
	
}
