package com.minnymin.zephyr.bukkit.spell.creation;

import org.bukkit.entity.Player;

import com.minnymin.zephyr.api.aspect.Aspect;
import com.minnymin.zephyr.api.aspect.AspectList;
import com.minnymin.zephyr.api.spell.CastResult;
import com.minnymin.zephyr.api.spell.SpellContext;
import com.minnymin.zephyr.api.spell.SpellRecipe;
import com.minnymin.zephyr.common.spell.BaseSpell;

public class EnderchestSpell extends BaseSpell {

	public EnderchestSpell() {
		super("enderchest", "Opens your enderchest", 3, 300);
	}

	@Override
	public CastResult cast(SpellContext context) {
		Player player = context.<Player>getPlayer();
		player.openInventory(player.getEnderChest());
		return CastResult.SUCCESS;
	}

	@Override
	public SpellRecipe getRecipe() {
		return new SpellRecipe(AspectList.build(Aspect.ENDER, 16).add(Aspect.VOID, 32));
	}

}
