package com.minnymin.zephyr.api.spell.target;

/**
 * Represents a target, held in a generic container.
 * Is typed to allow for cross platform access
 *
 * @author minnymin3
 */
public class TargetHolder<T> {

	private T target;
	
	/**
	 * Creates a new TargetHolder with the given target
	 * @param target
	 */
	@SuppressWarnings("unchecked")
	public TargetHolder(Object target) {
		try {
			this.target = (T) target;
		} catch (ClassCastException ex) {
			this.target = null;
		}
	}
	
	/**
	 * Gets the target
	 * @return The target or null if there is no target or the target was not of type T
	 */
	public T get() {
		return this.target;
	}
	
	/**
	 * Checks if the target is held in this holder
	 * @return True if the target is missing
	 */
	public boolean isAbsent() {
		return this.target == null;
	}
	
}
