package com.minnymin.zephyr.spell;

import java.util.Map;

public abstract class SpellRecipe {

	public static SpellRecipeBuilder builder() {
		return new SpellRecipeBuilder();
	}
	
	private Map<String, Integer> recipe;
	
	protected SpellRecipe() {
		
	}
	
}
