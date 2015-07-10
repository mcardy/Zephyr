package com.minnymin.zephyr.spell;

import java.util.HashSet;
import java.util.Set;

import com.minnymin.zephyr.user.User;

public abstract class SpellManager {

	private Set<Spell> spellSet;

	public SpellManager() {
		this.spellSet = new HashSet<Spell>();
	}

	public void addSpell(Spell spell) {
		onSpellAdded(spell);
		this.spellSet.add(spell);
	}

	public Spell getSpell(String name) {
		for (Spell s : spellSet) {
			if (s.getName().equalsIgnoreCase(name)) {
				return s;
			}
		}
		return null;
	}

	public Spell getSpell(Class<? extends Spell> spell) {
		for (Spell s : spellSet) {
			if (s.getClass().isAssignableFrom(spell)) {
				return s;
			}
		}
		return null;
	}

	public void cast(Spell spell, SpellContext context) {
		User user = context.getUser();
		if (spell == null) {
			user.sendMessage("That spell does not exist...");
		}
		if (!user.hasMana(spell.getManaCost())) {
			user.sendMessage("You do not have enough mana to cast " + spell.getName() + "! " + user.getMana() + " / "
					+ spell.getManaCost());
			return;
		}
		CastResult result = spell.cast(context);
		if (result != CastResult.FAILURE) {
			user.drainMana(spell.getManaCost());
		}
	}

	public abstract void onSpellAdded(Spell spell);

}
