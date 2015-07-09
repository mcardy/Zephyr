package com.minnymin.zephyr.sponge.spell;

import org.spongepowered.api.text.Texts;

import com.minnymin.zephyr.spell.Spell;
import com.minnymin.zephyr.spell.SpellContext;
import com.minnymin.zephyr.sponge.SpongeUser;
import com.minnymin.zephyr.user.User;

public class TestSpell extends Spell {

	public TestSpell() {
		super("test", "This is a test");
	}

	@Override
	public void cast(SpellContext context) {
		User user = context.getUser();
		if (user instanceof SpongeUser) {
			((SpongeUser)user).getSource().sendMessage(Texts.of("Hello"));
		}
	}

}
