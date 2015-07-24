package com.minnymin.zephyr.bukkit.item.wand;

import org.bukkit.Material;

import com.minnymin.zephyr.api.item.ItemRecipe;
import com.minnymin.zephyr.api.util.ColorCharacter;

public class NoviceWand extends BukkitWand {

	public NoviceWand() {
		super("Novice Wand", ColorCharacter.GREEN, 5, ColorCharacter.GRAY + "A basic wand wand");
	}

	@Override
	@SuppressWarnings("deprecation")
	public ItemRecipe getRecipe() {
		ItemRecipe recipe = new ItemRecipe().setShapeless(true);
		recipe.setIngredient('a', Material.STICK.getId()).setIngredient('b', Material.REDSTONE.getId());
		return recipe;
	}

}
