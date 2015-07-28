package com.minnymin.zephyr.bukkit.spell.attack;

import org.bukkit.Bukkit;

import com.minnymin.zephyr.api.aspect.Aspect;
import com.minnymin.zephyr.api.aspect.AspectList;
import com.minnymin.zephyr.api.spell.CastResult;
import com.minnymin.zephyr.api.spell.SpellContext;
import com.minnymin.zephyr.api.spell.SpellRecipe;
import com.minnymin.zephyr.api.user.User;
import com.minnymin.zephyr.bukkit.ZephyrPlugin;
import com.minnymin.zephyr.bukkit.projectile.ArrowProjectile;
import com.minnymin.zephyr.common.spell.BaseSpell;

public class ArrowStormSpell extends BaseSpell {

	public ArrowStormSpell() {
		super("arrowstorm", "Shoots a storm of arrows", 3, 120);
	}

	@Override
	public CastResult cast(SpellContext context) {
		final User user = context.getUser();
		for (int i = 0; i < 5 * context.getPower(); i++) {
			Bukkit.getScheduler().runTaskLater(ZephyrPlugin.getInstance(), new Runnable() {
				@Override
				public void run() {
					new ArrowProjectile().launch(user);
				}
			}, i);
		}
		return CastResult.SUCCESS;
	}

	@Override
	public SpellRecipe getRecipe() {
		return new SpellRecipe(AspectList.build(Aspect.WEAPON, 32).add(Aspect.WIND, 32), ArrowSpell.class);
	}

}
