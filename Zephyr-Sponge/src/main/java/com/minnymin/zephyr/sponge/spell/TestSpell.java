package com.minnymin.zephyr.sponge.spell;

import com.minnymin.zephyr.spell.CastResult;
import com.minnymin.zephyr.spell.Spell;
import com.minnymin.zephyr.spell.SpellContext;
import com.minnymin.zephyr.sponge.user.SpongeUser;
import com.minnymin.zephyr.user.User;

public class TestSpell extends Spell {

	public TestSpell() {
		super("test", "This is a test", 1, 10, 5);
	}

	@Override
	public CastResult cast(SpellContext context) {
		User user = context.getUser();
		if (user instanceof SpongeUser) {
			user.sendMessage("Hello!");
		}
		return CastResult.SUCCESS;
	}

}
