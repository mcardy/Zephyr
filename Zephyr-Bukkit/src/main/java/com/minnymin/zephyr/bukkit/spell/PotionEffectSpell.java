package com.minnymin.zephyr.bukkit.spell;

import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;

import com.minnymin.zephyr.api.spell.CastResult;
import com.minnymin.zephyr.api.spell.SpellContext;
import com.minnymin.zephyr.common.spell.BaseSpell;

/**
 * Represents a potion effect spell
 *
 * @author minnymin3
 */
public abstract class PotionEffectSpell extends BaseSpell {
	
	public PotionEffectSpell(String name, String description, int requiredLevel, int manaCost) {
		super(name, description, requiredLevel, manaCost);
	}
	
	public PotionEffectSpell(String name, String description, int requiredLevel, int manaCost, int exp) {
		super(name, description, requiredLevel, manaCost, exp);
	}

	@Override
	public CastResult cast(SpellContext context) {
		context.<Player>getPlayer().addPotionEffect(this.getPotionEffect(context));
		return CastResult.SUCCESS;
	}
	
	public abstract PotionEffect getPotionEffect(SpellContext context);

}
