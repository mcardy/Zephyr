package com.minnymin.zephyr.bukkit.spell.buff;

import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.minnymin.zephyr.api.spell.SpellContext;
import com.minnymin.zephyr.bukkit.spell.PotionEffectSpell;

public class RegenerationSpell extends PotionEffectSpell {

	public RegenerationSpell() {
		super("regeneration", "Targets the caster and applies regeneration", 1, 25, 10);
	}

	@Override
	public PotionEffect getPotionEffect(SpellContext context) {
		return new PotionEffect(PotionEffectType.REGENERATION, 100 * context.getPower(), 1);
	}
	
}
