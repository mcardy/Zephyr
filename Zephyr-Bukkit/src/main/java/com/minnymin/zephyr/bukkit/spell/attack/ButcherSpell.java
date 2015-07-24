package com.minnymin.zephyr.bukkit.spell.attack;

import java.util.Collection;

import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;

import com.minnymin.zephyr.api.aspect.Aspect;
import com.minnymin.zephyr.api.aspect.AspectList;
import com.minnymin.zephyr.api.spell.CastResult;
import com.minnymin.zephyr.api.spell.SpellContext;
import com.minnymin.zephyr.api.spell.SpellRecipe;
import com.minnymin.zephyr.api.spell.target.Targeted;
import com.minnymin.zephyr.api.spell.target.Targeted.TargetType;
import com.minnymin.zephyr.common.spell.BaseSpell;

@Targeted(type = TargetType.LIVING_AREA)
public class ButcherSpell extends BaseSpell {

	public ButcherSpell() {
		super("butcher", "Butchers all enemies around you", 1, 50);
	}

	@Override
	public CastResult cast(SpellContext context) {
		Collection<Monster> monsters = context.<Collection<Monster>>getTarget().get();
		for (Monster m : monsters) {
			m.damage(m.getHealth(), context.<Player>getPlayer());;
		}
		return CastResult.SUCCESS;
	}
	
	@Override
	public SpellRecipe getRecipe() {
		return new SpellRecipe(AspectList.build(Aspect.BEAST, 8).add(Aspect.WEAPON, 8));
	}
	
}
