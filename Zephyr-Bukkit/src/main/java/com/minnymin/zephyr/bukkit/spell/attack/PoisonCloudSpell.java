package com.minnymin.zephyr.bukkit.spell.attack;

import java.util.Collection;

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

@Targeted(type = TargetType.MONSTER_AREA, range = 5)
public class PoisonCloudSpell extends BaseSpell {

	public PoisonCloudSpell() {
		super("poisoncloud", "Makes a cloud of poison that poisons all monsters around you", 5, 100);
	}

	@Override
	public CastResult cast(SpellContext context) {
		for (LivingEntity en : context.<Collection<LivingEntity>>getTarget().get()) {
			en.addPotionEffect(new PotionEffect(PotionEffectType.POISON, context.getPower() * 40, context.getPower()));
		}
		return CastResult.SUCCESS;
	}

	@Override
	public SpellRecipe getRecipe() {
		return new SpellRecipe(AspectList.build(Aspect.DEATH, 32), PoisonSpell.class);
	}

}
