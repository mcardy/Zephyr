package com.minnymin.zephyr.bukkit.command;

import java.util.List;
import java.util.Map.Entry;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.google.common.collect.Lists;
import com.minnymin.zephyr.api.Zephyr;
import com.minnymin.zephyr.api.aspect.Aspect;
import com.minnymin.zephyr.api.aspect.AspectList;
import com.minnymin.zephyr.api.item.Item;
import com.minnymin.zephyr.api.item.WandItem;
import com.minnymin.zephyr.api.spell.Spell;
import com.minnymin.zephyr.api.spell.SpellManager;
import com.minnymin.zephyr.api.user.User;
import com.minnymin.zephyr.bukkit.item.SpellTome;
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
	@Cmd(label = "bind", description = "Binds a spell to the wand in your hand", usage = "/bind <spell>")
	public static void onBind(CommandContext context) {
		Player player = (Player) context.getSender();
		if (context.getOptions().length == 0) {
			context.getSender().sendMessage("Not enough arguments! /cast <spell>");
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
				|| !Zephyr.getUserManager().getUser(player.getUniqueId()).getKnownSpells()
						.contains(spell.getName())) {
			context.getSender().sendMessage("You don't know that spell!");
			return;
		}
		wand.bindSpell(hand, spell, Zephyr.getUserManager().getUser(player.getUniqueId()));
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

	@SuppressWarnings("deprecation")
	@Cmd(label = "aspect", description = "Checks the aspects on an item id", usage = "/aspects <id>")
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
