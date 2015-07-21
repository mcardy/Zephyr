package com.minnymin.zephyr.bukkit.command;

import com.minnymin.zephyr.api.Manager;
import com.minnymin.zephyr.bukkit.ZephyrPlugin;
import com.minnymin.zephyr.bukkit.util.command.BukkitCommandHandler;

public class BukkitCommandManager implements Manager {
	
	private BukkitCommandHandler handler;

	@Override
	public void onEnable() {
		handler = new BukkitCommandHandler(ZephyrPlugin.getInstance());
		handler.registerCommands(SpellCommand.class);
		handler.registerCommands(UserCommand.class);
		handler.registerHelp();
	}

	@Override
	public void onDisable() {
		handler = null;
	}
	
}
