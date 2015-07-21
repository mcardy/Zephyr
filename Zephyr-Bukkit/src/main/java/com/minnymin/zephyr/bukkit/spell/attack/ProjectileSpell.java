package com.minnymin.zephyr.bukkit.spell.attack;

import com.minnymin.zephyr.api.spell.CastResult;
import com.minnymin.zephyr.api.spell.SpellContext;
import com.minnymin.zephyr.bukkit.projectile.BallProjectile;
import com.minnymin.zephyr.common.spell.BaseSpell;

public class ProjectileSpell extends BaseSpell {

	public ProjectileSpell() {
		super("projectile", "Launch a projectile", 1, 10);
	}

	@Override
	public CastResult cast(SpellContext context) {
		new BallProjectile().launch(context.getUser());
		return CastResult.SUCCESS;
	}

}
