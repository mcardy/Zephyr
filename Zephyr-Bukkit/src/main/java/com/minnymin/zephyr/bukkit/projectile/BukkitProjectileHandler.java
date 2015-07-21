package com.minnymin.zephyr.bukkit.projectile;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.ProjectileHitEvent;

import com.minnymin.zephyr.bukkit.ZephyrPlugin;
import com.minnymin.zephyr.projectile.Projectile;
import com.minnymin.zephyr.projectile.ProjectileHandler;

public class BukkitProjectileHandler extends ProjectileHandler<Entity> implements Listener {

	@Override
	public void onEnable() {
		super.onEnable();
		Bukkit.getPluginManager().registerEvents(this, ZephyrPlugin.getInstance());
	}
	
	@Override
	public void trackTask(final Projectile projectile) {
		Bukkit.getScheduler().runTask(ZephyrPlugin.getInstance(), new Runnable() {
			private final int maxTicks = 60;
			private int ticks = 0;
			
			@Override
			public void run() {
				if (tracking.containsKey(projectile.getEntity()) && ticks < maxTicks) {
					ticks++;
					Location loc = projectile.<Entity>getEntity().getLocation();
					projectile.onProjectileTick(loc);
					Bukkit.getScheduler().runTaskLater(ZephyrPlugin.getInstance(), this, 1);
				}
			}
		});
	}
	
	@EventHandler
	public void onProjectileHitBlock(ProjectileHitEvent event) {
		this.onBlockHit(event.getEntity(), event.getEntity().getLocation());
	}
	
	@EventHandler
	public void onProjectileHitEntity(EntityDamageByEntityEvent event) {
		this.onEntityHit(event.getDamager(), event.getEntity());
	}

}
