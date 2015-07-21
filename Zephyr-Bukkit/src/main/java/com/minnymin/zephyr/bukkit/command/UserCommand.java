package com.minnymin.zephyr.bukkit.command;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import com.minnymin.zephyr.api.Zephyr;
import com.minnymin.zephyr.api.user.User;
import com.minnymin.zephyr.bukkit.util.command.Cmd;
import com.minnymin.zephyr.bukkit.util.command.CommandContext;
import com.minnymin.zephyr.bukkit.util.command.SenderSpecification;
import com.minnymin.zephyr.bukkit.util.command.SenderSpecification.SenderType;

public class UserCommand {

	@SenderSpecification(type = SenderType.PLAYER, message = "This command can only be executed by an in-game player")
	@Cmd(label = "mana", description = "Displays your mana", usage = "/mana")
	public static void onMana(CommandContext context) {
		User user = Zephyr.getUserManager().getUser(((Player) context.getSender()).getUniqueId());
		StringBuffer builder = new StringBuffer(ChatColor.GRAY + "Mana " + user.getMana() + " / "
				+ user.getMaximumMana() + ": [");
		int percent = (int) (((float) user.getMana() / (float) user.getMaximumMana()) * 10);
		builder.append(ChatColor.AQUA);
		for (int i = 1; i <= 10; i++) {
			builder.append("=");
			if (i == percent) {
				builder.append(ChatColor.DARK_GRAY);
			}
		}
		builder.append(ChatColor.GRAY + "]");

		context.getSender().sendMessage(builder.toString());
	}

	@SenderSpecification(type = SenderType.PLAYER, message = "This command can only be executed by an in-game player")
	@Cmd(label = "progress", description = "Displays your progress", usage = "/progress")
	public static void onProgress(CommandContext context) {
		User user = Zephyr.getUserManager().getUser(((Player) context.getSender()).getUniqueId());
		StringBuffer builder = new StringBuffer(ChatColor.GRAY + ", Progress " + user.getUserData().getLevelProgress()
				+ " / " + user.getRequiredLevelProgress() + ": [");
		int percent = (int) (((float) user.getUserData().getLevelProgress() / (float) user.getRequiredLevelProgress()) * 10);
		builder.append(ChatColor.GREEN);
		for (int i = 1; i <= 10; i++) {
			builder.append("=");
			if (i == percent) {
				builder.append(ChatColor.DARK_GRAY);
			}
		}
		builder.append(ChatColor.GRAY + "] Level " + user.getUserData().getLevel());

		context.getSender().sendMessage(builder.toString());
	}

	@SuppressWarnings("deprecation")
	@SenderSpecification(type = SenderType.PLAYER, message = "This command can only be executed by an in-game player")
	@Cmd(label = "mana.restore", description = "Restores sender's mana", usage = "/mana restore [<player>]")
	public static void onManaRestore(CommandContext context) {
		User target = null;
		if (context.getOptions().length == 0) {
			target = Zephyr.getUserManager().getUser(((Player) context.getSender()).getUniqueId());
		} else {
			Player player = null;
			if ((player = Bukkit.getPlayer(context.getOptions()[0])) != null) {
				target = Zephyr.getUserManager().getUser(player.getUniqueId());
			} else {
				target = Zephyr.getUserManager().getUser(((Player) context.getSender()).getUniqueId());
			}
		}

		target.setMana(target.getMaximumMana());
		context.getSender().sendMessage(ChatColor.AQUA + "Mana restored!");
	}

}
