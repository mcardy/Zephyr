package com.minnymin.zephyr.api.spell;

import java.util.List;

import com.minnymin.zephyr.api.Manager;

/**
 * Represents a manager used to register and retrieve spells
 *
 * @author minnymin3
 */
public interface SpellManager extends Manager {

	/**
	 * Casts a {@link Spell}
	 * @param spell The spell to cast
	 * @param context The context of the cast
	 */
	public void cast(Spell spell, SpellContext context);

	/**
	 * Begins casting a {@link ContinuousSpell}
	 * @param spell The continuous spell to cast
	 * @param context The context of the cast
	 */
	public void castContinuous(ContinuousSpell spell, SpellContext context);

	/**
	 * Gets all {@link Spell} objects registered in this manager
	 * @return A list of all the spell names
	 */
	public List<String> getAllSpells();

	/**
	 * Gets a {@link Spell} by the given class
	 * @param spell The class of the spell to find
	 * @return The spell object or null if the spell was not found
	 */
	public Spell getSpell(Class<? extends Spell> spell);

	/**
	 * Gets a {@link Spell} by the given name
	 * @param name The name of the spell to find
	 * @return The spell object or null if the spell was not found
	 */
	public Spell getSpell(String name);

	/**
	 * Gets a list of {@link Spell} objects from the given level
	 * @param level The level to get
	 * @return A list of spells that have the same required level as the given level
	 */
	public List<Spell> getSpellsForLevel(int level);

	/**
	 * Add a {@link Spell} to this spell manager instance
	 * @param spell The spell to add
	 */
	public void registerSpell(Spell spell);

}