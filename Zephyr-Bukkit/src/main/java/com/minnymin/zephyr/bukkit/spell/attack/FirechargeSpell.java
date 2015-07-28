package com.minnymin.zephyr.bukkit.spell.attack;

import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;

import com.minnymin.zephyr.api.aspect.Aspect;
import com.minnymin.zephyr.api.aspect.AspectList;
import com.minnymin.zephyr.api.spell.CastResult;
import com.minnymin.zephyr.api.spell.SpellContext;
import com.minnymin.zephyr.api.spell.SpellRecipe;
import com.minnymin.zephyr.common.spell.BaseSpell;

public class FirechargeSpell extends BaseSpell {

	public FirechargeSpell() {
		super("firecharge", "Launches a firecharge", 1, 50);
	}
	
	@Override
	public CastResult cast(SpellContext context) {
		context.<Player>getPlayer().launchProjectile(Fireball.class);
		return CastResult.SUCCESS;
	}

	@Override
	public SpellRecipe getRecipe() {
		return new SpellRecipe(AspectList.build(Aspect.FIRE, 16).add(Aspect.DESTRUCTION, 16), FireballSpell.class);
	}

}
