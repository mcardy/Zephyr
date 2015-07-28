package com.minnymin.zephyr.bukkit.spell.attack;

import org.bukkit.entity.Arrow;
import org.bukkit.entity.LivingEntity;

import com.minnymin.zephyr.api.aspect.Aspect;
import com.minnymin.zephyr.api.aspect.AspectList;
import com.minnymin.zephyr.api.projectile.Projectile;
import com.minnymin.zephyr.api.spell.CastResult;
import com.minnymin.zephyr.api.spell.SpellContext;
import com.minnymin.zephyr.api.spell.SpellRecipe;
import com.minnymin.zephyr.api.spell.target.Targeted;
import com.minnymin.zephyr.api.spell.target.Targeted.TargetType;
import com.minnymin.zephyr.bukkit.projectile.HomingProjectile;
import com.minnymin.zephyr.common.spell.BaseSpell;

@Targeted(type = TargetType.LIVING)
public class HomingArrowSpell extends BaseSpell {

	public HomingArrowSpell() {
		super("homingarrow", "Launches an arrow that tracks your target", 2, 40);
	}

	@Override
	public CastResult cast(SpellContext context) {
		Projectile proj = new HomingProjectile(Arrow.class, context.<LivingEntity> getTarget().get());
		proj.launch(context.getUser());
		return CastResult.SUCCESS;
	}

	@Override
	public SpellRecipe getRecipe() {
		return new SpellRecipe(AspectList.build(Aspect.WEAPON, 16).add(Aspect.WIND, 8).add(Aspect.MOVEMENT, 8),
				ArrowSpell.class);
	}

}
