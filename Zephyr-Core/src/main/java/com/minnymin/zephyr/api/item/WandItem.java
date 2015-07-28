package com.minnymin.zephyr.api.item;

import com.minnymin.zephyr.api.spell.Spell;
import com.minnymin.zephyr.api.user.User;

public interface WandItem extends ActionItem {

	public void bindRightClick(Object itemStack, Spell spell, User caster);
	public void bindLeftClick(Object itemStack, Spell spell, User caster);
	
	public String getLeftBoundSpell(Object itemStack);
	public String getRightBoundSpell(Object itemStack);
	
}
