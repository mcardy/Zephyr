package com.minnymin.zephyr.bukkit.projectile;

import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;

import com.minnymin.zephyr.api.Zephyr;
import com.minnymin.zephyr.api.projectile.Projectile;
import com.minnymin.zephyr.api.user.User;
import com.minnymin.zephyr.bukkit.nms.packet.PacketEntityDestroy;
import com.minnymin.zephyr.bukkit.util.ParticleEffects;
import com.minnymin.zephyr.bukkit.util.ParticleEffects.Particle;

public abstract class BallProjectile implements Projectile {

	private Snowball snowball;
	private Particle effect;
	
	public BallProjectile(Particle effect) {
		this.effect = effect;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <T> T getEntity() {
		try {
			return (T) this.snowball;
		} catch (ClassCastException ex) {
			return null;
		}
	}

	@Override
	public void launch(User user) {		
		this.snowball = user.<Player>getPlayer().launchProjectile(Snowball.class);
		PacketEntityDestroy packet = new PacketEntityDestroy(snowball.getEntityId());
		packet.send(user.<Player>getPlayer());
		for (Player p : user.<Player>getPlayer().getWorld().getPlayers()) {
			packet.send(p);
		}
		Zephyr.getProjectileManager().trackProjectile(this);
	}

	@Override
	public void onProjectileTick(Object obj) {		
		if (!(obj instanceof Location)) {
			return;
		}
		Location loc = (Location) obj;
		ParticleEffects.sendParticle(this.effect, loc, 10);
	}

	@Override
	public void onBlockHit(Object obj) {
		if (!(obj instanceof Location)) {
			return;
		}
		onBlockHit((Location)obj);
	}

	@Override
	public void onEntityHit(Object obj) {
		if (!(obj instanceof LivingEntity)) {
			return;
		}
		onEntityHit((LivingEntity)obj);
	}
	
	public abstract void onEntityHit(LivingEntity en);
	public abstract void onBlockHit(Location loc);

}
