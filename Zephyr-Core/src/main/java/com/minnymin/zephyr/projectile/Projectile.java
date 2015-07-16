package com.minnymin.zephyr.projectile;

import com.minnymin.zephyr.user.User;

public interface Projectile {

	public <T> T getEntity();
	public void launch(User user);
	public void onProjectileTick(Object location);
	public void onBlockHit(Object location);
	public void onEntityHit(Object entity);
	
}
