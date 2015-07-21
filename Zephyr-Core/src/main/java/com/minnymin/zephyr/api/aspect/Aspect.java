package com.minnymin.zephyr.api.aspect;

import java.util.HashMap;
import java.util.Map;

import com.minnymin.zephyr.api.util.Colour;

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
	BEAST("Bestia", "Animal, Beast", Colour.DARK_GREEN),
	CLEAR("Vetro", "Clear, Glass", Colour.WHITE),
	CONSTRUCT("Costruire", "Construction, Build", Colour.GRAY),
	DARKNESS("Tenebre", "Darkness, Hidden, Obscured", Colour.BLACK),
	DEFENSE("Difesa", "Defense, Protection", Colour.DARK_BLUE),
	DESTRUCTION("Contritio", "Destruction, Fractured", Colour.DARK_GRAY),
	EARTH("Terra", "Earth, Dirt", Colour.DARK_GREEN),
	ENDER("Endrich", "Ender, Strange", Colour.DARK_PURPLE),
	ENERGY("Energia", "Energy, Power", Colour.GRAY),
	EVIL("Malum", "Evil", Colour.DARK_RED),
	FIRE("Ignis", "Fire, Heat", Colour.RED),
	FLESH("Carnem", "Flesh, Bones", Colour.RED),
	ICE("Glaciem", "Ice, Cold", Colour.AQUA),
	KNOWLEDGE("Cognitio", "Knowledge", Colour.GOLD),
	LIFE("Vita", "Life, Sustanance", Colour.GREEN),
	LIGHT("Lucem", "Light, Brightness", Colour.YELLOW),
	MACHINE("Apparatus", "Machine, Device", Colour.DARK_AQUA),
	METAL("Metallum", "Metal, Ore", Colour.GRAY),
	MOVEMENT("Motus", "Movement, Motion", Colour.WHITE),
	MYSTICAL("Magicis", "Magic, Mystical", Colour.LIGHT_PURPLE),
	PLANT("Herba", "Herb, Plant", Colour.GREEN),
	STONE("Lapideas", "Stone, Rock", Colour.DARK_GRAY),
	TIME("Tempus", "Time", Colour.YELLOW),
	TOOL("Strumento", "Instrument, Tool", Colour.GREEN),
	VALUE("Valore", "Value, Expensive", Colour.GOLD),
	VOID("Vuoto", "Void, Empty", Colour.BLACK),
	WATER("Aqua", "Water", Colour.BLUE),
	WEAPON("Impetum", "Attack, Harmful, Weapon", Colour.RED),
	WIND("Ventum", "Wind, Air", Colour.WHITE),
	WOOD("Legno", "Wooden, Solid", Colour.DARK_GREEN);

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
	
	private Colour color;
	private String description;
	private String name;

	Aspect(String name, String description, Colour color) {
		this.name = name;
		this.description = description;
		this.color = color;
	}

	/**
	 * Gets the ChatColor of the aspect
	 */
	public Colour getColour() {
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