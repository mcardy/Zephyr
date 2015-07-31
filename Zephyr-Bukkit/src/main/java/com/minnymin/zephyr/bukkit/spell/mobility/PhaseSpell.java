package com.minnymin.zephyr.bukkit.spell.mobility;

import java.util.List;
import java.util.Set;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;

import com.minnymin.zephyr.api.aspect.Aspect;
import com.minnymin.zephyr.api.aspect.AspectList;
import com.minnymin.zephyr.api.spell.CastResult;
import com.minnymin.zephyr.api.spell.SpellContext;
import com.minnymin.zephyr.api.spell.SpellRecipe;
import com.minnymin.zephyr.api.spell.target.Targeted;
import com.minnymin.zephyr.api.spell.target.Targeted.TargetType;
import com.minnymin.zephyr.bukkit.conditions.TargetBlockAirCondition;
import com.minnymin.zephyr.bukkit.spell.BukkitTargetUtils;
import com.minnymin.zephyr.common.spell.BaseSpell;

@Targeted(type = TargetType.BLOCK, range = 5)
public class PhaseSpell extends BaseSpell {

	public PhaseSpell() {
		super("phase", "Phase through a wall", 4, 100);
		addCondition(new TargetBlockAirCondition("You cannot phase through air!"));
	}

	@Override
	public CastResult cast(SpellContext context) {
		Player player = context.<Player> getPlayer();
		Block target = context.<Block> getTarget().get();
		BlockFace direction = getClickedFace(player);
		Block phased = null;

		switch (direction) {
		case NORTH:
			phased = target.getRelative(0, 0, -1);
			break;
		case SOUTH:
			phased = target.getRelative(0, 0, 1);
			break;
		case EAST:
			phased = target.getRelative(1, 0, 0);
			break;
		case WEST:
			phased = target.getRelative(-1, 0, 0);
			break;
		case UP:
			phased = target.getRelative(0, +3, 0);
			break;
		case DOWN:
			phased = target.getRelative(0, -2, 0);
			break;
		default:
			phased = target;
			break;
		}

		if (!BukkitTargetUtils.getTransparent().contains(phased.getType())) {
			player.sendMessage(ChatColor.RED + "You cannot phase through that block");
			return CastResult.FAILURE;
		} else if (BukkitTargetUtils.getTransparent().contains(phased.getRelative(0, -1, 0).getType())) {
			Location loc = phased.getRelative(0, -1, 0).getLocation();
			loc.add(0.5, 0, 0.5);
			loc.setPitch(player.getLocation().getPitch());
			loc.setYaw(player.getLocation().getYaw());
			player.teleport(loc);
			return CastResult.SUCCESS;
		} else if (BukkitTargetUtils.getTransparent().contains(phased.getRelative(0, 1, 0).getType())) {
			Location loc = phased.getLocation();
			loc.add(0.5, 0, 0.5);
			loc.setPitch(player.getLocation().getPitch());
			loc.setYaw(player.getLocation().getYaw());
			player.teleport(loc);
			return CastResult.SUCCESS;
		} else {
			player.sendMessage(ChatColor.RED + "You cannot phase through that block");
			return CastResult.FAILURE;
		}

	}

	@Override
	public SpellRecipe getRecipe() {
		return new SpellRecipe(AspectList.build(Aspect.ENDER, 32).add(Aspect.MOVEMENT, 32), BlinkSpell.class);
	}

	private BlockFace getClickedFace(Player player) {
		List<Block> blocks = player.getLastTwoTargetBlocks((Set<Material>) null, 30);
		return blocks.get(0).getFace(blocks.get(1));
	}

}
