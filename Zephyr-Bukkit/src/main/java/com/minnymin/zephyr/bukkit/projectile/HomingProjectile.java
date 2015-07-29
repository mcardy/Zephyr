package com.minnymin.zephyr.bukkit.projectile;

import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import com.minnymin.zephyr.api.Zephyr;
import com.minnymin.zephyr.api.projectile.Projectile;
import com.minnymin.zephyr.api.user.User;

public class HomingProjectile implements Projectile {

	private Class<? extends org.bukkit.entity.Projectile> projectile;
	private Entity entity;
	private LivingEntity target;
	
	public HomingProjectile(Class<? extends org.bukkit.entity.Projectile> projectileClass, LivingEntity target) {
		this.projectile = projectileClass;
		this.target = target;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <T> T getEntity() {
		try {
			return (T) entity;
		} catch (ClassCastException ex) {
			return null;
		}
	}

	@Override
	public void launch(User user) {	
		this.entity = user.<Player>getPlayer().launchProjectile(this.projectile);
		Zephyr.getProjectileManager().trackProjectile(this);
	}

	@Override
	public void onBlockHit(Object location) {		
	}

	@Override
	public void onEntityHit(Object entity) {		
	}

	@Override
	public void onProjectileTick(Object location) {	
		if (target.isDead()) {
			return;
		}
		double speed = this.entity.getVelocity().length() * 0.9D + 0.14D;
		Vector velocity = null;
		Vector direction = this.entity.getVelocity().clone().normalize();
		Vector targetDirection = this.target.getLocation().clone().add(new Vector(0, 0.5D, 0))
				.subtract(this.entity.getLocation()).toVector();
		Vector targetDirectionNorm = targetDirection.clone().normalize();

		double angle = direction.angle(targetDirectionNorm);

		if (angle < 0.12D) {
			velocity = direction.clone().multiply(speed);
		} else {
			velocity = direction.clone().multiply((angle - 0.12D) / angle)
					.add(targetDirectionNorm.clone().multiply(0.12D / angle)).normalize().multiply(speed);
		}
		this.entity.setVelocity(velocity.add(new Vector(0.0D, 0.03D, 0.0D)));
	}

	
	
}
