package com.minnymin.zephyr.sponge.spell;

import com.minnymin.zephyr.api.spell.Spell;
import com.minnymin.zephyr.common.spell.AbstractSpellManager;
import com.minnymin.zephyr.common.spell.BaseSpell;
import com.minnymin.zephyr.sponge.ZephyrPlugin;
import com.minnymin.zephyr.sponge.spell.attack.PunchSpell;

public class SpongeSpellManager extends AbstractSpellManager {
	
	@Override
	public void onEnable() {
		super.onEnable();
		registerSpell(new PunchSpell());
	}
	
	@Override
	public void onSpellAdded(Spell spell) {
		if (spell instanceof BaseSpell && ((BaseSpell)spell).isListener()) {
			ZephyrPlugin.getGame().getEventManager().register(ZephyrPlugin.getInstance(), spell);
		}
	}

}
