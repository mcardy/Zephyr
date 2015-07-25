package com.minnymin.zephyr.bukkit.spell.attack;

import com.minnymin.zephyr.api.aspect.Aspect;
import com.minnymin.zephyr.api.aspect.AspectList;
import com.minnymin.zephyr.api.spell.CastResult;
import com.minnymin.zephyr.api.spell.SpellContext;
import com.minnymin.zephyr.api.spell.SpellRecipe;
import com.minnymin.zephyr.bukkit.projectile.BallProjectile;
import com.minnymin.zephyr.common.spell.BaseSpell;

public class ProjectileSpell extends BaseSpell {

	public ProjectileSpell() {
		super("projectile", "Launch a projectile", 5, 10);
	}

	@Override
	public CastResult cast(SpellContext context) {
		new BallProjectile().launch(context.getUser());
		return CastResult.SUCCESS;
	}
	
	@Override
	public SpellRecipe getRecipe() {
		return new SpellRecipe(AspectList.build(Aspect.FIRE, 8));
	}

}
