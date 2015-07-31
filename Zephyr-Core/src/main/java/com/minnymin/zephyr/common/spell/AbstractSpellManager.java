package com.minnymin.zephyr.common.spell;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.minnymin.zephyr.api.aspect.AspectList;
import com.minnymin.zephyr.api.spell.CastCondition;
import com.minnymin.zephyr.api.spell.CastResult;
import com.minnymin.zephyr.api.spell.ContinuousSpell;
import com.minnymin.zephyr.api.spell.Spell;
import com.minnymin.zephyr.api.spell.SpellContext;
import com.minnymin.zephyr.api.spell.SpellManager;
import com.minnymin.zephyr.api.user.User;
import com.minnymin.zephyr.api.util.ColorCharacter;

public abstract class AbstractSpellManager implements SpellManager {

	private Set<Spell> spellSet = new HashSet<Spell>();

	@Override
	public void onEnable() {
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
			if (spell.getLevel() == level) {
				spells.add(spell);
			}
		}
		return spells;
	}

	@Override
	public List<Spell> getSpellsForRecipe(AspectList list) {
		List<Spell> set = new ArrayList<Spell>();
		for (Spell spell : this.spellSet) {
			if (spell.getRecipe() != null && spell.getRecipe().isSatisfied(list)) {
				set.add(spell);
			}
		}
		return set;
	}
	
	@Override
	public void cast(Spell spell, SpellContext context) {
		User user = context.getUser();
		if (user.isCooling()) {
			return;
		}
		if (spell == null) {
			user.sendMessage(ColorCharacter.RED + "That spell does not exist...");
			return;
		}
		
		for (CastCondition condition : spell.getConditions()) {
			if (!condition.canCast(context)) {
				user.sendMessage(ColorCharacter.RED + condition.getMessage(context));
				return;
			}
		}
		
		CastResult result = spell.cast(context);
		if (result != CastResult.FAILURE) {
			user.drainMana(spell.getManaCost());
			user.addLevelProgress(spell.getExperienceGain());
		}
		user.setCooling(true);
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
