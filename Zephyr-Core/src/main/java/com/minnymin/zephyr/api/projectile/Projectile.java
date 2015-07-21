package com.minnymin.zephyr.api.projectile;

import com.minnymin.zephyr.api.user.User;

/**
 * Represents a projectile that can be launched by a {@link User}
 *
 * @author minnymin3
 */
public interface Projectile {

	/**
	 * Gets the entity of this projectile
	 * @return The entity of this projectile or null if T is not of the entitie's type
	 */
	public <T> T getEntity();
	
	/**
	 * Launches this projectile and should begin tracking with {@link ProjectileManager}
	 * @param user The user to launch this projectile from
	 */
	public void launch(User user);
	
	/**
	 * Called by {@link ProjectileManager} when the entity hits a block
	 * @param location The location object for the platform
	 */
	public void onBlockHit(Object location);
	
	/**
	 * Called by {@link ProjectileManager} when the entity hits an entity
	 * @param entity The entity object for the platform
	 */
	public void onEntityHit(Object entity);
	
	/**
	 * Called by {@link ProjectileManager} when the entity ticks
	 * @param location The location object for the platform
	 */
	public void onProjectileTick(Object location);
	
}
