package com.minnymin.zephyr.bukkit.projectile;

import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;

import com.minnymin.zephyr.api.Zephyr;
import com.minnymin.zephyr.api.projectile.Projectile;
import com.minnymin.zephyr.api.user.User;

public class ArrowProjectile implements Projectile {

	private Arrow arrow;
	
	@SuppressWarnings("unchecked")
	@Override
	public <T> T getEntity() {
		try {
			return (T) this.arrow;
		} catch (ClassCastException ex) {
			return null;
		}
	}

	@Override
	public void launch(User user) {
		this.arrow = user.<Player>getPlayer().launchProjectile(Arrow.class);
		Zephyr.getProjectileManager().trackProjectile(this);
	}

	@Override
	public void onBlockHit(Object obj) {
		this.arrow.remove();
	}

	@Override
	public void onEntityHit(Object entity) {
		this.arrow.remove();
	}

	@Override
	public void onProjectileTick(Object location) {		
	}

}
