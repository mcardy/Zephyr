package com.minnymin.zephyr.api.aspect;

import java.util.HashMap;
import java.util.Map;

import com.minnymin.zephyr.api.util.ColorCharacter;

/**
 * Represents all of the types of aspects available.
 * 
 * Ported from Zephyrus II
 * 
 * @author minnymin3
 * 
 */
public enum Aspect {
	
	// TODO Comments on each aspect
	BEAST("Bestia", "Animal, Beast", ColorCharacter.DARK_GREEN),
	CLEAR("Vetro", "Clear, Glass", ColorCharacter.WHITE),
	CONSTRUCT("Costruire", "Construction, Build", ColorCharacter.GRAY),
	DARKNESS("Tenebre", "Darkness, Hidden, Obscured", ColorCharacter.BLACK),
	DEATH("Mortem", "Death", ColorCharacter.DARK_RED),
	DEFENSE("Difesa", "Defense, Protection", ColorCharacter.DARK_BLUE),
	DESTRUCTION("Contritio", "Destruction, Fractured", ColorCharacter.DARK_GRAY),
	EARTH("Terra", "Earth, Dirt", ColorCharacter.DARK_GREEN),
	ENDER("Endrich", "Ender, Strange", ColorCharacter.DARK_PURPLE),
	FIRE("Ignis", "Fire, Heat", ColorCharacter.RED),
	ICE("Glaciem", "Ice, Cold", ColorCharacter.AQUA),
	KNOWLEDGE("Cognitio", "Knowledge", ColorCharacter.GOLD),
	LIFE("Vita", "Life, Sustanance", ColorCharacter.GREEN),
	LIGHT("Lucem", "Light, Brightness", ColorCharacter.YELLOW),
	MACHINE("Apparatus", "Machine, Device", ColorCharacter.DARK_AQUA),
	MAGIC("Magicis", "Magic, Mystical", ColorCharacter.LIGHT_PURPLE),
	METAL("Metallum", "Metal, Ore", ColorCharacter.GRAY),
	MOVEMENT("Motus", "Movement, Motion", ColorCharacter.WHITE),
	STONE("Lapideas", "Stone, Rock", ColorCharacter.DARK_GRAY),
	TIME("Tempus", "Time", ColorCharacter.YELLOW),
	TOOL("Strumento", "Instrument, Tool", ColorCharacter.GREEN),
	VALUE("Valore", "Value, Expensive", ColorCharacter.GOLD),
	VOID("Vuoto", "Void, Empty", ColorCharacter.BLACK),
	WATER("Aqua", "Water", ColorCharacter.BLUE),
	WEAPON("Impetum", "Attack, Harmful, Weapon", ColorCharacter.RED),
	WIND("Ventum", "Wind, Air", ColorCharacter.WHITE),
	WOOD("Legno", "Wooden, Solid", ColorCharacter.DARK_GREEN);

	private static Map<String, AspectList> aspectMap;
	
	public static Map<String, AspectList> getAspectMap() {
		if (aspectMap == null) {
			aspectMap = new HashMap<String, AspectList>();
		}
		return aspectMap;
	}
	
	public static AspectList getAspects(String itemId) {
		return getAspectMap().get(itemId);
	}
	
	/**
	 * Aspect class below
	 */
	
	private ColorCharacter color;
	private String description;
	private String name;

	Aspect(String name, String description, ColorCharacter color) {
		this.name = name;
		this.description = description;
		this.color = color;
	}

	/**
	 * Gets the ChatColor of the aspect
	 */
	public ColorCharacter getColour() {
		return color;
	}

	/**
	 * Gets the default description written in English
	 */
	public String getDefaultDescription() {
		return description;
	}

	/**
	 * Gets the default name written in English
	 */
	public String getDefaultName() {
		return name;
	}

}