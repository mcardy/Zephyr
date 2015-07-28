package com.minnymin.zephyr.bukkit.spell.attack;

import com.minnymin.zephyr.api.aspect.Aspect;
import com.minnymin.zephyr.api.aspect.AspectList;
import com.minnymin.zephyr.api.spell.CastResult;
import com.minnymin.zephyr.api.spell.SpellContext;
import com.minnymin.zephyr.api.spell.SpellRecipe;
import com.minnymin.zephyr.bukkit.projectile.ArrowProjectile;
import com.minnymin.zephyr.common.spell.BaseSpell;

public class ArrowSpell extends BaseSpell {

	public ArrowSpell() {
		super("arrow", "Launches an arrow", 1, 30);
	}
	
	@Override
	public CastResult cast(SpellContext context) {
		new ArrowProjectile().launch(context.getUser());
		return CastResult.SUCCESS;
	}
	
	@Override
	public SpellRecipe getRecipe() {
		return new SpellRecipe(AspectList.build(Aspect.WEAPON, 8).add(Aspect.WIND, 8));
	}

}
