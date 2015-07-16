package com.minnymin.zephyr.sponge.projectile;

import org.spongepowered.api.entity.Entity;
import org.spongepowered.api.entity.EntityTypes;
import org.spongepowered.api.entity.player.Player;
import org.spongepowered.api.world.World;

import com.minnymin.zephyr.Zephyr;
import com.minnymin.zephyr.projectile.Projectile;
import com.minnymin.zephyr.user.User;

public class TestProjectile implements Projectile {

	private Entity en;

	@SuppressWarnings("unchecked")
	@Override
	public <T> T getEntity() {
		try {
			return (T) en;
		} catch (ClassCastException ex) {
			return null;
		}
	}

	@Override
	public void launch(User user) {
		Player player = user.<Player> getPlayerObject();
		World world = player.getWorld();
		// Best I can do with Sponge as it is now
		en = world.createEntity(EntityTypes.ARROW, player.getLocation().getPosition()).get();
		world.spawnEntity(en);
		Zephyr.getProjectileHandler().trackProjectile(this);
	}

	@Override
	public void onProjectileTick(Object location) {
		System.out.println("TICK");
	}

	@Override
	public void onBlockHit(Object location) {
	}

	@Override
	public void onEntityHit(Object entity) {
	}

}
