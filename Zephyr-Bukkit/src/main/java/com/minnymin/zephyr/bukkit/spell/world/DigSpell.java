package com.minnymin.zephyr.bukkit.spell.world;

import com.minnymin.zephyr.api.spell.CastResult;
import com.minnymin.zephyr.api.spell.SpellContext;
import com.minnymin.zephyr.api.spell.SpellRecipe;
import com.minnymin.zephyr.common.spell.BaseSpell;

public class DigSpell extends BaseSpell {

	public DigSpell(String name, String description, int requiredLevel, int manaCost) {
		super(name, description, requiredLevel, manaCost);
	}

	@Override
	public CastResult cast(SpellContext context) {
		return null;
	}

	@Override
	public SpellRecipe getRecipe() {
		return null;
	}

	
	
}
