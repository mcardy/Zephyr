package com.minnymin.zephyr.api.item;

import com.minnymin.zephyr.api.spell.Spell;
import com.minnymin.zephyr.api.user.User;

public interface WandItem extends ActionItem {

	public void bindSpell(Object itemStack, Spell spell, User caster);
	
	public String getBoundSpell(Object itemStack);
	
}
