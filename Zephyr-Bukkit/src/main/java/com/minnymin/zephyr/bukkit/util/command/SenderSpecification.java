package com.minnymin.zephyr.bukkit.util.command;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(value = RetentionPolicy.RUNTIME)
public @interface SenderSpecification {
	
	public SenderType type();
	public String message();
	
	public enum SenderType {
		CONSOLE, PLAYER, ANY;
	}
	
}
