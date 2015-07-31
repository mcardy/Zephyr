package com.minnymin.zephyr.bukkit.conditions;

import org.bukkit.Material;
import org.bukkit.block.Block;

import com.minnymin.zephyr.api.spell.CastCondition;
import com.minnymin.zephyr.api.spell.SpellContext;

public class TargetBlockAirCondition implements CastCondition {

	private String message;
	
	public TargetBlockAirCondition() {
		this("You cannot target that block!");
	}
	
	public TargetBlockAirCondition(String message) {
		this.message = message;
	}
	
	@Override
	public boolean canCast(SpellContext context) {
		return context.hasTarget() && (context.<Block>getTarget().get().getType() != Material.AIR);
	}

	@Override
	public String getMessage(SpellContext context) {
		return this.message;
	}

}
