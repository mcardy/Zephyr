package com.minnymin.zephyr.bukkit.spell.attack;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import com.minnymin.zephyr.api.aspect.Aspect;
import com.minnymin.zephyr.api.aspect.AspectList;
import com.minnymin.zephyr.api.spell.CastResult;
import com.minnymin.zephyr.api.spell.SpellContext;
import com.minnymin.zephyr.api.spell.SpellRecipe;
import com.minnymin.zephyr.bukkit.projectile.BallProjectile;
import com.minnymin.zephyr.bukkit.util.ParticleEffects.Particle;
import com.minnymin.zephyr.common.spell.BaseSpell;

public class BoltSpell extends BaseSpell {

	public BoltSpell() {
		super("bolt", "Launches a bolt of energy", 1, 25);
	}

	@Override
	public CastResult cast(SpellContext context) {
		new BoltProjectile(context.getPower(), context.<Player>getPlayer()).launch(context.getUser());
		return CastResult.SUCCESS;
	}

	@Override
	public SpellRecipe getRecipe() {
		return new SpellRecipe(AspectList.build(Aspect.LIGHT, 8).add(Aspect.WEAPON, 8));
	}
	
	private static class BoltProjectile extends BallProjectile {

		private int power;
		private UUID source;
		
		public BoltProjectile(int power, Player source) {
			super(Particle.BLUE_SPARKLE);
			this.power = power;
			this.source = source.getUniqueId();
		}
		
		@Override
		public void onBlockHit(Location loc) {
		}
		
		@Override
		public void onEntityHit(LivingEntity en) {
			en.damage(this.power * 3, Bukkit.getPlayer(this.source));;
		}
		
	}

}
