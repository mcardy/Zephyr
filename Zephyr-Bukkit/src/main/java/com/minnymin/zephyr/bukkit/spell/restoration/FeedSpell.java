package com.minnymin.zephyr.bukkit.spell.restoration;

import org.bukkit.entity.Player;

import com.minnymin.zephyr.api.aspect.Aspect;
import com.minnymin.zephyr.api.aspect.AspectList;
import com.minnymin.zephyr.api.spell.CastResult;
import com.minnymin.zephyr.api.spell.SpellContext;
import com.minnymin.zephyr.api.spell.SpellRecipe;
import com.minnymin.zephyr.common.spell.BaseSpell;

public class FeedSpell extends BaseSpell {

	public FeedSpell() {
		super("feed", "Feed yourself!", 1, 20);
	}

	@Override
	public CastResult cast(SpellContext context) {
		Player player = context.<Player>getPlayer();
		player.setFoodLevel(player.getFoodLevel() + context.getPower() * 2);
		return CastResult.SUCCESS;
	}

	@Override
	public SpellRecipe getRecipe() {
		return new SpellRecipe(AspectList.build(Aspect.LIFE, 8).add(Aspect.BEAST, 8));
	}

}
