package com.minnymin.zephyr.common.projectile;

import java.util.Map;
import java.util.WeakHashMap;

import com.minnymin.zephyr.api.projectile.Projectile;
import com.minnymin.zephyr.api.projectile.ProjectileManager;

/**
 * Implementations of ProjectileHandler should track entity hit events and
 * should tick entities as they move with a runnable
 * 
 * @author minnymin3
 * 
 * @param <T> Platform's entity type
 */
public abstract class AbstractProjectileManager<T> implements ProjectileManager {

	protected Map<T, Projectile> tracking = new WeakHashMap<T, Projectile>();

	@Override
	public void onEnable() {
	}
	
	@Override
	public void onDisable() {
	}

	@Override
	public void trackProjectile(Projectile projectile) {
		this.tracking.put(projectile.<T> getEntity(), projectile);
		this.trackTask(projectile);
	}

	protected void onEntityHit(T entity, T target) {
		if (this.tracking.containsKey(entity)) {
			Projectile proj = this.tracking.get(entity);
			proj.onEntityHit(target);
			this.tracking.remove(entity);
		}
	}
	
	protected void onBlockHit(T entity, Object location) {
		if (this.tracking.containsKey(entity)) {
			Projectile proj = this.tracking.get(entity);
			proj.onBlockHit(location);
			this.tracking.remove(entity);
		}
	}
	
	protected abstract void trackTask(Projectile projectile);

}
