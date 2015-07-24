package com.minnymin.zephyr.bukkit.item;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.google.common.collect.Lists;
import com.minnymin.zephyr.api.Zephyr;
import com.minnymin.zephyr.api.spell.Spell;
import com.minnymin.zephyr.api.user.User;
import com.minnymin.zephyr.api.util.ColorCharacter;
import com.minnymin.zephyr.common.item.BaseActionItem;

public class SpellTome extends BaseActionItem {

	private static final String SEPERATOR = " - ";
	
	public SpellTome() {
		super(340, "Spell Tome", ColorCharacter.GRAY, "Click to learn spell");
	}

	@Override
	public void onInteract(User user) {
		Player player = user.<Player>getPlayerObject();
		ItemStack stack = player.getItemInHand();
		String displayName = stack.getItemMeta().getDisplayName();
		Spell spell = Zephyr.getSpellManager().getSpell(displayName.split(SEPERATOR)[1]);
		// TODO check if spell can be learned
		if (spell != null) {
			user.getUserData().teachSpell(spell);
			user.sendMessage("Learned " + spell.getName());
			player.getInventory().remove(stack);
		} else {
			user.sendMessage("Uh oh! That spell tome appears to be broken...");
		}
	}
	
	public static ItemStack getSpellTome(Spell spell) {
		ItemStack stack = new ItemStack(Material.BOOK);
		ItemMeta meta = stack.getItemMeta();
		meta.setDisplayName(ChatColor.GRAY + "Spell Tome" + SEPERATOR + spell.getName());
		meta.setLore(Lists.asList("Click to learn spell", new String[]{}));
		stack.setItemMeta(meta);
		return stack;
	}

}
