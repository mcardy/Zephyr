package com.minnymin.zephyr;

import com.minnymin.zephyr.projectile.ProjectileHandler;
import com.minnymin.zephyr.spell.SpellManager;
import com.minnymin.zephyr.user.UserManager;

public interface ZephyrAPI {

	public ProjectileHandler<?> getProjectileHandler();
	public SpellManager getSpellManager();
	public UserManager getUserManager();
	
}
