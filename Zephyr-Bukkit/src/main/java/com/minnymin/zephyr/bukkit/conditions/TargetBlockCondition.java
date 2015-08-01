package com.minnymin.zephyr.bukkit.conditions;

import java.util.Arrays;
import java.util.Collection;

import org.bukkit.Material;
import org.bukkit.block.Block;

import com.minnymin.zephyr.api.spell.CastCondition;
import com.minnymin.zephyr.api.spell.SpellContext;

public class TargetBlockCondition implements CastCondition {

	private String message;
	private boolean doesEqual;
	private Collection<Material> materials;
	
	public TargetBlockCondition(boolean doesEqual, Material... materials) {
		this(doesEqual, Arrays.asList(materials));
	}
	
	public TargetBlockCondition(boolean doesEqual, String message, Material... materials) {
		this(doesEqual, message, Arrays.asList(materials));
	}
	
	public TargetBlockCondition(boolean doesEqual, Collection<Material> materials) {
		this(doesEqual, "You cannot target that block!", materials);
	}
	
	public TargetBlockCondition(boolean doesEqual, String message, Collection<Material> materials) {
		this.message = message;
		this.doesEqual = doesEqual;
		this.materials = materials;
	}
	
	@Override
	public boolean canCast(SpellContext context) {
		if (doesEqual) {
			return context.hasTarget() && (materials.contains(context.<Block>getTarget().get().getType()));
		} else {
			return context.hasTarget() && !(materials.contains(context.<Block>getTarget().get().getType()));
		}
	}

	@Override
	public String getMessage(SpellContext context) {
		return this.message;
	}

}
