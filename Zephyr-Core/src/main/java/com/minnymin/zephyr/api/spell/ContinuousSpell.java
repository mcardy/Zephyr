package com.minnymin.zephyr.api.spell;

/**
 * Represents a spell that is cast and continues casting automatically
 *
 * @author minnymin3
 */
public interface ContinuousSpell extends Spell {

	/**
	 * Gets the mana cost per update
	 * @return The mana cost per update
	 */
	public int getManaPerUpdate();

	/**
	 * Gets how often the spell updates
	 * @return How often the spell updates in game ticks (20 per second)
	 */
	public int getUpdateTime();

	/**
	 * Updates the spell, called every update time in game ticks until the user stops casting or
	 * the user runs out of mana
	 * @param context The context of the spell cast
	 * @return The {@link CastResult} of this update
	 */
	public CastResult update(SpellContext context);

}