package com.minnymin.zephyr.api.item;

import com.minnymin.zephyr.api.spell.Spell;

public interface WandItem extends ActionItem {

	public void bindSpell(Object itemStack, Spell spell);
	
	public String getBoundSpell(Object itemStack);
	
}
