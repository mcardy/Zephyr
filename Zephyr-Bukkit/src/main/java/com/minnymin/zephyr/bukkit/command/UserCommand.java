package com.minnymin.zephyr.bukkit.command;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import com.google.common.collect.Lists;
import com.minnymin.zephyr.api.Zephyr;
import com.minnymin.zephyr.api.spell.Spell;
import com.minnymin.zephyr.api.spell.SpellManager;
import com.minnymin.zephyr.api.user.User;
import com.minnymin.zephyr.bukkit.spell.BukkitSpellContext;
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
		for (int i = 0; i < 10; i++) {
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
		StringBuffer builder = new StringBuffer(ChatColor.GRAY + "Progress " + user.getUserData().getLevelProgress()
				+ " / " + user.getRequiredLevelProgress() + ": [");
		float percent = ((float) user.getUserData().getLevelProgress() / (float) user.getRequiredLevelProgress()) * 10;
		builder.append(ChatColor.GREEN);
		for (int i = 0; i < 10; i++) {
			builder.append("=");
			if (i == (int) percent) {
				builder.append(ChatColor.DARK_GRAY);
			}
		}
		builder.append(ChatColor.GRAY + "] Level " + user.getLevel());

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
	
	@SenderSpecification(type = SenderType.PLAYER, message = "This command can only be executed by an in-game player")
	@Cmd(label = "alias", aliases = { "shortcut", "c.set" }, description = "Bind a spell to a shortcut", usage = "/alias <key> <spell>")
	public static void onAlias(CommandContext context) {
		User user = Zephyr.getUserManager().getUser(((Player) context.getSender()).getUniqueId());
		if (context.getOptions().length < 2) {
			context.getSender().sendMessage("Usage: /alias <key> <spell>");
			return;
		}
		String key = context.getOptions()[0];
		String spellName = context.getOptions()[1];
		if (Zephyr.getSpellManager().getSpell(spellName) == null
				|| !user.getKnownSpells().contains(spellName)) {
			context.getSender().sendMessage("You do not know a spell by that name");
			return;
		}
		user.addAlias(key, Zephyr.getSpellManager().getSpell(spellName));
		user.sendMessage("Alias added: '/c " + key + "' will now cast " + spellName);
	}

	@SenderSpecification(type = SenderType.PLAYER, message = "This command can only be executed by an in-game player")
	@Cmd(label = "c", description = "Cast a spell via shortcut set with /alias", usage = "/c <spell>")
	public static void onCastShortcut(CommandContext context) {
		User user = Zephyr.getUserManager().getUser(((Player) context.getSender()).getUniqueId());
		if (context.getOptions().length == 0) {
			if (user.isCasting()) {
				user.setCasting(null, null);
			} else {
				context.getSender().sendMessage("Not enough arguments! /c <spell>");
				return;
			}
		}
		SpellManager manager = Zephyr.getSpellManager();
		Spell spell = manager.getSpell(user.getAliases().get(context.getOptions()[0]));
		if (spell == null) {
			context.getSender().sendMessage("That alias was not found. Set it with /alias <key> <spell>");
			return;
		}
		List<String> list = Lists.newArrayList(context.getOptions());
		list.remove(0);
		String[] args = list.toArray(new String[list.size()]);
		manager.cast(spell, new BukkitSpellContext(spell, user, args));
		context.getSender().sendMessage("Spell cast!");
	}
	
	@SenderSpecification(type = SenderType.PLAYER, message = "This command can only be executed by an in-game player")
	@Cmd(label = "levelup", description = "Levels up the user", usage = "/levelup")
	public static void onProgressAdd(CommandContext context) {
		User user = Zephyr.getUserManager().getUser(((Player)context.getSender()).getUniqueId());
		user.addLevelProgress(user.getRequiredLevelProgress()-user.getUserData().getLevelProgress());
	}

}
