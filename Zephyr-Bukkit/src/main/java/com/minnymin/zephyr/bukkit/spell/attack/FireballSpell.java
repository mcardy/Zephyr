package com.minnymin.zephyr.bukkit.spell.attack;

import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;

import com.minnymin.zephyr.api.aspect.Aspect;
import com.minnymin.zephyr.api.aspect.AspectList;
import com.minnymin.zephyr.api.spell.CastResult;
import com.minnymin.zephyr.api.spell.SpellContext;
import com.minnymin.zephyr.api.spell.SpellRecipe;
import com.minnymin.zephyr.bukkit.projectile.BallProjectile;
import com.minnymin.zephyr.bukkit.util.ParticleEffects.Particle;
import com.minnymin.zephyr.common.spell.BaseSpell;

public class FireballSpell extends BaseSpell {

	public FireballSpell() {
		super("fireball", "Launches a fireball", 1, 50);
	}
	
	@Override
	public CastResult cast(SpellContext context) {
		new FireProjectile(context.getPower()).launch(context.getUser());
		return CastResult.SUCCESS;
	}

	@Override
	public SpellRecipe getRecipe() {
		return new SpellRecipe(AspectList.build(Aspect.FIRE, 8).add(Aspect.DESTRUCTION, 8));
	}

	private static class FireProjectile extends BallProjectile {

		private int power;
		
		public FireProjectile(int power) {
			super(Particle.FIRE);
			this.power = power;
		}
		
		@Override
		public void onBlockHit(Location loc) {
		}
		
		@Override
		public void onEntityHit(LivingEntity en) {
			en.setFireTicks(power * 40);
		}
		
	}
	
}
