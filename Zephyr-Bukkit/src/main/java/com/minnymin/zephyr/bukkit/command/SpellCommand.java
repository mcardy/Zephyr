package com.minnymin.zephyr.bukkit.command;

import java.util.List;

import org.bukkit.entity.Player;

import com.google.common.collect.Lists;
import com.minnymin.zephyr.Zephyr;
import com.minnymin.zephyr.bukkit.spell.BukkitSpellContext;
import com.minnymin.zephyr.spell.Spell;
import com.minnymin.zephyr.spell.SpellManager;
import com.minnymin.zephyr.user.User;
import com.minnymin.zephyr.util.Cmd;
import com.minnymin.zephyr.util.CommandContext;
import com.minnymin.zephyr.util.SenderSpecification;
import com.minnymin.zephyr.util.SenderSpecification.SenderType;

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
