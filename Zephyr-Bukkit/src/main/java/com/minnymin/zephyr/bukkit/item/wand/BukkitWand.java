package com.minnymin.zephyr.bukkit.item.wand;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.minnymin.zephyr.api.Zephyr;
import com.minnymin.zephyr.api.spell.Spell;
import com.minnymin.zephyr.api.spell.SpellManager;
import com.minnymin.zephyr.api.user.User;
import com.minnymin.zephyr.api.util.ColorCharacter;
import com.minnymin.zephyr.bukkit.spell.BukkitSpellContext;
import com.minnymin.zephyr.bukkit.spell.BukkitTargetUtils;
import com.minnymin.zephyr.common.item.BaseWand;

public abstract class BukkitWand extends BaseWand {

	private static final String boundLabel = ColorCharacter.GRAY + "Bound spell";

	private int maxLevel;

	public BukkitWand(String name, ColorCharacter color, int maxLevel, String descriptor) {
		super(280, name, color, new String[] { descriptor,
				ChatColor.GRAY + "Max bind level: " + ChatColor.RED + maxLevel });
		this.maxLevel = maxLevel;
	}

	@Override
	public void onInteract(User user) {
		Player player = user.<Player> getPlayerObject();
		Block shelf = null;
		if ((shelf = BukkitTargetUtils.getTargetBlock(player, 5)) != null && shelf.getType() == Material.BOOKSHELF) {
			if (shelf.getRelative(BlockFace.UP).getType() == Material.CHEST) {
				
			} else {
				Set<org.bukkit.entity.Item> itemEntities = getItems(shelf.getRelative(BlockFace.UP).getLocation()
						.add(0.5, 0.5, 0.5));
			}
		} else {
			SpellManager manager = Zephyr.getSpellManager();
			String spellName = getBoundSpell(player.getItemInHand());
			Spell spell = manager.getSpell(spellName);
			if (spell != null) {
				manager.cast(spell, new BukkitSpellContext(spell, user, new String[0]));
			}
		}
	}

	@Override
	public void bindSpell(Object obj, Spell spell, User user) {
		if (!(obj instanceof ItemStack)) {
			user.sendMessage("You can only bind spells to wands!");
			return;
		}
		ItemStack stack = (ItemStack) obj;
		String name = stack.hasItemMeta() && stack.getItemMeta().hasDisplayName() ? stack.getItemMeta()
				.getDisplayName() : null;
		List<String> lore = stack.hasItemMeta() && stack.getItemMeta().hasLore() ? stack.getItemMeta().getLore() : null;
		if (name == null || !ChatColor.stripColor(name).equals(this.getName()) || lore == null) {
			user.sendMessage("You can only bind spells to wands!");
			return;
		}
		if (spell.getLevel() > this.maxLevel) {
			user.sendMessage(ColorCharacter.RED + "Your wand is too low level to bind " + spell.getName() + " to");
			return;
		}
		if (lore.get(lore.size() - 1).split(": ")[0].equals(boundLabel)) {
			lore.remove(lore.size() - 1);
		}
		if (spell != null) {
			lore.add(boundLabel + ": " + spell.getName());
		}
		ItemMeta meta = stack.getItemMeta();
		meta.setLore(lore);
		stack.setItemMeta(meta);
		user.sendMessage(ColorCharacter.BLUE + "Bound " + spell.getName() + " to your wand!");
	}

	@Override
	public String getBoundSpell(Object obj) {
		if (!(obj instanceof ItemStack)) {
			return null;
		}
		ItemStack stack = (ItemStack) obj;
		List<String> lore = stack.hasItemMeta() && stack.getItemMeta().hasLore() ? stack.getItemMeta().getLore() : null;
		if (lore == null) {
			return null;
		}
		String spell = "";
		if (lore.get(lore.size() - 1).split(": ")[0].equals(boundLabel)) {
			spell = lore.get(lore.size() - 1).split(": ")[1];
		}
		return spell;
	}

	private Set<org.bukkit.entity.Item> getItems(Location loc) {
		Set<org.bukkit.entity.Item> set = new HashSet<org.bukkit.entity.Item>();
		for (Entity en : loc.getChunk().getEntities()) {
			if (en.getType() == EntityType.DROPPED_ITEM) {
				if (en.getLocation().distance(loc) <= 1) {
					set.add((org.bukkit.entity.Item) en);
				}
			}
		}
		return set;
	}

}
