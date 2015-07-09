package com.minnymin.zephyr.sponge.spell;

import com.minnymin.zephyr.spell.Spell;
import com.minnymin.zephyr.spell.SpellContext;
import com.minnymin.zephyr.spell.SpellManager;

public class SpongeSpellManager extends SpellManager {

	public SpongeSpellManager() {
		addSpell(new TestSpell());
	}
	
	@Override
	public void addHook(Spell spell) {
		
	}

	@Override
	public SpellContext getContext() {
		return null;
	}

}
