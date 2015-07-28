package com.minnymin.zephyr.bukkit.spell.restoration;

import org.bukkit.entity.Player;

import com.minnymin.zephyr.api.aspect.Aspect;
import com.minnymin.zephyr.api.aspect.AspectList;
import com.minnymin.zephyr.api.spell.CastResult;
import com.minnymin.zephyr.api.spell.SpellContext;
import com.minnymin.zephyr.api.spell.SpellRecipe;
import com.minnymin.zephyr.api.spell.target.Targeted;
import com.minnymin.zephyr.api.spell.target.Targeted.TargetType;
import com.minnymin.zephyr.common.spell.BaseSpell;

@Targeted(type = TargetType.PLAYER)
public class HealerSpell extends BaseSpell {

	public HealerSpell() {
		super("healer", "Heals your target", 2, 20);
	}

	@Override
	public CastResult cast(SpellContext context) {
		Player player = context.<Player>getTarget().get();
		player.setHealth(player.getHealth() + context.getPower() * 2);
		return CastResult.SUCCESS;
	}

	@Override
	public SpellRecipe getRecipe() {
		return new SpellRecipe(AspectList.build(Aspect.LIFE, 16).add(Aspect.BEAST, 16));
	}

}
