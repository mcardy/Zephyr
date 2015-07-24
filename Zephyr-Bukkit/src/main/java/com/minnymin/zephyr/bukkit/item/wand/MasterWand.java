package com.minnymin.zephyr.bukkit.item.wand;

import org.bukkit.ChatColor;
import org.bukkit.Material;

import com.minnymin.zephyr.api.item.ItemRecipe;
import com.minnymin.zephyr.api.util.ColorCharacter;

public class MasterWand extends BukkitWand {

	public MasterWand() {
		super("Master Wand", ColorCharacter.AQUA, 50, ChatColor.GRAY + "The wand of a master");
	}
	
	@Override
	@SuppressWarnings("deprecation")
	public ItemRecipe getRecipe() {
		ItemRecipe recipe = new ItemRecipe().setShapeless(true);
		recipe.setIngredient('a', Material.STICK.getId()).setIngredient('b', Material.ENDER_PEARL.getId());
		return recipe;
	}
	
}
