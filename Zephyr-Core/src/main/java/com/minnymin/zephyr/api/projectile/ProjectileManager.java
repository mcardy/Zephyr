package com.minnymin.zephyr.api.projectile;

import com.minnymin.zephyr.api.Manager;

/**
 * Represents a manager used to track {@link Projectile} and call a projectile's
 * tick and hit methods
 *
 * @author minnymin3
 */
public interface ProjectileManager extends Manager {

	/**
	 * Start tracking the specified projectile
	 * @param projectile The projectile to track
	 */
	public void trackProjectile(Projectile projectile);

}