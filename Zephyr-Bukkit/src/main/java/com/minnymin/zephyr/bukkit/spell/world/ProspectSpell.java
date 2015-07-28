package com.minnymin.zephyr.bukkit.spell.world;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.bukkit.ChatColor;
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
import com.minnymin.zephyr.common.spell.BaseSpell;

@Targeted(type = TargetType.BLOCK, range = 5)
public class ProspectSpell extends BaseSpell {

	public ProspectSpell() {
		super("prospect", "Prospects the earth through the block in front of you", 5, 100);
	}

	@Override
	public CastResult cast(SpellContext context) {
		Player player = context.<Player> getPlayer();

		Block targetBlock = context.<Block> getTarget().get();
		if (targetBlock.getType() == Material.AIR) {
			player.sendMessage(ChatColor.RED + "You need to be looking at a block to prospect!");
			return CastResult.FAILURE;
		}

		Set<String> found = new HashSet<String>();
		BlockFace d = getClickedFace(player);
		Block start = null;
		
		switch (d) {
		case NORTH:
			start = targetBlock.getRelative(0, 0, -1);
			break;
		case SOUTH:
			start = targetBlock.getRelative(0, 0, 1);
			break;
		case EAST:
			start = targetBlock.getRelative(1, 0, 0);
			break;
		case WEST:
			start = targetBlock.getRelative(-1, 0, 0);
			break;
		case UP:
			start = targetBlock.getRelative(0, 1, 0);
			break;
		case DOWN:
			start = targetBlock.getRelative(0, -1, 0);
			break;
		default:
			start = targetBlock;
			break;
		}

		for (int x = -1; x <= 1; x++) {
			for (int y = -1; y <= 1; y++) {
				for (int z = -1; z <= 1; z++) {
					Material block = start.getRelative(x, y, z).getType();
					if (block == Material.GOLD_ORE) {
						found.add(ChatColor.GOLD + "Gold");
					}
					if (block == Material.IRON_ORE) {
						found.add(ChatColor.GRAY + "Iron");
					}
					if (block == Material.COAL_ORE) {
						found.add(ChatColor.DARK_GRAY + "Coal");
					}
					if (block == Material.REDSTONE_ORE) {
						found.add(ChatColor.RED + "Redstone");
					}
					if (block == Material.LAPIS_ORE) {
						found.add(ChatColor.BLUE + "Lapis Lazulite");
					}
					if (block == Material.DIAMOND_ORE) {
						found.add(ChatColor.AQUA + "Diamond");
					}
				}
			}
		}

		StringBuilder msg = null;
		if (found.size() == 0) {
			msg = new StringBuilder("none...");
		} else {
			for (String s : found) {
				if (msg == null) {
					msg = new StringBuilder(s);
				} else {
					msg.append(", " + s);
				}
			}
		}
		player.sendMessage("Found resources: " + msg.toString());

		return CastResult.SUCCESS;
	}

	@Override
	public SpellRecipe getRecipe() {
		return new SpellRecipe(AspectList.build(Aspect.STONE, 32).add(Aspect.METAL, 32).add(Aspect.VALUE, 32)
				.add(Aspect.EARTH, 32));
	}

	private BlockFace getClickedFace(Player player) {
		List<Block> blocks = player.getLastTwoTargetBlocks((Set<Material>) null, 30);
		return blocks.get(0).getFace(blocks.get(1));
	}

}
