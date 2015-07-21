package com.minnymin.zephyr.api.spell.target;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Indicates that a spell is targeted
 *
 * @author minnymin3
 */
@java.lang.annotation.Target(value = ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Targeted {

	/**
	 * The types of targets available
	 */
	public enum TargetType {

		/**
		 * Target the block the player is looking at
		 */
		BLOCK,

		/**
		 * Target the entity that the caster is looking at 
		 */
		ENTITY,
		
		/**
		 * Target the entities around the caster
		 */
		ENTITY_AREA,
		
		/**
		 * Target the living entity that the caster is looking at
		 */
		LIVING,
		
		/**
		 * Target the living entities around the caster
		 */
		LIVING_AREA,
		
		/**
		 * Target the monster entity that the caster is looking at
		 */
		MONSTER,
		
		/**
		 * Target the monster entities around the caster
		 */
		MONSTER_AREA,
		
		/**
		 * Target the player entity that the caster is looking at
		 */
		PLAYER,
		
		/**
		 * Target the player entities around the caster
		 */
		PLAYER_AREA,
		
		/**
		 * Target the caster
		 */
		SELF;

	}

	/**
	 * @return Whether or not this spell is friendly to the target. Ignored if
	 *         target type is block.
	 */
	public boolean friendly() default false;

	/**
	 * @return Whether or not the target is optional
	 */
	public boolean optional() default false;

	/**
	 * @return The range of the target
	 */
	public int range() default 5;

	/**
	 * @return The type of target
	 */
	public TargetType type();

}
