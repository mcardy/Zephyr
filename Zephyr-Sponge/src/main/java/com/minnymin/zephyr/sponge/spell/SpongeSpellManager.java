package com.minnymin.zephyr.sponge.spell;

import com.minnymin.zephyr.spell.Spell;
import com.minnymin.zephyr.spell.SpellManager;
import com.minnymin.zephyr.sponge.ZephyrPlugin;

public class SpongeSpellManager extends SpellManager {

	public SpongeSpellManager() {
		addSpell(new TestSpell());
		addSpell(new TestStateSpell());
		addSpell(new TestContinuousSpell());
	}
	
	@Override
	public void onSpellAdded(Spell spell) {
		if (spell.isListener()) {
			ZephyrPlugin.getGame().getEventManager().register(ZephyrPlugin.getInstance(), spell);
		}
	}

}
