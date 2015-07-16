package com.minnymin.zephyr.bukkit.projectile;

import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;

import com.minnymin.zephyr.Zephyr;
import com.minnymin.zephyr.bukkit.nms.packet.PacketEntityDestroy;
import com.minnymin.zephyr.bukkit.util.ParticleEffects;
import com.minnymin.zephyr.bukkit.util.ParticleEffects.Particle;
import com.minnymin.zephyr.projectile.Projectile;
import com.minnymin.zephyr.user.User;

public class BallProjectile implements Projectile {

	private Snowball snowball;
	
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
		this.snowball = user.<Player>getPlayerObject().launchProjectile(Snowball.class);
		PacketEntityDestroy packet = new PacketEntityDestroy(snowball.getEntityId());
		packet.send(user.<Player>getPlayerObject());
		for (Player p : user.<Player>getPlayerObject().getWorld().getPlayers()) {
			packet.send(p);
		}
		Zephyr.getProjectileHandler().trackProjectile(this);
	}

	@Override
	public void onProjectileTick(Object obj) {		
		if (!(obj instanceof Location)) {
			return;
		}
		Location loc = (Location) obj;
		ParticleEffects.sendParticle(Particle.FIRE, loc, 10);
	}

	@Override
	public void onBlockHit(Object obj) {
	}

	@Override
	public void onEntityHit(Object obj) {
		if (!(obj instanceof LivingEntity)) {
			return;
		}
		LivingEntity target = (LivingEntity) obj;
		target.setFireTicks(100);
	}

}
