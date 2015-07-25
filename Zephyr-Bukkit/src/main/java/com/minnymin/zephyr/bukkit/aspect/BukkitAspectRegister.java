package com.minnymin.zephyr.bukkit.aspect;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.ShapelessRecipe;

import com.minnymin.zephyr.api.Manager;
import com.minnymin.zephyr.api.aspect.Aspect;
import com.minnymin.zephyr.api.aspect.AspectList;
import com.minnymin.zephyr.bukkit.ZephyrPlugin;

public class BukkitAspectRegister implements Manager {

	private Map<Material, AspectList> modifications;
	
	@Override
	public void onEnable() {
		ZephyrPlugin.logger().info("Calculating aspects...");
		
		addBaseItems();
		addModificationItems();
		predictItems();
	}
	
	// Adds base items (wood, stone, etc.)
	private void addBaseItems() {
		// Adding non-craftable items starting from id 1
		// Blocks
		add(Material.STONE, Aspect.STONE, 2);
		add(Material.DIRT, Aspect.EARTH, 1);
		add(Material.COBBLESTONE, Aspect.STONE, 1, Aspect.DESTRUCTION, 1);
		add(Material.SAPLING, Aspect.LIFE, 2, Aspect.WOOD, 1);
		add(Material.SAND, Aspect.EARTH, 1);
		add(Material.GRAVEL, Aspect.EARTH, 1, Aspect.STONE, 1);
		add(Material.LOG, Aspect.WOOD, 8);
		add(Material.LOG_2, Aspect.WOOD, 8);
		add(Material.LEAVES, Aspect.LIFE, 1);
		add(Material.LEAVES_2, Aspect.LIFE, 1);
		add(Material.SPONGE, Aspect.LIFE, 2, Aspect.WATER, 1);
		add(Material.GLASS, Aspect.CLEAR, 2);
		add(Material.DEAD_BUSH, Aspect.LIFE, 1, Aspect.DEATH, 1);
		add(Material.WOOL, Aspect.BEAST, 1);
		add(Material.YELLOW_FLOWER, Aspect.LIFE, 1);
		add(Material.RED_ROSE, Aspect.LIFE, 1);
		add(Material.RED_MUSHROOM, Aspect.LIFE, 1, Aspect.DARKNESS, 1);
		add(Material.BROWN_MUSHROOM, Aspect.LIFE, 1, Aspect.DARKNESS, 1);
		add(Material.MOSSY_COBBLESTONE, Aspect.STONE, 1, Aspect.LIFE, 1);
		add(Material.OBSIDIAN, Aspect.DARKNESS, 1, Aspect.STONE, 1);
		add(Material.HARD_CLAY, Aspect.STONE, 2);
		add(Material.ICE, Aspect.ICE, 2);
		add(Material.CACTUS, Aspect.LIFE, 1, Aspect.WEAPON, 1);
		add(Material.SUGAR_CANE, Aspect.LIFE, 1);
		add(Material.PUMPKIN, Aspect.LIFE, 2);
		add(Material.NETHERRACK, Aspect.STONE, 1, Aspect.FIRE, 1);
		add(Material.SOUL_SAND, Aspect.DARKNESS, 1, Aspect.EARTH, 1);
		add(Material.STAINED_GLASS, Aspect.CLEAR, 2);
		add(Material.VINE, Aspect.LIFE, 1);
		add(Material.MYCEL, Aspect.LIFE, 4, Aspect.EARTH, 2, Aspect.MAGIC, 1);
		add(Material.WATER_LILY, Aspect.LIFE, 1, Aspect.WATER, 1);
		add(Material.NETHER_WARTS, Aspect.LIFE, 1, Aspect.FIRE, 1);
		add(Material.ENDER_STONE, Aspect.STONE, 1, Aspect.ENDER, 1);
		add(Material.STAINED_CLAY, Aspect.STONE, 2, Aspect.EARTH, 1);
		add(Material.TORCH, Aspect.LIGHT, 1);

		// Items
		add(Material.APPLE, Aspect.LIFE, 2);
		add(Material.COAL, Aspect.STONE, 1, Aspect.FIRE, 4);
		add(Material.DIAMOND, Aspect.VALUE, 16, Aspect.CLEAR, 4);
		add(Material.IRON_INGOT, Aspect.METAL, 4);
		add(Material.GOLD_INGOT, Aspect.METAL, 2, Aspect.VALUE, 2);
		add(Material.STRING, Aspect.BEAST, 1);
		add(Material.FEATHER, Aspect.BEAST, 2, Aspect.WIND, 4);
		add(Material.SULPHUR, Aspect.DESTRUCTION, 2);
		add(Material.SEEDS, Aspect.LIFE, 1);
		add(Material.WHEAT, Aspect.LIFE, 2);
		add(Material.FLINT, Aspect.STONE, 1, Aspect.TOOL, 1);
		add(Material.RAW_BEEF, Aspect.LIFE, 2, Aspect.BEAST, 2);
		add(Material.RAW_CHICKEN, Aspect.LIFE, 2, Aspect.BEAST, 2);
		add(Material.PORK, Aspect.LIFE, 2, Aspect.BEAST, 2);
		add(Material.RAW_FISH, Aspect.LIFE, 2, Aspect.BEAST, 1);
		add(Material.MUTTON, Aspect.LIFE, 2, Aspect.BEAST, 1);
		add(Material.RABBIT, Aspect.LIFE, 2, Aspect.BEAST, 1);
		add(Material.GRILLED_PORK, Aspect.LIFE, 2, Aspect.BEAST, 1, Aspect.FIRE, 1);
		add(Material.COOKED_BEEF, Aspect.LIFE, 2, Aspect.BEAST, 1, Aspect.FIRE, 1);
		add(Material.COOKED_CHICKEN, Aspect.LIFE, 2, Aspect.BEAST, 1, Aspect.FIRE, 1);
		add(Material.COOKED_FISH, Aspect.LIFE, 2, Aspect.BEAST, 1, Aspect.FIRE, 1);
		add(Material.COOKED_MUTTON, Aspect.LIFE, 2, Aspect.BEAST, 1, Aspect.FIRE, 1);
		add(Material.COOKED_RABBIT, Aspect.LIFE, 2, Aspect.BEAST, 1, Aspect.FIRE, 1);
		add(Material.WATER_BUCKET, Aspect.WATER, 4, Aspect.METAL, 12);
		add(Material.LAVA_BUCKET, Aspect.FIRE, 4, Aspect.METAL, 12);
		add(Material.MILK_BUCKET, Aspect.LIFE, 2, Aspect.METAL, 12);
		add(Material.SADDLE, Aspect.BEAST, 4);
		add(Material.REDSTONE, Aspect.MACHINE, 1);
		add(Material.GLOWSTONE_DUST, Aspect.LIGHT, 1);
		add(Material.SNOW_BALL, Aspect.ICE, 1);
		add(Material.LEATHER, Aspect.BEAST, 1);
		add(Material.CLAY_BALL, Aspect.EARTH, 1);
		add(Material.CLAY_BRICK, Aspect.STONE, 1);
		add(Material.SUGAR_CANE, Aspect.LIFE, 1);
		add(Material.SLIME_BALL, Aspect.BEAST, 1);
		add(Material.EGG, Aspect.LIFE, 2, Aspect.BEAST, 1);
		addBase(Material.INK_SACK, new AspectList());
		add(Material.BONE, Aspect.BEAST, 3);
		add(Material.PUMPKIN_SEEDS, Aspect.LIFE, 1);
		add(Material.MELON, Aspect.LIFE, 1);
		add(Material.ENDER_PEARL, Aspect.ENDER, 8, Aspect.MAGIC, 4);
		add(Material.BLAZE_ROD, Aspect.FIRE, 4, Aspect.MAGIC, 4);
		add(Material.SPIDER_EYE, Aspect.BEAST, 2, Aspect.DEATH, 1);
		add(Material.GHAST_TEAR, Aspect.BEAST, 1, Aspect.MAGIC, 2);
		add(Material.NETHER_WARTS, Aspect.FIRE, 2, Aspect.LIFE, 1, Aspect.MAGIC, 1);
		add(Material.EMERALD, Aspect.VALUE, 8, Aspect.CLEAR, 2);
		add(Material.CARROT_ITEM, Aspect.LIFE, 1);
		add(Material.POTATO_ITEM, Aspect.LIFE, 1);
		add(Material.BAKED_POTATO, Aspect.LIFE, 1, Aspect.FIRE, 1);
		add(Material.POISONOUS_POTATO, Aspect.DEATH, 2);
		add(Material.SKULL_ITEM, Aspect.DEATH, 4, Aspect.DARKNESS, 4, Aspect.BEAST, 2);
		add(Material.NETHER_BRICK_ITEM, Aspect.STONE, 1, Aspect.DARKNESS, 1);
		add(Material.QUARTZ, Aspect.STONE, 1);
		add(Material.PRISMARINE_SHARD, Aspect.WATER, 2, Aspect.STONE, 1);
		add(Material.PRISMARINE_CRYSTALS, Aspect.WATER, 2, Aspect.STONE, 1, Aspect.LIGHT, 1);
		add(Material.RABBIT_FOOT, Aspect.BEAST, 1, Aspect.MAGIC, 2);
		
		// All aspects in nether star & dragon egg
		AspectList list = new AspectList();
		for (Aspect aspect : Aspect.values()) {
			list.add(aspect, 128);
		}
		addBase(Material.NETHER_STAR, list);
		addBase(Material.DRAGON_EGG, list);
		
	}

	// Modifies items (crafting to crafting table)
	private void addModificationItems() {
		this.modifications = new HashMap<Material, AspectList>();
		
		modify(Material.WORKBENCH, AspectList.build(Aspect.CONSTRUCT, 4));
		modify(Material.CHEST, AspectList.build(Aspect.VOID, 4));
		modify(Material.FURNACE, AspectList.build(Aspect.FIRE, 2));
		modify(Material.LADDER, AspectList.build(Aspect.MOVEMENT, 1));
		modify(Material.BOAT, AspectList.build(Aspect.MOVEMENT, 2).add(Aspect.WATER, 4));
		modify(Material.SANDSTONE, AspectList.build(Aspect.STONE, 1));
		modify(Material.GLOWSTONE, AspectList.build(Aspect.STONE, 1));
		modify(Material.PAPER, AspectList.build(Aspect.KNOWLEDGE, 1));
		modify(Material.BOW, AspectList.build(Aspect.WEAPON, 4));
		modify(Material.ARROW, AspectList.build(Aspect.WEAPON, 1));
		modify(Material.FLINT_AND_STEEL, AspectList.build(Aspect.TOOL, 1).add(Aspect.FIRE, 2));
		modify(Material.FISHING_ROD, AspectList.build(Aspect.TOOL, 2));
		modify(Material.SHEARS, AspectList.build(Aspect.TOOL, 2));
		modify(Material.WATCH, AspectList.build(Aspect.TIME, 8));
		modify(Material.WOOD_PLATE, AspectList.build(Aspect.MACHINE, 1));
		modify(Material.STONE_PLATE, AspectList.build(Aspect.MACHINE, 1));
		modify(Material.IRON_PLATE, AspectList.build(Aspect.MACHINE, 1));
		modify(Material.GOLD_PLATE, AspectList.build(Aspect.MACHINE, 1));
		modify(Material.LEVER, AspectList.build(Aspect.MACHINE, 1));
		modify(Material.WOOD_BUTTON, AspectList.build(Aspect.MACHINE, 1));
		modify(Material.STONE_BUTTON, AspectList.build(Aspect.MACHINE, 1));
		modify(Material.TRAP_DOOR, AspectList.build(Aspect.MOVEMENT, 2));
		modify(Material.IRON_TRAPDOOR, AspectList.build(Aspect.MOVEMENT, 2));
		modify(Material.PISTON_BASE, AspectList.build(Aspect.MOVEMENT, 2));
		modify(Material.PISTON_STICKY_BASE, AspectList.build(Aspect.MOVEMENT, 2));
		modify(Material.DAYLIGHT_DETECTOR, AspectList.build(Aspect.MACHINE, 4).add(Aspect.LIGHT, 2));
		modify(Material.TRAPPED_CHEST, AspectList.build(Aspect.MACHINE, 2));
		modify(Material.CAULDRON_ITEM, AspectList.build(Aspect.VOID, 4));
		modify(Material.GLASS_BOTTLE, AspectList.build(Aspect.VOID, 1));
		modify(Material.ENDER_CHEST, AspectList.build(Aspect.VOID, 4));
		modify(Material.ANVIL, AspectList.build(Aspect.CONSTRUCT, 8));
		
		modify(Material.BUCKET, AspectList.build(Aspect.VOID, 2));
	}
	
	// Predicts items (doors, stairs, etc.)
	private void predictItems() {
		List<Material> qued = new ArrayList<Material>();
		int lastLength = 0;
		for (Material mat : Material.values()) {
			if (!Aspect.getAspectMap().containsKey(mat.toString())) {
				qued.add(mat);
			}
		}
		while (!qued.isEmpty() && lastLength != qued.size()) {
			lastLength = qued.size();
			List<Material> toRemove = new ArrayList<Material>();
			for (Material mat : qued) {
				List<Recipe> recipes = Bukkit.getRecipesFor(new ItemStack(mat));
				AspectList list = new AspectList();
				
				// Move on if no recipes
				if (recipes.size() == 0) {
					toRemove.add(mat);
					continue;
				}
				
				// We only care about one recipe
				Recipe recipe = recipes.get(0);
				Collection<ItemStack> items;

				// Gets the items into a collection
				if (recipe instanceof ShapedRecipe) {
					items = ((ShapedRecipe) recipe).getIngredientMap().values();
				} else if (recipe instanceof ShapelessRecipe) {
					items = ((ShapelessRecipe) recipe).getIngredientList();
				} else {
					// Not a shaped or shapeless recipe? remove and
					// continue.
					toRemove.add(mat);
					continue;
				}

				// Quit if an item in the recipe is not yet predicted
				boolean quit = false;
				int divider = recipe.getResult().getAmount();
				Map<Aspect, Double> tempMap = new HashMap<Aspect, Double>();

				// Go through each item in the recipe
				for (ItemStack ingredient : items) {
					if (ingredient == null) {
						continue;
					}
					AspectList aspects = Aspect.getAspects(ingredient.getType().toString());
					if (aspects == null) {
						// No aspects? quit and break loop
						quit = true;
						break;
					}
					for (Entry<Aspect, Integer> e : aspects.getAspects().entrySet()) {
						if (tempMap.containsKey(e.getKey())) {
							tempMap.put(e.getKey(), tempMap.get(e.getKey())
									+ ((double) e.getValue() / (double) divider));
						} else {
							tempMap.put(e.getKey(), (double) e.getValue() / (double) divider);
						}
					}
				}

				// If not quitting, add items to list
				if (!quit) {
					for (Entry<Aspect, Double> e : tempMap.entrySet()) {
						if (e.getValue() >= 0.5) {
							list.add(e.getKey(), (int) Math.ceil(e.getValue()));
						}
					}
				} else {
					// If quitting, move on to the next item, do not add
					continue;
				}
				
				// Additional modifiers to the list, tools and such
				String name = mat.toString();
				if (name.endsWith("AXE") || name.endsWith("HOE") || name.endsWith("SPADE")) {
					list.add(Aspect.TOOL, 4);
				} else if (name.endsWith("BOOTS")) {
					list.add(Aspect.DEFENSE, 2);
				} else if (name.endsWith("LEGGINGS")) {
					list.add(Aspect.DEFENSE, 3);
				} else if (name.endsWith("CHESTPLATE")) {
					list.add(Aspect.DEFENSE, 4);
				} else if (name.endsWith("HELMET")) {
					list.add(Aspect.DEFENSE, 2);
				} else if (name.endsWith("SWORD")) {
					list.add(Aspect.WEAPON, 4);
				} else if (name.endsWith("BARDING")) {
					list.add(Aspect.DEFENSE, 4);
				}
				
				if (this.modifications.containsKey(mat)) {
					list.merge(this.modifications.get(mat));
				}
				
				Aspect.getAspectMap().put(mat.toString(), list);
				toRemove.add(mat);
			}
			for (Material mat : toRemove) {
				qued.remove(mat);
			}
		}

		// Debugging, print out all recipes that had issues and were left in que
		if (ZephyrPlugin.DEBUG) {
			System.out.println("--------- NO ASPECTS ----------");
			for (Material mat : Material.values()) {
				if (!Aspect.getAspectMap().containsKey(mat.toString())) {
					System.out.println(mat.toString());
				}
			}
		}

	}

	private void add(Material type, Object... list) {
		AspectList aspects = new AspectList();
		for (int i = 0; i < list.length; i += 2) {
			if (list.length <= i + 1 || !(list[i] instanceof Aspect) || !(list[i + 1] instanceof Integer)) {
				continue;
			}
			aspects.add((Aspect) list[i], (Integer) list[i + 1]);
		}
		addBase(type, aspects);
	}

	private void addBase(Material type, AspectList list) {
		Aspect.getAspectMap().put(type.toString(), list);
	}

	private void modify(Material type, AspectList list) {
		this.modifications.put(type, list);
	}

	@Override
	public void onDisable() {
	}

}
