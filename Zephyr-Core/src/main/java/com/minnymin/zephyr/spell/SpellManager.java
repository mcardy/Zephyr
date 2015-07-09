package com.minnymin.zephyr.spell;

import java.util.HashSet;
import java.util.Set;

public abstract class SpellManager {

	private Set<Spell> spellSet;
	
	public SpellManager() {
		this.spellSet = new HashSet<Spell>();
	}
	
	public void addSpell(Spell spell) {
		addHook(spell);
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
	
	
	public abstract void addHook(Spell spell);
	public abstract SpellContext getContext();
	
}
