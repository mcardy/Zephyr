package com.minnymin.zephyr.bukkit.spell.attack;

import org.bukkit.entity.LivingEntity;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.minnymin.zephyr.api.aspect.Aspect;
import com.minnymin.zephyr.api.aspect.AspectList;
import com.minnymin.zephyr.api.spell.CastResult;
import com.minnymin.zephyr.api.spell.SpellContext;
import com.minnymin.zephyr.api.spell.SpellRecipe;
import com.minnymin.zephyr.api.spell.target.Targeted;
import com.minnymin.zephyr.api.spell.target.Targeted.TargetType;
import com.minnymin.zephyr.common.spell.BaseSpell;

@Targeted(type = TargetType.LIVING)
public class PoisonSpell extends BaseSpell {

	public PoisonSpell() {
		super("poison", "Poisons your target", 2, 40);
	}

	@Override
	public CastResult cast(SpellContext context) {
		context.<LivingEntity> getTarget()
				.get()
				.addPotionEffect(new PotionEffect(PotionEffectType.POISON, context.getPower() * 40, context.getPower()));
		return CastResult.SUCCESS;
	}

	@Override
	public SpellRecipe getRecipe() {
		return new SpellRecipe(AspectList.build(Aspect.DEATH, 12));
	}

}
