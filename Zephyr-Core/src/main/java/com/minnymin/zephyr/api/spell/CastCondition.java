package com.minnymin.zephyr.api.spell;

public interface CastCondition {

	/**
	 * Checks if the user is able to cast a spell
	 * @param context The {@link SpellContext} of the cast
	 * @return True if the user can cast, false otherwise
	 */
	public boolean canCast(SpellContext context);
	
	/**
	 * Gets the message that is sent to the user when the condition is not satisfied
	 * @return The message to send
	 */
	public String getMessage(SpellContext context);
	
}
