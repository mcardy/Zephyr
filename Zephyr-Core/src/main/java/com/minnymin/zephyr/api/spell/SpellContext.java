package com.minnymin.zephyr.api.spell;

import com.google.common.base.Optional;
import com.minnymin.zephyr.api.user.User;

/**
 * Represents the context of a spell cast including arguments, the caster, target, etc.
 *
 * @author minnymin3
 */
public interface SpellContext {

	/**
	 * @return The arguments of the spell cast, empty String array if no arguments are present
	 */
	public String[] getArguments();

	/**
	 * @return The player object for the platform or null if T is not of the player type
	 */
	public <T> T getPlayer();

	/**
	 * @return Gets the power the spell was cast with
	 */
	public int getPower();

	/**
	 * @return Gets the spell that was cast
	 */
	public Spell getSpell();

	/**
	 * @return Gets the target holder of the target. If target is not of type T, the TargetHolder will be empty.
	 */
	public <T> Optional<T> getTarget();

	/**
	 * @return The caster of the spell
	 */
	public User getUser();

	/**
	 * @return Checks whether or not a target is present
	 */
	public boolean hasTarget();

}