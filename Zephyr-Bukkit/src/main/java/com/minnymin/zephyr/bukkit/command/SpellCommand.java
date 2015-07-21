package com.minnymin.zephyr.bukkit.command;

import java.util.List;

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

public class SpellCommand {

	@SenderSpecification(type = SenderType.PLAYER, message = "This command can only be executed by an in-game player")
	@Cmd(description = "Casts a spell", label = "cast", usage = "/cast <spell> [<args...>]")
	public static void onCast(CommandContext context) {
		User user = Zephyr.getUserManager().getUser(((Player)context.getSender()).getUniqueId());
		if (context.getOptions().length == 0) {
			if (user.isCasting()) {
				user.setCasting(null, null);
			} else {
				context.getSender().sendMessage("Not enough arguments! /cast <spell>");
				return;
			}
		}
		SpellManager manager = Zephyr.getSpellManager();
		Spell spell = manager.getSpell(context.getOptions()[0]);
		List<String> list = Lists.newArrayList(context.getOptions());
		list.remove(0);
		String[] args = list.toArray(new String[list.size()]);
		manager.cast(spell, new BukkitSpellContext(spell, user, args));
	}
	
}
