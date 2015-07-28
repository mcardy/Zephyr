package com.minnymin.zephyr.bukkit.spell.attack;

import com.minnymin.zephyr.api.aspect.Aspect;
import com.minnymin.zephyr.api.aspect.AspectList;
import com.minnymin.zephyr.api.spell.CastResult;
import com.minnymin.zephyr.api.spell.SpellContext;
import com.minnymin.zephyr.api.spell.SpellRecipe;
import com.minnymin.zephyr.bukkit.projectile.ArrowProjectile;
import com.minnymin.zephyr.common.spell.BaseContinuousSpell;

public class ArrowRainSpell extends BaseContinuousSpell {

	public ArrowRainSpell() {
		// level 5, 100 mana initial, 20 exp reward, 5 ticks per update, 20 mana per update
		super("arrowrain", "Rains down a continuous stream of arrows", 5, 100, 20, 4, 20);
	}

	@Override
	public CastResult update(SpellContext context) {
		new ArrowProjectile().launch(context.getUser());
		return CastResult.SUCCESS;
	}

	@Override
	public SpellRecipe getRecipe() {
		return new SpellRecipe(AspectList.build(Aspect.WEAPON, 64).add(Aspect.WIND, 64), ArrowStormSpell.class);
	}

}
