package com.minnymin.zephyr.bukkit.spell.world;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;

import com.minnymin.zephyr.api.aspect.Aspect;
import com.minnymin.zephyr.api.aspect.AspectList;
import com.minnymin.zephyr.api.spell.CastResult;
import com.minnymin.zephyr.api.spell.SpellContext;
import com.minnymin.zephyr.api.spell.SpellRecipe;
import com.minnymin.zephyr.api.spell.target.Targeted;
import com.minnymin.zephyr.api.spell.target.Targeted.TargetType;
import com.minnymin.zephyr.bukkit.conditions.TargetBlockAirCondition;
import com.minnymin.zephyr.common.spell.BaseSpell;

@Targeted(type = TargetType.BLOCK)
public class DigSpell extends BaseSpell {

	public DigSpell() {
		super("dig", "Digs your target block", 1, 5);
		addCondition(new TargetBlockAirCondition("You cannot dig air!"));
	}

	@Override
	public CastResult cast(SpellContext context) {
		Block block = context.<Block>getTarget().get();
		block.breakNaturally(new ItemStack(Material.IRON_PICKAXE));
		return CastResult.SUCCESS;
	}

	@Override
	public SpellRecipe getRecipe() {
		return new SpellRecipe(AspectList.build(Aspect.STONE, 8).add(Aspect.EARTH, 8).add(Aspect.TOOL, 8));
	}

}
