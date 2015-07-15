package com.minnymin.zephyr.bukkit.util.command;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(value = RetentionPolicy.RUNTIME)
public @interface Permission {

	public String permission();
	
	public String message() default "You do not have permission to use that command!";
	
}
