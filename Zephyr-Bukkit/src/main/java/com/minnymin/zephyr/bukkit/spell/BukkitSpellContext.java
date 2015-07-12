package com.minnymin.zephyr.bukkit.spell;

import com.minnymin.zephyr.spell.Spell;
import com.minnymin.zephyr.spell.SpellContext;
import com.minnymin.zephyr.spell.target.TargetHolder;
import com.minnymin.zephyr.user.User;

public class BukkitSpellContext extends SpellContext {

	public BukkitSpellContext(Spell spell, User user, String[] args) {
		super(spell, user, args);
	}

	@Override
	public <T> TargetHolder<T> getTarget() {
		// TODO Implement targets for Bukkit
		return null;
	}

}
