package com.minnymin.zephyr.bukkit.spell.attack;

import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;

import com.minnymin.zephyr.api.spell.CastResult;
import com.minnymin.zephyr.api.spell.SpellContext;
import com.minnymin.zephyr.common.spell.BaseSpell;

public class FireballSpell extends BaseSpell {

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
