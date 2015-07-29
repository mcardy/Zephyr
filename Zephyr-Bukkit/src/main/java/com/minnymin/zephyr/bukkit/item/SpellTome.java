package com.minnymin.zephyr.bukkit.item;

import org.apache.commons.lang.WordUtils;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;

import com.google.common.collect.Lists;
import com.minnymin.zephyr.api.Zephyr;
import com.minnymin.zephyr.api.spell.Spell;
import com.minnymin.zephyr.api.spell.SpellRecipe;
import com.minnymin.zephyr.api.user.User;
import com.minnymin.zephyr.api.util.ColorCharacter;
import com.minnymin.zephyr.common.item.BaseActionItem;

public class SpellTome extends BaseActionItem {

	private static final String SEPERATOR = " - " + ColorCharacter.GOLD;

	public SpellTome() {
		super(387, "Spell Tome", ColorCharacter.GRAY, "Click to learn spell");
	}

	@Override
	public void onLeftClick(User user) {
		Player player = user.<Player> getPlayer();
		ItemStack stack = player.getItemInHand();
		String displayName = stack.getItemMeta().getDisplayName();
		Spell spell = Zephyr.getSpellManager().getSpell(displayName.split(SEPERATOR)[1].toLowerCase());
		if (spell != null) {
			if (spell.getLevel() > user.getLevel()) {
				player.sendMessage(ChatColor.RED + "You are not high enough level to craft " + spell.getName() + ". "
						+ user.getLevel() + " / " + spell.getLevel());
				return;
			}
			SpellRecipe recipe;
			Spell prerequisite;
			if ((recipe = spell.getRecipe()).hasPrerequisite()
					&& !user.isSpellKnown(prerequisite = recipe.getPrerequisite())) {
				player.sendMessage(ChatColor.RED + "You must know " + prerequisite.getName() + " before you can learn "
						+ spell.getName());
				return;
			}
			user.teachSpell(spell);
			user.sendMessage("Learned " + spell.getName());
			player.getInventory().remove(stack);
		} else {
			user.sendMessage(ChatColor.RED + "Uh oh! That spell tome appears to be broken...");
		}
	}

	public static ItemStack getSpellTome(Spell spell, User user) {
		ItemStack stack = new ItemStack(Material.WRITTEN_BOOK);
		BookMeta meta = (BookMeta) stack.getItemMeta();

		StringBuilder page = new StringBuilder();

		page.append("Spell: " + spell.getName() + "\n");
		page.append(spell.getDescription() + "\n");
		page.append("Mana Cost: " + spell.getManaCost());
		page.append("\n\n");
		page.append("Cast this spell with /cast " + WordUtils.capitalize(spell.getName()) + "\n\n");
		page.append("Learn this spell by left clicking the book");

		meta.setPages(page.toString());
		meta.setAuthor(user.<Player> getPlayer().getName());
		meta.setDisplayName(ChatColor.GOLD + "Spell Tome" + ChatColor.GRAY + SEPERATOR
				+ WordUtils.capitalize(spell.getName()));
		meta.setLore(Lists.newArrayList(ChatColor.GRAY + "Learn by left clicking"));
		stack.setItemMeta(meta);
		return stack;
	}

}
