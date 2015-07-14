package com.minnymin.zephyr.spell.target;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@java.lang.annotation.Target(value=ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Targeted {

	/**
	 * The type that this spell targets
	 */
	public TargetType type();
	
	/**
	 * The range of the target
	 */
	public int range() default 5;
	
	/**
	 * Whether or not this spell is friendly to the target.
	 * Ignored if target type is block.
	 */
	public boolean friendly() default false;
	
	/**
	 * Whether or not the target is optional
	 */
	public boolean optional() default false;
	
	/**
	 * The types of targets available
	 */
	public enum TargetType {

		SELF, ENTITY, ENTITY_AREA, LIVING, LIVING_AREA, MONSTER, MONSTER_AREA, PLAYER, PLAYER_AREA, BLOCK; 
		
	}
	
}
