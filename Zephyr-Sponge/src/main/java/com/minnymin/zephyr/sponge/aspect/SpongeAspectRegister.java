package com.minnymin.zephyr.sponge.aspect;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;

import org.spongepowered.api.item.ItemType;
import org.spongepowered.api.item.ItemTypes;

import com.minnymin.zephyr.api.Manager;
import com.minnymin.zephyr.api.aspect.Aspect;
import com.minnymin.zephyr.api.aspect.AspectList;

public class SpongeAspectRegister implements Manager {

	private Set<ItemType> itemTypeList = new HashSet<ItemType>();
	
	/**
	 * TODO Implement fully once Sponge implements itemtypes
	 */
	@Override
	public void onEnable() {		
		add(ItemTypes.ACACIA_STAIRS, Aspect.WOOD, 2);
		add(ItemTypes.ACTIVATOR_RAIL, Aspect.MACHINE, 4, Aspect.METAL, 1);
		add(ItemTypes.ANVIL, Aspect.METAL, 32, Aspect.CONSTRUCT, 8, Aspect.TOOL, 4);
		add(ItemTypes.APPLE, Aspect.LIFE, 2, Aspect.PLANT, 1);
		add(ItemTypes.ARROW, Aspect.WEAPON, 4, Aspect.WOOD, 1);

		add(ItemTypes.BAKED_POTATO, Aspect.LIFE, 2, Aspect.PLANT, 1);
		add(ItemTypes.BEACON, Aspect.MYSTICAL, 32, Aspect.VALUE, 64, Aspect.ENERGY, 128);
		add(ItemTypes.BED, Aspect.WOOD, 6, Aspect.LIFE, 1);
		add(ItemTypes.BEEF, Aspect.BEAST, 4, Aspect.FLESH, 4);
		add(ItemTypes.BIRCH_STAIRS, Aspect.WOOD, 2);
		add(ItemTypes.BLAZE_POWDER, Aspect.FIRE, 4, Aspect.MYSTICAL, 1, Aspect.ENERGY, 1);
		add(ItemTypes.BLAZE_ROD, Aspect.FIRE, 8, Aspect.MYSTICAL, 2, Aspect.ENERGY, 2);
		add(ItemTypes.BOAT, Aspect.WOOD, 10, Aspect.WATER, 5, Aspect.MOVEMENT, 10);
		add(ItemTypes.BONE, Aspect.EVIL, 1, Aspect.FLESH, 4);
		add(ItemTypes.BOOK, Aspect.KNOWLEDGE, 8, Aspect.PLANT, 4);
		add(ItemTypes.BOOKSHELF, Aspect.KNOWLEDGE, 24, Aspect.WOOD, 12, Aspect.PLANT, 12);
		add(ItemTypes.BOW, Aspect.WEAPON, 8, Aspect.PLANT, 2, Aspect.WOOD, 3);
		add(ItemTypes.BOWL, Aspect.WOOD, 3, Aspect.VOID, 1);
		add(ItemTypes.BREAD, Aspect.LIFE, 2, Aspect.PLANT, 2);
		add(ItemTypes.BRICK, Aspect.STONE, 4, Aspect.EARTH, 4);
		add(ItemTypes.BRICK_STAIRS, Aspect.STONE, 2, Aspect.EARTH, 2);
		add(ItemTypes.BROWN_MUSHROOM, Aspect.LIFE, 2, Aspect.PLANT, 2);
		add(ItemTypes.BUCKET, Aspect.METAL, 24, Aspect.VOID, 4);

		add(ItemTypes.CACTUS, Aspect.WEAPON, 1, Aspect.PLANT, 4, Aspect.LIFE, 1);
		add(ItemTypes.CAKE, Aspect.LIFE, 4);
		add(ItemTypes.CARPET, Aspect.BEAST, 1);
		add(ItemTypes.CARROT, Aspect.LIFE, 2, Aspect.PLANT, 2);
		add(ItemTypes.CARROT_ON_A_STICK, Aspect.WOOD, 1, Aspect.LIFE, 2, Aspect.PLANT, 2, Aspect.MOVEMENT, 2);
		add(ItemTypes.CAULDRON, Aspect.METAL, 20, Aspect.VOID, 8, Aspect.WATER, 4);
		add(ItemTypes.CHEST, Aspect.WOOD, 16, Aspect.VOID, 4);
		add(ItemTypes.CHEST_MINECART, Aspect.METAL, 40, Aspect.MACHINE, 8, Aspect.MOVEMENT, 8, Aspect.WOOD, 8,
				Aspect.VOID, 8);
		add(ItemTypes.CHICKEN, Aspect.BEAST, 4, Aspect.FLESH, 4);
		add(ItemTypes.CLAY, Aspect.EARTH, 8);
		add(ItemTypes.CLAY, Aspect.EARTH, 2);
		add(ItemTypes.CLOCK, Aspect.TIME, 16, Aspect.VALUE, 16, Aspect.METAL, 16);
		add(ItemTypes.COAL, Aspect.FIRE, 2, Aspect.ENERGY, 2);
		add(ItemTypes.COAL_BLOCK, Aspect.FIRE, 18, Aspect.ENERGY, 18);
		add(ItemTypes.COAL_ORE, Aspect.FIRE, 2, Aspect.ENERGY, 2, Aspect.STONE, 2);
		add(ItemTypes.COBBLESTONE, Aspect.DESTRUCTION, 1, Aspect.STONE, 1);
		add(ItemTypes.COBBLESTONE_WALL, Aspect.DESTRUCTION, 1, Aspect.STONE, 1);
		add(ItemTypes.COMPARATOR, Aspect.MACHINE, 8, Aspect.STONE, 4, Aspect.ENERGY, 2);
		add(ItemTypes.COMPASS, Aspect.METAL, 4, Aspect.KNOWLEDGE, 1, Aspect.MOVEMENT, 1, Aspect.MACHINE, 2);
		add(ItemTypes.COOKED_BEEF, Aspect.FLESH, 4, Aspect.LIFE, 2, Aspect.BEAST, 2);
		add(ItemTypes.COOKED_CHICKEN, Aspect.FLESH, 4, Aspect.LIFE, 2, Aspect.BEAST, 2);
		add(ItemTypes.COOKED_FISH, Aspect.FLESH, 4, Aspect.LIFE, 2, Aspect.BEAST, 2);
		add(ItemTypes.COOKED_PORKCHOP, Aspect.FLESH, 4, Aspect.BEAST, 2, Aspect.LIFE, 2);
		add(ItemTypes.COOKED_MUTTON, Aspect.FLESH, 4, Aspect.BEAST, 2, Aspect.LIFE, 2);
		add(ItemTypes.COOKED_RABBIT, Aspect.FLESH, 4, Aspect.BEAST, 2, Aspect.LIFE, 2);
		add(ItemTypes.COOKIE, Aspect.LIFE, 4, Aspect.PLANT, 1);
		add(ItemTypes.CRAFTING_TABLE, Aspect.WOOD, 8, Aspect.CONSTRUCT, 8);

		add(ItemTypes.DARK_OAK_STAIRS, Aspect.WOOD, 2);
		add(ItemTypes.DAYLIGHT_DETECTOR, Aspect.LIGHT, 4, Aspect.MACHINE, 4);
		add(ItemTypes.DEADBUSH, Aspect.EVIL, 1, Aspect.PLANT, 1);
		add(ItemTypes.DETECTOR_RAIL, Aspect.MACHINE, 4, Aspect.METAL, 1);
		add(ItemTypes.DIAMOND, Aspect.CLEAR, 8, Aspect.VALUE, 8);
		add(ItemTypes.DIAMOND_AXE, Aspect.CLEAR, 24, Aspect.VALUE, 24, Aspect.TOOL, 8);
		add(ItemTypes.DIAMOND, Aspect.CLEAR, 8, Aspect.VALUE, 8);
		add(ItemTypes.DIAMOND_BLOCK, Aspect.CLEAR, 72, Aspect.VALUE, 72);
		add(ItemTypes.DIAMOND_BOOTS, Aspect.CLEAR, 32, Aspect.VALUE, 32, Aspect.DEFENSE, 8);
		add(ItemTypes.DIAMOND_CHESTPLATE, Aspect.CLEAR, 64, Aspect.VALUE, 64, Aspect.DEFENSE, 8);
		add(ItemTypes.DIAMOND_HELMET, Aspect.CLEAR, 40, Aspect.VALUE, 40, Aspect.DEFENSE, 8);
		add(ItemTypes.DIAMOND_HOE, Aspect.CLEAR, 16, Aspect.VALUE, 16, Aspect.TOOL, 8);
		add(ItemTypes.DIAMOND_LEGGINGS, Aspect.CLEAR, 56, Aspect.VALUE, 56, Aspect.TOOL, 8);
		add(ItemTypes.DIAMOND_ORE, Aspect.STONE, 2, Aspect.CLEAR, 4, Aspect.VALUE, 4);
		add(ItemTypes.DIAMOND_PICKAXE, Aspect.CLEAR, 24, Aspect.VALUE, 24, Aspect.TOOL, 8);
		add(ItemTypes.DIAMOND_SHOVEL, Aspect.CLEAR, 8, Aspect.VALUE, 8, Aspect.TOOL, 8);
		add(ItemTypes.DIAMOND_SWORD, Aspect.CLEAR, 16, Aspect.VALUE, 16, Aspect.WEAPON, 8);
		add(ItemTypes.DIRT, Aspect.EARTH, 2);
		add(ItemTypes.DISPENSER, Aspect.STONE, 8, Aspect.MACHINE, 8);
		add(ItemTypes.DRAGON_EGG, Aspect.ENDER, 128, Aspect.EVIL, 128, Aspect.LIFE, 128, Aspect.DARKNESS, 128,
				Aspect.LIGHT, 128, Aspect.BEAST, 128, Aspect.VOID, 128);
		add(ItemTypes.DROPPER, Aspect.STONE, 8, Aspect.MACHINE, 8);
		add(ItemTypes.DYE, Aspect.CONSTRUCT, 1);

		add(ItemTypes.EGG, Aspect.LIFE, 8, Aspect.BEAST, 2);
		add(ItemTypes.EMERALD, Aspect.CLEAR, 8, Aspect.VALUE, 8, Aspect.CONSTRUCT, 4);
		add(ItemTypes.EMERALD_BLOCK, Aspect.CLEAR, 72, Aspect.VALUE, 72, Aspect.CONSTRUCT, 36);
		add(ItemTypes.EMERALD_ORE, Aspect.STONE, 2, Aspect.CLEAR, 4, Aspect.VALUE, 4);
		add(ItemTypes.ENCHANTED_BOOK, Aspect.MYSTICAL, 8, Aspect.KNOWLEDGE, 8);
		add(ItemTypes.ENCHANTING_TABLE, Aspect.MYSTICAL, 16, Aspect.KNOWLEDGE, 16);
		add(ItemTypes.ENDER_CHEST, Aspect.VOID, 8, Aspect.ENDER, 8);
		add(ItemTypes.ENDER_PEARL, Aspect.ENDER, 8);
		add(ItemTypes.END_STONE, Aspect.ENDER, 1, Aspect.STONE, 1);
		add(ItemTypes.EXPERIENCE_BOTTLE, Aspect.KNOWLEDGE, 2);
		add(ItemTypes.ENDER_EYE, Aspect.FIRE, 2, Aspect.ENDER, 8, Aspect.MYSTICAL, 4);

		add(ItemTypes.FEATHER, Aspect.WIND, 4, Aspect.BEAST, 2);
		add(ItemTypes.FENCE, Aspect.WOOD, 3);
		add(ItemTypes.FENCE_GATE, Aspect.WOOD, 8, Aspect.MACHINE, 2);
		add(ItemTypes.FERMENTED_SPIDER_EYE, Aspect.EVIL, 2, Aspect.BEAST, 1, Aspect.FLESH, 1);
		add(ItemTypes.FIRE_CHARGE, Aspect.DESTRUCTION, 4, Aspect.FIRE, 4);
		add(ItemTypes.FIREWORKS, Aspect.FIRE, 2, Aspect.ENERGY, 4, Aspect.LIGHT, 4);
		add(ItemTypes.FIREWORK_CHARGE, Aspect.FIRE, 2, Aspect.ENERGY, 4, Aspect.LIGHT, 4);
		add(ItemTypes.FISH, Aspect.BEAST, 4, Aspect.FLESH, 4);
		add(ItemTypes.FISHING_ROD, Aspect.TOOL, 4);
		add(ItemTypes.FLINT, Aspect.STONE, 2);
		add(ItemTypes.FLINT_AND_STEEL, Aspect.STONE, 2, Aspect.METAL, 4, Aspect.FIRE, 2);
		add(ItemTypes.FLOWER_POT, Aspect.STONE, 4);
		add(ItemTypes.FURNACE, Aspect.STONE, 8, Aspect.FIRE, 1);
		add(ItemTypes.FURNACE_MINECART, Aspect.MACHINE, 8, Aspect.MOVEMENT, 8, Aspect.ENERGY, 8, Aspect.METAL, 40);

		add(ItemTypes.GHAST_TEAR, Aspect.BEAST, 2, Aspect.MYSTICAL, 2, Aspect.FIRE, 2, Aspect.EVIL, 2);
		add(ItemTypes.GLASS, Aspect.CLEAR, 3);
		add(ItemTypes.GLASS_PANE, Aspect.CLEAR, 1);
		add(ItemTypes.GLASS_BOTTLE, Aspect.CLEAR, 3);
		add(ItemTypes.GLOWSTONE, Aspect.STONE, 1, Aspect.LIGHT, 8);
		add(ItemTypes.GLOWSTONE_DUST, Aspect.LIGHT, 2);
		add(ItemTypes.GOLD_INGOT, Aspect.METAL, 4, Aspect.VALUE, 4);
		add(ItemTypes.GOLDEN_AXE, Aspect.METAL, 12, Aspect.VALUE, 12, Aspect.TOOL, 2);
		add(ItemTypes.GOLDEN_HORSE_ARMOR, Aspect.METAL, 20, Aspect.VALUE, 12, Aspect.DEFENSE, 2);
		add(ItemTypes.GOLD_BLOCK, Aspect.METAL, 36, Aspect.VALUE, 36);
		add(ItemTypes.GOLDEN_BOOTS, Aspect.METAL, 16, Aspect.VALUE, 16, Aspect.DEFENSE, 2);
		add(ItemTypes.GOLDEN_CHESTPLATE, Aspect.METAL, 32, Aspect.VALUE, 32, Aspect.DEFENSE, 2);
		add(ItemTypes.GOLDEN_HELMET, Aspect.METAL, 20, Aspect.VALUE, 20, Aspect.DEFENSE, 2);
		add(ItemTypes.GOLDEN_HOE, Aspect.METAL, 8, Aspect.VALUE, 8, Aspect.TOOL, 2);
		add(ItemTypes.GOLDEN_LEGGINGS, Aspect.METAL, 28, Aspect.VALUE, 28, Aspect.DEFENSE, 2);
		add(ItemTypes.GOLD_NUGGET, Aspect.METAL, 1);
		add(ItemTypes.GOLD_ORE, Aspect.STONE, 2, Aspect.VALUE, 2, Aspect.METAL, 2);
		add(ItemTypes.GOLDEN_PICKAXE, Aspect.METAL, 12, Aspect.VALUE, 12, Aspect.TOOL, 2);
		add(ItemTypes.GOLDEN_SHOVEL, Aspect.METAL, 8, Aspect.VALUE, 8, Aspect.TOOL, 2);
		add(ItemTypes.GOLDEN_SWORD, Aspect.METAL, 8, Aspect.VALUE, 8, Aspect.WEAPON, 2);
		add(ItemTypes.GOLDEN_APPLE, Aspect.METAL, 4, Aspect.VALUE, 4, Aspect.LIFE, 8);
		add(ItemTypes.GOLDEN_CARROT, Aspect.METAL, 4, Aspect.VALUE, 4, Aspect.LIFE, 2);
		add(ItemTypes.GOLDEN_RAIL, Aspect.METAL, 4, Aspect.MACHINE, 1, Aspect.ENERGY, 4);
		add(ItemTypes.GRASS, Aspect.EARTH, 1, Aspect.PLANT, 1);
		add(ItemTypes.GRAVEL, Aspect.EARTH, 1, Aspect.STONE, 1);
		add(ItemTypes.GUNPOWDER, Aspect.DESTRUCTION, 8, Aspect.BEAST, 2, Aspect.ENERGY, 4);

		add(ItemTypes.HARDENED_CLAY, Aspect.STONE, 2);
		add(ItemTypes.HAY_BLOCK, Aspect.PLANT, 2);
		add(ItemTypes.HEAVY_WEIGHTED_PRESSURE_PLATE, Aspect.METAL, 16, Aspect.MACHINE, 4);
		add(ItemTypes.HOPPER, Aspect.METAL, 24, Aspect.MACHINE, 8, Aspect.VOID, 4);
		add(ItemTypes.HOPPER_MINECART, Aspect.METAL, 48, Aspect.MACHINE, 16, Aspect.VOID, 4, Aspect.MOVEMENT, 8);

		add(ItemTypes.ICE, Aspect.ICE, 2);
		add(ItemTypes.IRON_INGOT, Aspect.METAL, 8);
		add(ItemTypes.IRON_AXE, Aspect.METAL, 24, Aspect.TOOL, 4);
		add(ItemTypes.IRON_HORSE_ARMOR, Aspect.METAL, 40, Aspect.DEFENSE, 4);
		add(ItemTypes.IRON_BLOCK, Aspect.METAL, 72);
		add(ItemTypes.IRON_BOOTS, Aspect.METAL, 32, Aspect.DEFENSE, 4);
		add(ItemTypes.IRON_CHESTPLATE, Aspect.METAL, 64, Aspect.DEFENSE, 4);
		add(ItemTypes.IRON_DOOR, Aspect.METAL, 48, Aspect.MACHINE, 4);
		add(ItemTypes.IRON_BARS, Aspect.METAL, 3);
		add(ItemTypes.IRON_HELMET, Aspect.METAL, 40, Aspect.DEFENSE, 4);
		add(ItemTypes.IRON_HOE, Aspect.METAL, 16, Aspect.TOOL, 4);
		add(ItemTypes.IRON_LEGGINGS, Aspect.METAL, 56, Aspect.DEFENSE, 4);
		add(ItemTypes.IRON_ORE, Aspect.STONE, 2, Aspect.METAL, 4);
		add(ItemTypes.IRON_PICKAXE, Aspect.METAL, 24, Aspect.TOOL, 4);
		add(ItemTypes.IRON_SHOVEL, Aspect.METAL, 8, Aspect.TOOL, 4);
		add(ItemTypes.IRON_SWORD, Aspect.METAL, 16, Aspect.WEAPON, 4);
		add(ItemTypes.ITEM_FRAME, Aspect.WOOD, 8);

		add(ItemTypes.JUKEBOX, Aspect.VALUE, 8, Aspect.WOOD, 8, Aspect.MACHINE, 8);
		add(ItemTypes.JUNGLE_STAIRS, Aspect.WOOD, 2);

		add(ItemTypes.LADDER, Aspect.WOOD, 2);
		add(ItemTypes.LAPIS_BLOCK, Aspect.VALUE, 9);
		add(ItemTypes.LAPIS_ORE, Aspect.STONE, 2, Aspect.VALUE, 4);
		add(ItemTypes.LAVA_BUCKET, Aspect.FIRE, 8, Aspect.METAL, 24);
		add(ItemTypes.LEAD, Aspect.BEAST, 2);
		add(ItemTypes.LEATHER, Aspect.BEAST, 2);
		add(ItemTypes.LEATHER_BOOTS, Aspect.BEAST, 8, Aspect.DEFENSE, 2);
		add(ItemTypes.LEATHER_CHESTPLATE, Aspect.BEAST, 16, Aspect.DEFENSE, 2);
		add(ItemTypes.LEATHER_HELMET, Aspect.BEAST, 10, Aspect.DEFENSE, 2);
		add(ItemTypes.LEATHER_LEGGINGS, Aspect.BEAST, 14, Aspect.DEFENSE, 2);
		add(ItemTypes.LEAVES, Aspect.PLANT, 1);
		add(ItemTypes.LEAVES2, Aspect.PLANT, 1);
		add(ItemTypes.LEVER, Aspect.MACHINE, 2);
		add(ItemTypes.LIT_PUMPKIN, Aspect.LIGHT, 8, Aspect.LIFE, 8, Aspect.PLANT, 8);
		add(ItemTypes.LOG, Aspect.WOOD, 8);
		add(ItemTypes.LOG2, Aspect.WOOD, 8);
		add(ItemTypes.LIGHT_WEIGHTED_PRESSURE_PLATE, Aspect.METAL, 8, Aspect.VALUE, 8, Aspect.MACHINE, 4);

		add(ItemTypes.MAGMA_CREAM, Aspect.BEAST, 2, Aspect.FIRE, 2);
		add(ItemTypes.MAP, Aspect.TOOL, 8, Aspect.PLANT, 4, Aspect.KNOWLEDGE, 8);
		add(ItemTypes.MELON, Aspect.LIFE, 1, Aspect.PLANT, 1);
		add(ItemTypes.MELON_BLOCK, Aspect.LIFE, 8, Aspect.PLANT, 8);
		add(ItemTypes.MELON_SEEDS, Aspect.LIFE, 1);
		add(ItemTypes.MILK_BUCKET, Aspect.METAL, 24, Aspect.BEAST, 4);
		add(ItemTypes.MINECART, Aspect.METAL, 40, Aspect.MACHINE, 8, Aspect.MOVEMENT, 8);
		add(ItemTypes.MOSSY_COBBLESTONE, Aspect.STONE, 1, Aspect.PLANT, 1);
		add(ItemTypes.MUSHROOM_STEW, Aspect.LIFE, 2, Aspect.PLANT, 4);
		add(ItemTypes.MYCELIUM, Aspect.EARTH, 1, Aspect.PLANT, 1, Aspect.MYSTICAL, 1);

		add(ItemTypes.NAME_TAG, Aspect.BEAST);
		add(ItemTypes.NETHER_BRICK, Aspect.EVIL, 1, Aspect.STONE, 4, Aspect.FIRE, 1);
		add(ItemTypes.NETHERBRICK, Aspect.STONE, 1);
		add(ItemTypes.NETHER_BRICK_STAIRS, Aspect.EVIL, 1, Aspect.STONE, 4, Aspect.FIRE, 1);
		add(ItemTypes.NETHER_BRICK_FENCE, Aspect.EVIL, 1, Aspect.STONE, 4, Aspect.FIRE, 1);
		add(ItemTypes.NETHER_WART, Aspect.EVIL, 2, Aspect.PLANT, 2);
		add(ItemTypes.NETHER_STAR, Aspect.MYSTICAL, 128);
		add(ItemTypes.NETHERRACK, Aspect.FIRE, 1, Aspect.STONE, 1);
		add(ItemTypes.NOTEBLOCK, Aspect.WOOD, 8, Aspect.MACHINE, 8);

		add(ItemTypes.OBSIDIAN, Aspect.DARKNESS, 2, Aspect.STONE, 2);

		add(ItemTypes.PACKED_ICE, Aspect.ICE, 4);
		add(ItemTypes.PAINTING, Aspect.KNOWLEDGE, 1, Aspect.WOOD, 1);
		add(ItemTypes.PAPER, Aspect.PLANT, 2, Aspect.KNOWLEDGE, 1);
		add(ItemTypes.PISTON, Aspect.MACHINE, 8, Aspect.STONE, 8, Aspect.WOOD, 4, Aspect.ENERGY, 4,
				Aspect.MOVEMENT, 4);
		add(ItemTypes.PLANKS, Aspect.WOOD, 2);
		add(ItemTypes.POISONOUS_POTATO, Aspect.EVIL, 2, Aspect.PLANT, 2);
		add(ItemTypes.POTATO, Aspect.LIFE, 2, Aspect.PLANT, 2);
		add(ItemTypes.FURNACE_MINECART, Aspect.MACHINE, 8, Aspect.MOVEMENT, 8, Aspect.ENERGY, 8, Aspect.METAL, 40);
		add(ItemTypes.PUMPKIN, Aspect.LIFE, 8, Aspect.PLANT, 8);
		add(ItemTypes.PUMPKIN_PIE, Aspect.LIFE, 8, Aspect.PLANT, 4, Aspect.BEAST, 2);
		add(ItemTypes.PUMPKIN_SEEDS, Aspect.LIFE, 1);

		add(ItemTypes.QUARTZ, Aspect.STONE, 1, Aspect.VALUE, 1);
		add(ItemTypes.QUARTZ_BLOCK, Aspect.STONE, 4, Aspect.VALUE, 4);
		add(ItemTypes.QUARTZ_ORE, Aspect.STONE, 2, Aspect.VALUE, 1, Aspect.FIRE, 1);
		add(ItemTypes.QUARTZ_STAIRS, Aspect.STONE, 4, Aspect.VALUE, 4);

		add(ItemTypes.RAIL, Aspect.METAL, 4, Aspect.MACHINE, 1);
		add(ItemTypes.RED_MUSHROOM, Aspect.LIFE, 2, Aspect.PLANT, 2);
		add(ItemTypes.RED_FLOWER, Aspect.LIFE, 2);
		add(ItemTypes.REDSTONE, Aspect.ENERGY, 1, Aspect.MACHINE, 1);
		add(ItemTypes.REDSTONE_BLOCK, Aspect.ENERGY, 9, Aspect.MACHINE, 9);
		add(ItemTypes.REDSTONE_LAMP, Aspect.MACHINE, 4, Aspect.LIGHT, 4);
		add(ItemTypes.REDSTONE_ORE, Aspect.STONE, 2, Aspect.ENERGY, 4);
		add(ItemTypes.REDSTONE_TORCH, Aspect.ENERGY, 2);
		add(ItemTypes.REEDS, Aspect.PLANT, 1);
		add(ItemTypes.REPEATER, Aspect.MACHINE, 8, Aspect.STONE, 4, Aspect.ENERGY, 2);
		add(ItemTypes.ROTTEN_FLESH, Aspect.FLESH, 4, Aspect.EVIL, 1);

		add(ItemTypes.SADDLE, Aspect.BEAST, 4);
		add(ItemTypes.SAND, Aspect.EARTH, 1);
		add(ItemTypes.SANDSTONE, Aspect.STONE, 2, Aspect.EARTH, 2);
		add(ItemTypes.SANDSTONE_STAIRS, Aspect.STONE, 2, Aspect.EARTH, 2);
		add(ItemTypes.SAPLING, Aspect.PLANT, 4, Aspect.WOOD, 1);
		add(ItemTypes.SHEARS, Aspect.TOOL, 2, Aspect.METAL, 8);
		add(ItemTypes.SIGN, Aspect.WOOD, 8);
		add(ItemTypes.SKULL, Aspect.EVIL, 8, Aspect.FLESH, 8);
		add(ItemTypes.SLIME_BALL, Aspect.BEAST, 2);
		add(ItemTypes.STONEBRICK, Aspect.STONE, 2);
		add(ItemTypes.STONE_BRICK_STAIRS, Aspect.STONE, 2);
		add(ItemTypes.SNOW, Aspect.ICE, 4);
		add(ItemTypes.SNOWBALL, Aspect.ICE, 1);
		add(ItemTypes.SOUL_SAND, Aspect.LIFE, 1, Aspect.EARTH, 1, Aspect.EVIL, 1, Aspect.DARKNESS, 1);
		add(ItemTypes.SPECKLED_MELON, Aspect.LIFE, 4, Aspect.PLANT, 1);
		add(ItemTypes.SPIDER_EYE, Aspect.BEAST, 2, Aspect.FLESH, 1);
		add(ItemTypes.SPRUCE_STAIRS, Aspect.WOOD, 2);
		add(ItemTypes.STAINED_HARDENED_CLAY, Aspect.STONE, 2);
		add(ItemTypes.STAINED_GLASS, Aspect.CLEAR, 3);
		add(ItemTypes.STAINED_GLASS_PANE, Aspect.CLEAR, 1);
		add(ItemTypes.STONE_SLAB, Aspect.STONE, 1);
		add(ItemTypes.STONE_SLAB2, Aspect.STONE, 1);
		add(ItemTypes.STICK, Aspect.WOOD, 1);
		add(ItemTypes.STICKY_PISTON, Aspect.MACHINE, 8, Aspect.STONE, 8, Aspect.WOOD, 4, Aspect.ENERGY, 4,
				Aspect.MOVEMENT, 4, Aspect.BEAST, 2);
		add(ItemTypes.STONE, Aspect.STONE, 2);
		add(ItemTypes.STONE_AXE, Aspect.STONE, 6, Aspect.TOOL, 1);
		add(ItemTypes.STONE_BUTTON, Aspect.STONE, 2, Aspect.MACHINE, 1);
		add(ItemTypes.STONE_HOE, Aspect.STONE, 4, Aspect.TOOL, 1);
		add(ItemTypes.STONE_PICKAXE, Aspect.STONE, 6, Aspect.TOOL, 1);
		add(ItemTypes.STONE_PRESSURE_PLATE, Aspect.STONE, 4, Aspect.MACHINE, 4);
		add(ItemTypes.STONE_SHOVEL, Aspect.STONE, 2, Aspect.TOOL, 1);
		add(ItemTypes.STONE_SWORD, Aspect.STONE, 4, Aspect.WEAPON, 1);
		add(ItemTypes.STONE_STAIRS, Aspect.DESTRUCTION, 1, Aspect.STONE, 1);
		add(ItemTypes.STRING, Aspect.BEAST, 1);
		add(ItemTypes.SUGAR, Aspect.PLANT, 1);
		
		add(ItemTypes.TALLGRASS, Aspect.WIND, 1, Aspect.PLANT, 1);
		add(ItemTypes.TNT, Aspect.DESTRUCTION, 16, Aspect.ENERGY, 8);
		add(ItemTypes.TORCH, Aspect.LIGHT, 1);
		add(ItemTypes.TRAPDOOR, Aspect.MOVEMENT, 2, Aspect.MACHINE, 2, Aspect.WOOD, 4);
		add(ItemTypes.TRAPPED_CHEST, Aspect.WOOD, 16, Aspect.VOID, 4, Aspect.MACHINE, 2);
		add(ItemTypes.TRIPWIRE_HOOK, Aspect.WOOD, 2, Aspect.METAL, 4, Aspect.MACHINE, 2);
		
		add(ItemTypes.VINE, Aspect.PLANT, 2);
		
		add(ItemTypes.WATER_BUCKET, Aspect.METAL, 24, Aspect.WATER, 8);
		add(ItemTypes.WATERLILY, Aspect.PLANT, 2, Aspect.WATER, 4);
		add(ItemTypes.WHEAT, Aspect.LIFE, 1, Aspect.PLANT, 1);
		add(ItemTypes.WHEAT_SEEDS, Aspect.PLANT, 1);
		add(ItemTypes.WOODEN_AXE, Aspect.WOOD, 4, Aspect.TOOL, 1);
		add(ItemTypes.WOODEN_BUTTON, Aspect.WOOD, 1, Aspect.MACHINE, 1);
		add(ItemTypes.WOODEN_DOOR, Aspect.WOOD, 4, Aspect.MACHINE, 1);
		add(ItemTypes.WOODEN_HOE, Aspect.WOOD, 4, Aspect.TOOL, 1);
		add(ItemTypes.WOODEN_PICKAXE, Aspect.WOOD, 6, Aspect.TOOL, 1);
		add(ItemTypes.WOODEN_PRESSURE_PLATE, Aspect.WOOD, 4, Aspect.MACHINE, 4);
		add(ItemTypes.WOODEN_SHOVEL, Aspect.WOOD, 2, Aspect.TOOL, 1);
		add(ItemTypes.WOODEN_SLAB, Aspect.WOOD, 1);
		add(ItemTypes.WOODEN_SWORD, Aspect.WOOD, 4, Aspect.WEAPON, 1);
		add(ItemTypes.WOODEN_DOOR, Aspect.WOOD, 4, Aspect.MACHINE, 1);
		add(ItemTypes.WOOL, Aspect.BEAST, 2);
		add(ItemTypes.WRITABLE_BOOK, Aspect.KNOWLEDGE, 8, Aspect.BEAST, 1, Aspect.PLANT, 4);
		add(ItemTypes.WRITTEN_BOOK, Aspect.KNOWLEDGE, 8, Aspect.PLANT, 4);
		
		add(ItemTypes.YELLOW_FLOWER, Aspect.PLANT, 1);
		
		
		for (Field field : ItemTypes.class.getDeclaredFields()) {
			if (java.lang.reflect.Modifier.isStatic(field.getModifiers())) {
		        if (field.getType().isAssignableFrom(ItemType.class)) {
		        	try {
						ItemType type = (ItemType) field.get(null);
						if (!this.itemTypeList.contains(type)) {
							System.out.println(type.getName());
						}
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					}
		        }
		    }
		}
		
	}
	
	@Override
	public void onDisable() {}
	
	private void add(ItemType type, Object... list) {
		AspectList aspects = new AspectList();
		for (int i = 0; i < list.length; i += 2) {
			if (list.length <= i + 1 || !(list[i] instanceof Aspect) || !(list[i + 1] instanceof Integer)) {
				continue;
			}
			aspects.add((Aspect)list[i], (Integer)list[i+1]);
		}
		add(type, aspects);
	}
	
	private void add(ItemType type, AspectList list) {
		Aspect.getAspectMap().put(type.getName(), list);
		this.itemTypeList.add(type);
	}
	
}
