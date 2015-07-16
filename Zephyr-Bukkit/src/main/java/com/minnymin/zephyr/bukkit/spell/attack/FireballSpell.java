package com.minnymin.zephyr.bukkit.spell.attack;

import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;

import com.minnymin.zephyr.spell.CastResult;
import com.minnymin.zephyr.spell.Spell;
import com.minnymin.zephyr.spell.SpellContext;

public class FireballSpell extends Spell {

	public FireballSpell() {
		super("fireball", "Shoots a fireball", 1, 20);
	}

	@Override
	public CastResult cast(SpellContext context) {
		Player player = (Player) context.getPlayer();
		player.launchProjectile(Fireball.class);
		return CastResult.SUCCESS;
	}

}
