package com.minnymin.zephyr.bukkit.spell.creation;

import org.bukkit.entity.Player;

import com.minnymin.zephyr.api.aspect.Aspect;
import com.minnymin.zephyr.api.aspect.AspectList;
import com.minnymin.zephyr.api.spell.CastResult;
import com.minnymin.zephyr.api.spell.SpellContext;
import com.minnymin.zephyr.api.spell.SpellRecipe;
import com.minnymin.zephyr.common.spell.BaseSpell;

public class CraftSpell extends BaseSpell {

	public CraftSpell() {
		super("craft", "Opens a crafting table", 1, 100);
	}

	@Override
	public CastResult cast(SpellContext context) {
		context.<Player>getPlayer().openWorkbench(null, true);
		return CastResult.SUCCESS;
	}

	@Override
	public SpellRecipe getRecipe() {
		return new SpellRecipe(AspectList.build(Aspect.CONSTRUCT, 16).add(Aspect.WOOD, 16));
	}

}
