package com.minnymin.zephyr.api.spell;

import java.util.Collection;

import com.minnymin.zephyr.api.util.Configurable;

/**
 * Represents a castable spell
 *
 * @author minnymin3
 */
public interface Spell extends Configurable {

	/**
	 * Adds a cast condition to the collection of cast conditions
	 * @param condition The condition to add
	 */
	public void addCondition(CastCondition condition);
	
	/**
	 * Casts the spell
	 * @param context The context of the spell cast
	 * @return The {@link CastResult} of the spell cast
	 */
	public CastResult cast(SpellContext context);

	/**
	 * Returns a collection of cast conditions
	 * @return A collection of cast conditions
	 */
	public Collection<CastCondition> getConditions();
	
	/**
	 * @return The description of the spell
	 */
	public String getDescription();

	/**
	 * @return The experience reward of casting the spell
	 */
	public int getExperienceGain();

	/**
	 * @return The mana cost of casting the spell
	 */
	public int getManaCost();

	/**
	 * @return The name of the spell
	 */
	public String getName();

	/**
	 * @return The required level to learn the spell
	 */
	public int getLevel();
	
	/**
	 * Gets the spell recipe required to craft
	 */
	public SpellRecipe getRecipe();

}