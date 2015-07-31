package com.minnymin.zephyr.bukkit.spell.world;

import java.util.List;
import java.util.Set;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.minnymin.zephyr.api.aspect.Aspect;
import com.minnymin.zephyr.api.aspect.AspectList;
import com.minnymin.zephyr.api.spell.CastResult;
import com.minnymin.zephyr.api.spell.SpellContext;
import com.minnymin.zephyr.api.spell.SpellRecipe;
import com.minnymin.zephyr.api.spell.target.Targeted;
import com.minnymin.zephyr.api.spell.target.Targeted.TargetType;
import com.minnymin.zephyr.common.spell.BaseSpell;

@Targeted(type = TargetType.BLOCK)
public class MineSpell extends BaseSpell {

	public MineSpell() {
		super("mine", "Mines out a 3x3 square around your target block", 4, 25);
	}

	@Override
	public CastResult cast(SpellContext context) {
		Player player = context.<Player> getPlayer();

		Block target = context.<Block> getTarget().get();
		if (target.getType() == Material.AIR) {
			context.getUser().sendMessage(ChatColor.RED + "You cannot mine that block");
			return CastResult.FAILURE;
		}
		BlockFace d = getClickedFace(player);
		
		if (d == BlockFace.NORTH || d == BlockFace.SOUTH) {
			for (int x = -1; x <= 1; x++) {
				for (int y = -1; y <= 1; y++) {
					target.getRelative(x, y, 0).breakNaturally(new ItemStack(Material.IRON_PICKAXE));
				}
			}
		} else if (d == BlockFace.EAST || d == BlockFace.WEST) {
			for (int y = -1; y <= 1; y++) {
				for (int z = -1; z <= 1; z++) {
					target.getRelative(0, y, z).breakNaturally(new ItemStack(Material.IRON_PICKAXE));
				}
			}
		} else if (d == BlockFace.UP || d == BlockFace.DOWN) {
			for (int x = -1; x <= 1; x++) {
				for (int z = -1; z <= 1; z++) {
					target.getRelative(x, 0, z).breakNaturally(new ItemStack(Material.IRON_PICKAXE));
				}
			}
		}
		
		return CastResult.SUCCESS;
	}

	@Override
	public SpellRecipe getRecipe() {
		return new SpellRecipe(AspectList.build(Aspect.STONE, 32).add(Aspect.EARTH, 32).add(Aspect.TOOL, 32), DigSpell.class);
	}
	
	private BlockFace getClickedFace(Player player) {
		List<Block> blocks = player.getLastTwoTargetBlocks((Set<Material>) null, 30);
		return blocks.get(0).getFace(blocks.get(1));
	}

}
