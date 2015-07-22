package com.minnymin.zephyr.api.aspect;

import java.util.HashMap;
import java.util.Map;

import com.minnymin.zephyr.api.util.ColorCode;

/**
 * Represents all of the types of aspects available.
 * 
 * Ported from Zephyrus II
 * 
 * @author minnymin3
 * 
 */
public enum Aspect {
	
	// TODO Comments on each aspect?
	BEAST("Bestia", "Animal, Beast", ColorCode.DARK_GREEN),
	CLEAR("Vetro", "Clear, Glass", ColorCode.WHITE),
	CONSTRUCT("Costruire", "Construction, Build", ColorCode.GRAY),
	DARKNESS("Tenebre", "Darkness, Hidden, Obscured", ColorCode.BLACK),
	DEFENSE("Difesa", "Defense, Protection", ColorCode.DARK_BLUE),
	DESTRUCTION("Contritio", "Destruction, Fractured", ColorCode.DARK_GRAY),
	EARTH("Terra", "Earth, Dirt", ColorCode.DARK_GREEN),
	ENDER("Endrich", "Ender, Strange", ColorCode.DARK_PURPLE),
	ENERGY("Energia", "Energy, Power", ColorCode.GRAY),
	EVIL("Malum", "Evil", ColorCode.DARK_RED),
	FIRE("Ignis", "Fire, Heat", ColorCode.RED),
	FLESH("Carnem", "Flesh, Bones", ColorCode.RED),
	ICE("Glaciem", "Ice, Cold", ColorCode.AQUA),
	KNOWLEDGE("Cognitio", "Knowledge", ColorCode.GOLD),
	LIFE("Vita", "Life, Sustanance", ColorCode.GREEN),
	LIGHT("Lucem", "Light, Brightness", ColorCode.YELLOW),
	MACHINE("Apparatus", "Machine, Device", ColorCode.DARK_AQUA),
	METAL("Metallum", "Metal, Ore", ColorCode.GRAY),
	MOVEMENT("Motus", "Movement, Motion", ColorCode.WHITE),
	MYSTICAL("Magicis", "Magic, Mystical", ColorCode.LIGHT_PURPLE),
	PLANT("Herba", "Herb, Plant", ColorCode.GREEN),
	STONE("Lapideas", "Stone, Rock", ColorCode.DARK_GRAY),
	TIME("Tempus", "Time", ColorCode.YELLOW),
	TOOL("Strumento", "Instrument, Tool", ColorCode.GREEN),
	VALUE("Valore", "Value, Expensive", ColorCode.GOLD),
	VOID("Vuoto", "Void, Empty", ColorCode.BLACK),
	WATER("Aqua", "Water", ColorCode.BLUE),
	WEAPON("Impetum", "Attack, Harmful, Weapon", ColorCode.RED),
	WIND("Ventum", "Wind, Air", ColorCode.WHITE),
	WOOD("Legno", "Wooden, Solid", ColorCode.DARK_GREEN);

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
	
	private ColorCode color;
	private String description;
	private String name;

	Aspect(String name, String description, ColorCode color) {
		this.name = name;
		this.description = description;
		this.color = color;
	}

	/**
	 * Gets the ChatColor of the aspect
	 */
	public ColorCode getColour() {
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