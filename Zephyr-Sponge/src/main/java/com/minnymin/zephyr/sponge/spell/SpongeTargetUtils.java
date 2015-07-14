package com.minnymin.zephyr.sponge.spell;

import java.util.Collection;
import java.util.HashSet;

import org.spongepowered.api.block.BlockTypes;
import org.spongepowered.api.entity.Entity;
import org.spongepowered.api.entity.living.Living;
import org.spongepowered.api.entity.living.monster.Monster;
import org.spongepowered.api.entity.player.Player;
import org.spongepowered.api.util.blockray.BlockRay;
import org.spongepowered.api.util.blockray.BlockRayHit;
import org.spongepowered.api.world.Location;

import com.google.common.base.Predicate;

final class SpongeTargetUtils {

	protected static Player getTargetPlayer(Player player, int range) {
		return SpongeTargetUtils.<Player> getTarget(player, range, getNearbyPlayers(player, range));
	}
	
	protected static Monster getTargetMonster(Player player, int range) {
		return SpongeTargetUtils.<Monster> getTarget(player, range, getNearbyMonsters(player, range));
	}

	protected static Living getTargetLiving(Player player, int range) {
		return SpongeTargetUtils.<Living> getTarget(player, range, getNearbyLivings(player, range));
	}

	protected static Entity getTargetEntity(Player player, int range) {
		return SpongeTargetUtils.<Entity> getTarget(player, range, getNearbyEntities(player, range));
	}

	protected static Location getTargetBlock(Player player, int range) {
		BlockRay iterator = BlockRay.from(player).blockLimit(range).filter(new Predicate<BlockRayHit>() {
			@Override
			public boolean apply(BlockRayHit hit) {
				return hit.getExtent().getBlockType(hit.getBlockX(), hit.getBlockY(), hit.getBlockZ()) != BlockTypes.AIR;
			}
		}).build();
		return iterator.next().getLocation();
	}

	protected static <T extends Entity> T getTarget(Player player, int range, Collection<T> entities) {
		BlockRay iterator = BlockRay.from(player).blockLimit(range).build();
		while (iterator.hasNext()) {
			BlockRayHit block = iterator.next();
			for (T entity : entities) {
				int accuracy = 2;
				for (int offX = -accuracy; offX < accuracy; offX++) {
					for (int offY = -accuracy; offY < accuracy; offY++) {
						for (int offZ = -accuracy; offZ < accuracy; offZ++) {
							if (entity.getLocation().getBlockPosition().add(offX, offY, offZ)
									.equals(block.getLocation().getBlockPosition())) {
								return entity;
							}
						}
					}
				}
			}
		}
		return null;
	}

	protected static Collection<Player> getNearbyPlayers(Player player, int radius) {
		Collection<Entity> entities = getNearbyEntities(player, radius);
		Collection<Player> players = new HashSet<Player>();

		for (Entity en : entities) {
			if (en instanceof Player) {
				players.add((Player) en);
			}
		}
		return players;
	}
	
	protected static Collection<Monster> getNearbyMonsters(Player player, int radius) {
		Collection<Entity> entities = getNearbyEntities(player, radius);
		Collection<Monster> monsters = new HashSet<Monster>();

		for (Entity en : entities) {
			if (en instanceof Monster) {
				monsters.add((Monster) en);
			}
		}
		return monsters;
	}

	protected static Collection<Living> getNearbyLivings(Player player, int radius) {
		Collection<Entity> entities = getNearbyEntities(player, radius);
		Collection<Living> livings = new HashSet<Living>();

		for (Entity en : entities) {
			if (en instanceof Living) {
				livings.add((Living) en);
			}
		}
		return livings;
	}

	// TODO Make more efficient when possible
	protected static Collection<Entity> getNearbyEntities(Player player, int radius) {
		final Player p = player;
		final Location loc = player.getLocation();
		final int range = radius;

		Collection<Entity> entities = player.getWorld().getEntities(new Predicate<Entity>() {
			@Override
			public boolean apply(Entity en) {
				if (en != p && en.getLocation().getBlockPosition().distance(loc.getBlockPosition()) < range) {
					return true;
				}
				return false;
			}
		});

		return entities;
	}

}
