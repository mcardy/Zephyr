package com.minnymin.zephyr.bukkit.spell.mobility;

import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import com.minnymin.zephyr.api.aspect.Aspect;
import com.minnymin.zephyr.api.aspect.AspectList;
import com.minnymin.zephyr.api.spell.CastResult;
import com.minnymin.zephyr.api.spell.SpellContext;
import com.minnymin.zephyr.api.spell.SpellRecipe;
import com.minnymin.zephyr.api.spell.target.Targeted;
import com.minnymin.zephyr.api.spell.target.Targeted.TargetType;
import com.minnymin.zephyr.bukkit.conditions.TargetBlockCondition;
import com.minnymin.zephyr.bukkit.util.ParticleEffects;
import com.minnymin.zephyr.bukkit.util.ParticleEffects.Particle;
import com.minnymin.zephyr.common.spell.BaseSpell;

@Targeted(type = TargetType.BLOCK, range = 100)
public class BlinkSpell extends BaseSpell {

	public BlinkSpell() {
		super("blink", "Blink far away", 2, 80);
		addCondition(new TargetBlockCondition(false, "You cannot blink to air!", Material.AIR));
	}

	@Override
	public CastResult cast(SpellContext context) {
		Player player = context.<Player> getPlayer();
		Block block = context.<Block> getTarget().get();

		Location bottom = block.getLocation().add(0, 1, 0);
		Location top = block.getLocation().add(0, 2, 0);
		Block bottomBlock = bottom.getBlock();
		Block topBlock = top.getBlock();
		
		if (bottomBlock.getType() == Material.AIR && topBlock.getType() == Material.AIR) {
			Location previous = player.getLocation();
			Location teleport = block.getLocation().add(0.5, 1.25, 0.5);
			teleport.setPitch(previous.getPitch());
			teleport.setYaw(previous.getYaw());
			ParticleEffects.sendParticle(Particle.ENDER, previous, 1, 1, 1, 1, 16);
			player.teleport(teleport);
			player.getWorld().playEffect(teleport, Effect.ENDER_SIGNAL, 0);
			return CastResult.SUCCESS;
		} else  {
			player.sendMessage(ChatColor.RED + "You cannot blink there!");
			return CastResult.FAILURE;
		}
	}

	@Override
	public SpellRecipe getRecipe() {
		return new SpellRecipe(AspectList.build(Aspect.ENDER, 16).add(Aspect.MOVEMENT, 16));
	}

}
