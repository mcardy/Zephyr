package com.minnymin.zephyr.bukkit.item.wand;

import org.bukkit.Material;

import com.minnymin.zephyr.api.item.ItemRecipe;
import com.minnymin.zephyr.api.util.ColorCharacter;


public class AdeptWand extends BukkitWand {
	
	public AdeptWand() {
		super("Adept Wand", ColorCharacter.BLUE, 10, ColorCharacter.GRAY + "An adept wand");
	}
	
	@Override
	@SuppressWarnings("deprecation")
	public ItemRecipe getRecipe() {
		ItemRecipe recipe = new ItemRecipe().setShapeless(true);
		recipe.setIngredient('a', Material.STICK.getId()).setIngredient('b', Material.GLOWSTONE_DUST.getId());
		return recipe;
	}

}
