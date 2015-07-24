package com.minnymin.zephyr.bukkit.spell;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;
import org.bukkit.util.BlockIterator;

public final class BukkitTargetUtils {

	public static Player getTargetPlayer(Player player, int range) {
		return BukkitTargetUtils.<Player>getTarget(player, range, getNearbyPlayers(player, range));
	}
	
	public static Monster getTargetMonster(Player player, int range) {
		return BukkitTargetUtils.<Monster>getTarget(player, range, getNearbyMonsters(player, range));
	}
	
	public static LivingEntity getTargetLiving(Player player, int range) {
		return BukkitTargetUtils.<LivingEntity>getTarget(player, range, getNearbyLivings(player, range));
	}
	
	public static Entity getTargetEntity(Player player, int range) {
		return BukkitTargetUtils.<Entity>getTarget(player, range, getNearbyEntities(player, range));
	}
	
	public static <T extends Entity> T getTarget(Player player, int range, Collection<T> entities) {
		BlockIterator iterator = new BlockIterator(player);
		while (iterator.hasNext()) {
			Block block = iterator.next();
			for (T entity : entities) {
				int accuracy = 2;
				for (int offX = -accuracy; offX < accuracy; offX++) {
					for (int offY = -accuracy; offY < accuracy; offY++) {
						for (int offZ = -accuracy; offZ < accuracy; offZ++) {
							if (entity.getLocation().getBlock().equals(block)) {
								return entity;
							}
						}
					}
				}
			}
		}
		return null;
	}
	
	public static Collection<Player> getNearbyPlayers(Player player, int radius) {
		Collection<Player> col = new HashSet<Player>();
		
		for (Entity en : getNearbyEntities(player, radius)) {
			if (en instanceof Player) {
				col.add((Player) en);
			}
		}
		
		return col;
	}
	
	public static Collection<Monster> getNearbyMonsters(Player player, int radius) {
		Collection<Monster> col = new HashSet<Monster>();
		
		for (Entity en : getNearbyEntities(player, radius)) {
			if (en instanceof Monster) {
				col.add((Monster) en);
			}
		}
		
		return col;
	}
	
	public static Collection<LivingEntity> getNearbyLivings(Player player, int radius) {
		Collection<LivingEntity> col = new HashSet<LivingEntity>();
		
		for (Entity en : getNearbyEntities(player, radius)) {
			if (en instanceof LivingEntity) {
				col.add((LivingEntity) en);
			}
		}
		
		return col;
	}
	
	public static Collection<Entity> getNearbyEntities(Player player, int radius) {
		return player.getNearbyEntities(radius, radius, radius);
	}
	
	public static Block getTargetBlock(Player player, int range) {
		Set<Material> set = new HashSet<Material>();
		set.add(Material.AIR);
		return player.getTargetBlock(set, range);
	}
	
}
