package com.minnymin.zephyr.api.util;

/**
 * A wrapper class for Minecraft colours to allow for compatability between Zephyr-Bukkit and Zephyr-Sponge
 *
 * @author minnymin3
 */
public enum Colour {

	BLACK('0'),
	DARK_BLUE('1'),
	DARK_GREEN('2'),
	DARK_AQUA('3'),
	DARK_RED('4'),
	DARK_PURPLE('5'),
	GOLD('6'),
	GRAY('7'),
	DARK_GRAY('8'),
	BLUE('9'),
	GREEN('a'),
	AQUA('b'),
	RED('c'),
	LIGHT_PURPLE('d'),
	YELLOW('e'),
	WHITE('f');
	
	private static final char COLOR_CHAR = '\u00A7';
	private final char code;
	private final String string;
	
	private Colour(char code) {
		this.code = code;
		this.string = new String(new char[] {COLOR_CHAR, code});
	}
	
	public char getCode() {
		return this.code;
	}
	
	@Override
	public String toString() {
		return this.string;
	}
	
	
}
