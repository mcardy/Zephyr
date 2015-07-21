package com.minnymin.zephyr.sponge.projectile;

import org.spongepowered.api.entity.Entity;
import org.spongepowered.api.world.Location;

import com.minnymin.zephyr.Zephyr;
import com.minnymin.zephyr.projectile.Projectile;
import com.minnymin.zephyr.projectile.ProjectileHandler;
import com.minnymin.zephyr.sponge.ZephyrPlugin;

public class SpongeProjectileHandler extends ProjectileHandler<Entity> {

	@Override
	public void trackTask(final Projectile projectile) {
		Runnable task = new Runnable() {
			private final int maxTicks = 60;
			private int ticks = 0;
			
			@Override
			public void run() {
				if (tracking.containsKey(projectile.getEntity()) && ticks < maxTicks) {
					ticks++;
					Location loc = projectile.<Entity>getEntity().getLocation();
					projectile.onProjectileTick(loc);
					ZephyrPlugin.getGame().getScheduler().getTaskBuilder().execute(this)
					.name("zephyr:projectiletick").submit(Zephyr.getAPI());	
				}
			}
		};
		ZephyrPlugin.getGame().getScheduler().getTaskBuilder().interval(1).execute(task)
		.name("zephyr:projectiletick").submit(Zephyr.getAPI());	
	}

}
