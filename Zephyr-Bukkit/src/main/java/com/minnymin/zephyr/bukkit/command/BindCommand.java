package com.minnymin.zephyr.bukkit.command;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.minnymin.zephyr.api.Zephyr;
import com.minnymin.zephyr.api.item.Item;
import com.minnymin.zephyr.api.item.WandItem;
import com.minnymin.zephyr.api.spell.Spell;
import com.minnymin.zephyr.bukkit.util.command.Cmd;
import com.minnymin.zephyr.bukkit.util.command.CommandContext;
import com.minnymin.zephyr.bukkit.util.command.SenderSpecification;
import com.minnymin.zephyr.bukkit.util.command.SenderSpecification.SenderType;

public class BindCommand {

	@SenderSpecification(type = SenderType.PLAYER, message = "This command can only be executed by an in-game player")
	@Cmd(label = "bind", description = "Binds a spell to the wand in your hand", usage = "/bind <left|right> <spell>")
	public static void onBind(CommandContext context) {
		context.getSender().sendMessage("Usage: /bind <left|right> <spell>");
	}

	@SenderSpecification(type = SenderType.PLAYER, message = "This command can only be executed by an in-game player")
	@Cmd(label = "bind.left", description = "Binds a spell to the right click of the wand in your hand", usage = "/bind left <spell>")
	public static void onLeftBindCommand(CommandContext context) {
		bind(context, false);
	}

	@SenderSpecification(type = SenderType.PLAYER, message = "This command can only be executed by an in-game player")
	@Cmd(label = "bind.right", description = "Binds a spell to the left click of the wand in your hand", usage = "/bind right <spell>")
	public static void onRightBindCommand(CommandContext context) {
		bind(context, true);
	}
	
	@SenderSpecification(type = SenderType.PLAYER, message = "This command can only be executed by an in-game player")
	@Cmd(label = "unbind", description = "Unbinds all spells from the wand in your hand", usage = "/unbind [left|right]")
	public static void onUnbind(CommandContext context) {
		unbind(context, false); // left
		unbind(context, true); // right
	}
	
	@SenderSpecification(type = SenderType.PLAYER, message = "This command can only be executed by an in-game player")
	@Cmd(label = "unbind.left", description = "Unbinds a spell from the left click of the wand in your hand", usage = "/unbind left")
	public static void onLeftUnbind(CommandContext context) {
		unbind(context, false);
	}
	
	@SenderSpecification(type = SenderType.PLAYER, message = "This command can only be executed by an in-game player")
	@Cmd(label = "unbind.right", description = "Unbinds a spell from the right click of the wand in your hand", usage = "/unbind right")
	public static void onRightUnbind(CommandContext context) {
		unbind(context, true);
	}

	private static void bind(CommandContext context, boolean right) {
		Player player = (Player) context.getSender();
		if (context.getOptions().length == 0) {
			context.getSender().sendMessage("Not enough arguments! /bind " + (right ? "right" : "left") + " <spell>");
			return;
		}
		ItemStack hand = player.getItemInHand();
		if (hand == null) {
			context.getSender().sendMessage("You must be holding a wand!");
			return;
		}
		Item item = Zephyr.getItemManager().getItem(hand);
		if (item == null || !(item instanceof WandItem)) {
			context.getSender().sendMessage("You must be holding a wand!");
			return;
		}
		WandItem wand = (WandItem) item;
		Spell spell = Zephyr.getSpellManager().getSpell(context.getOptions()[0]);
		if (spell == null
				|| !Zephyr.getUserManager().getUser(player.getUniqueId()).getKnownSpells().contains(spell.getName())) {
			context.getSender().sendMessage("You don't know that spell!");
			return;
		}

		if (right) {
			wand.bindRightClick(hand, spell, Zephyr.getUserManager().getUser(player.getUniqueId()));
		} else {
			wand.bindLeftClick(hand, spell, Zephyr.getUserManager().getUser(player.getUniqueId()));
		}
	}
	
	private static void unbind(CommandContext context, boolean right) {
		Player player = (Player) context.getSender();
		ItemStack hand = player.getItemInHand();
		if (hand == null) {
			context.getSender().sendMessage(ChatColor.RED + "You must be holding a wand!");
			return;
		}
		Item item = Zephyr.getItemManager().getItem(hand);
		if (item == null || !(item instanceof WandItem)) {
			context.getSender().sendMessage(ChatColor.RED + "You must be holding a wand!");
			return;
		}
		WandItem wand = (WandItem) item;

		if (right) {
			wand.bindRightClick(hand, null, Zephyr.getUserManager().getUser(player.getUniqueId()));
		} else {
			wand.bindLeftClick(hand, null, Zephyr.getUserManager().getUser(player.getUniqueId()));
		}
	}

}
