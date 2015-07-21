package com.minnymin.zephyr.common.spell;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.minnymin.zephyr.api.spell.CastResult;
import com.minnymin.zephyr.api.spell.ContinuousSpell;
import com.minnymin.zephyr.api.spell.Spell;
import com.minnymin.zephyr.api.spell.SpellContext;
import com.minnymin.zephyr.api.spell.SpellManager;
import com.minnymin.zephyr.api.spell.target.Targeted;
import com.minnymin.zephyr.api.user.User;

public abstract class AbstractSpellManager implements SpellManager {

	private Set<Spell> spellSet;

	@Override
	public void onEnable() {
		this.spellSet = new HashSet<Spell>();
	}
	
	@Override
	public void onDisable() {
	}

	@Override
	public void registerSpell(Spell spell) {
		onSpellAdded(spell);
		this.spellSet.add(spell);
	}
	
	@Override
	public List<String> getAllSpells() {
		List<String> list = new ArrayList<String>();
		for (Spell spell : this.spellSet) {
			list.add(spell.getName());
		}
		return list;
	}

	@Override
	public Spell getSpell(String name) {
		for (Spell s : spellSet) {
			if (s.getName().equalsIgnoreCase(name)) {
				return s;
			}
		}
		return null;
	}

	@Override
	public Spell getSpell(Class<? extends Spell> spell) {
		for (Spell s : spellSet) {
			if (s.getClass().isAssignableFrom(spell)) {
				return s;
			}
		}
		return null;
	}
	
	@Override
	public List<Spell> getSpellsForLevel(int level) {
		List<Spell> spells = new ArrayList<Spell>();
		for (Spell spell : this.spellSet) {
			if (spell.getRequiredLevel() == level) {
				spells.add(spell);
			}
		}
		return spells;
	}

	@Override
	public void cast(Spell spell, SpellContext context) {
		User user = context.getUser();
		if (spell == null) {
			user.sendMessage("That spell does not exist...");
			return;
		}
		if (!user.getUserData().getKnownSpells().contains(spell.getName())) {
			user.sendMessage("You have not learned that spell!");
			return;
		}
		if (spell.getClass().isAnnotationPresent(Targeted.class)) {
			Targeted target = user.getClass().getAnnotation(Targeted.class);
			if (!target.optional() && !context.hasTarget()) {
				user.sendMessage("You do not have a target!");
				return;
			}
		}
		if (!user.hasMana(spell.getManaCost())) {
			user.sendMessage("You do not have enough mana to cast " + spell.getName() + "! " + user.getMana() + " / "
					+ spell.getManaCost());
			return;
		}
		CastResult result = spell.cast(context);
		if (result != CastResult.FAILURE) {
			user.drainMana(spell.getManaCost());
			user.addLevelProgress(spell.getExperienceGain());
		}
	}

	@Override
	public void castContinuous(ContinuousSpell spell, SpellContext context) {
		User user = context.getUser();
		if (spell == null) {
			return;
		}
		if (!user.hasMana(spell.getManaPerUpdate())) {
			user.sendMessage("You cannot keep casting " + spell.getName() + "! Mana: " + user.getMana() + " / "
					+ spell.getManaPerUpdate());
			user.setCasting(null, null);
			return;
		}
		CastResult result = spell.cast(context);
		if (result != CastResult.FAILURE) {
			user.drainMana(spell.getManaPerUpdate());
		}
	}

	public abstract void onSpellAdded(Spell spell);

}
