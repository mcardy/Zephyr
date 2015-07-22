package com.minnymin.zephyr.bukkit.item;

import java.util.List;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.minnymin.zephyr.api.Zephyr;
import com.minnymin.zephyr.api.item.ItemRecipe;
import com.minnymin.zephyr.api.spell.Spell;
import com.minnymin.zephyr.api.spell.SpellManager;
import com.minnymin.zephyr.api.user.User;
import com.minnymin.zephyr.api.util.ColorCode;
import com.minnymin.zephyr.bukkit.spell.BukkitSpellContext;
import com.minnymin.zephyr.common.item.BaseWand;

public class Wand extends BaseWand {

	private final String boundLabel = ColorCode.GRAY + "Bound spell";
	
	public Wand() {
		super(280, "Wand", ColorCode.AQUA, ColorCode.GREEN + "A basic wand");
	}

	@Override
	public ItemRecipe getRecipe() {
		ItemRecipe recipe = new ItemRecipe(true);
		recipe.setIngredient('a', 280).setIngredient('b', 348);
		return recipe;
	}
	
	@Override
	public void onInteract(User user) {
		SpellManager manager = Zephyr.getSpellManager();
		String spellName = getBoundSpell(user.<Player>getPlayerObject().getItemInHand());
		Spell spell = manager.getSpell(spellName);
		if (spell != null) {
			manager.cast(spell, new BukkitSpellContext(spell, user, new String[0]));
		}
	}

	@Override
	public void bindSpell(Object obj, Spell spell) {
		if (!(obj instanceof ItemStack)) {
			return;
		}
		ItemStack stack = (ItemStack) obj;
		List<String> lore = stack.hasItemMeta() && stack.getItemMeta().hasLore() ? stack.getItemMeta().getLore() : null;
		if (lore == null) {
			return;
		}
		if (lore.get(lore.size()-1).split(": ")[0].equals(this.boundLabel)) {
			lore.remove(lore.size()-1);
		}
		if (spell != null) {
			lore.add(this.boundLabel + ": " + spell.getName());
		}
		ItemMeta meta = stack.getItemMeta();
		meta.setLore(lore);
		stack.setItemMeta(meta);
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
		if (lore.get(lore.size()-1).split(": ")[0].equals(this.boundLabel)) {
			spell = lore.get(lore.size()-1).split(": ")[1];
		}
		return spell;
	}

}
