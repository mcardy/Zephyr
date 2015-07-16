package com.minnymin.zephyr.bukkit.spell.attack;

import java.util.Collection;

import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;

import com.minnymin.zephyr.spell.CastResult;
import com.minnymin.zephyr.spell.Spell;
import com.minnymin.zephyr.spell.SpellContext;
import com.minnymin.zephyr.spell.target.Targeted;
import com.minnymin.zephyr.spell.target.Targeted.TargetType;

@Targeted(type = TargetType.LIVING_AREA)
public class ButcherSpell extends Spell {

	public ButcherSpell() {
		super("butcher", "Butchers all enemies around you", 1, 50);
	}

	@Override
	public CastResult cast(SpellContext context) {
		Collection<Monster> monsters = context.<Collection<Monster>>getTarget().get();
		for (Monster m : monsters) {
			m.damage(m.getHealth(), context.<Player>getPlayer());;
		}
		return CastResult.SUCCESS;
	}

}
