package com.minnymin.zephyr.bukkit.command;

import java.util.Map.Entry;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.minnymin.zephyr.api.Zephyr;
import com.minnymin.zephyr.api.aspect.Aspect;
import com.minnymin.zephyr.api.aspect.AspectList;
import com.minnymin.zephyr.api.spell.Spell;
import com.minnymin.zephyr.bukkit.item.SpellTome;
import com.minnymin.zephyr.bukkit.util.command.Cmd;
import com.minnymin.zephyr.bukkit.util.command.CommandContext;
import com.minnymin.zephyr.bukkit.util.command.SenderSpecification;
import com.minnymin.zephyr.bukkit.util.command.SenderSpecification.SenderType;

public class ItemCommand {

	@SuppressWarnings("deprecation")
	@Cmd(label = "aspect", aliases = {"aspects"}, description = "Checks the aspects on an item id", usage = "/aspect <id>")
	public static void onAspectCommand(CommandContext context) {
		Material mat;
		AspectList list;

		if (context.getOptions().length == 0) {
			if (!context.isPlayer()) {
				context.getSender().sendMessage("Not enough arguments! /aspects <id>");
				return;
			}
			Player player = (Player) context.getSender();
			if (player.getItemInHand() == null) {
				context.getSender().sendMessage("Must be holding an item to check aspects on");
				return;
			}
			mat = player.getItemInHand().getType();
		} else {
			try {
				mat = Material.getMaterial(Integer.parseInt(context.getOptions()[0]));
			} catch (NumberFormatException e) {
				mat = Material.getMaterial(context.getOptions()[0].toUpperCase());
			}
			if (mat == null) {
				context.getSender().sendMessage("Item not found!");
				return;
			}
		}

		list = Aspect.getAspects(mat.toString());
		if (list == null) {
			context.getSender().sendMessage(mat.toString() + "'s aspects were not found!");
			return;
		}

		StringBuilder builder = null;
		for (Entry<Aspect, Integer> e : list.getAspects().entrySet()) {
			if (builder != null)
				builder.append(", ");
			else
				builder = new StringBuilder("Aspects: ");
			builder.append(e.getKey().getColour() + e.getKey().getDefaultName() + " " + e.getValue());
		}
		context.getSender().sendMessage("Aspects on " + mat.toString());
		context.getSender().sendMessage(builder.toString());

	}

	@SenderSpecification(type = SenderType.PLAYER, message = "This command can only be executed by an in-game player")
	@Cmd(label = "spelltome", description = "Gives a spelltome", usage = "/spelltome <spell>")
	public static void onSpelltome(CommandContext context) {
		if (context.getOptions().length == 0) {
			context.getSender().sendMessage("Not enough arguments! /spelltome <spell>");
			return;
		}
		String name = context.getOptions()[0];
		Spell spell = Zephyr.getSpellManager().getSpell(name);
		if (spell == null) {
			context.getSender().sendMessage("That spell does not exist");
			return;
		}
		ItemStack item = SpellTome.getSpellTome(spell,
				Zephyr.getUserManager().getUser(((Player) context.getSender()).getUniqueId()));
		Player player = (Player) context.getSender();
		player.getInventory().addItem(item);
		player.sendMessage("Gave spelltome");
	}
	
}
