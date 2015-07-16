package com.minnymin.zephyr.projectile;

import java.util.Map;
import java.util.WeakHashMap;

/**
 * Implementations of ProjectileHandler should track entity hit events and
 * should tick entities as they move with a runnable
 * 
 * @author minnymin3
 * 
 * @param <T> Platform's entity type
 */
public abstract class ProjectileHandler<T> {

	protected Map<T, Projectile> tracking;

	public ProjectileHandler() {
		tracking = new WeakHashMap<T, Projectile>();
	}

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
	
	public abstract void trackTask(Projectile projectile);

}
