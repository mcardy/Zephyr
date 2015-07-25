package com.minnymin.zephyr.bukkit.command;

import org.bukkit.event.HandlerList;

import com.minnymin.zephyr.bukkit.ZephyrPlugin;
import com.minnymin.zephyr.bukkit.util.command.Cmd;
import com.minnymin.zephyr.bukkit.util.command.CommandContext;

public class ZephyrCommand {

	@Cmd(label = "zephyr", description = "Zephyr utility command", usage = "/zephyr")
	public static void onZephyrCommand(CommandContext context) {
		context.getSender().sendMessage("Zephyr version " + ZephyrPlugin.getInstance().getDescription().getVersion());
	}
	
	@Cmd(label = "zephyr.reload", description = "Reloads all Zephyr loaders", usage = "/zephyr reload")
	public static void onZephyrReloadCommand(CommandContext context) {
		HandlerList.unregisterAll(ZephyrPlugin.getInstance());
		ZephyrPlugin.getInstance().getLogger().info("Unlaoding Zephyr managers...");
		ZephyrPlugin.getInstance().unloadManagers();
		ZephyrPlugin.getInstance().getLogger().info("Loading Zephyr managers...");
		ZephyrPlugin.getInstance().loadManagers();
		ZephyrPlugin.getInstance().getLogger().info("Zephyr reloaded!");
	}
	
}
