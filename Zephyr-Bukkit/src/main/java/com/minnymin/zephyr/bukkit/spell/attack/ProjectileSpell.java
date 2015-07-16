package com.minnymin.zephyr.bukkit.spell.attack;

import com.minnymin.zephyr.bukkit.projectile.BallProjectile;
import com.minnymin.zephyr.spell.CastResult;
import com.minnymin.zephyr.spell.Spell;
import com.minnymin.zephyr.spell.SpellContext;

public class ProjectileSpell extends Spell {

	public ProjectileSpell() {
		super("projectile", "Launch a projectile", 1, 10);
	}

	@Override
	public CastResult cast(SpellContext context) {
		new BallProjectile().launch(context.getUser());
		return CastResult.SUCCESS;
	}

}
