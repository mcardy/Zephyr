package com.minnymin.zephyr.bukkit.command;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import com.minnymin.zephyr.Zephyr;
import com.minnymin.zephyr.user.User;
import com.minnymin.zephyr.util.Cmd;
import com.minnymin.zephyr.util.CommandContext;
import com.minnymin.zephyr.util.SenderSpecification;
import com.minnymin.zephyr.util.SenderSpecification.SenderType;

public class UserCommand {

	@SenderSpecification(type =SenderType.PLAYER, message = "This command can only be executed by an in-game player")
	@Cmd(label = "mana", description = "Displays your mana", usage = "/mana")
	public static void onMana(CommandContext context) {
		User user = Zephyr.getUserManager().getUser(((Player)context.getSender()).getUniqueId());		
		StringBuffer builder = new StringBuffer(ChatColor.GRAY + "Mana " + user.getMana() + " / " + user.getMaximumMana() + ": [");
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
	
}
