package com.minnymin.zephyr.bukkit.item;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.inventory.meta.ItemMeta;

import com.minnymin.zephyr.api.Zephyr;
import com.minnymin.zephyr.api.item.ActionItem;
import com.minnymin.zephyr.api.item.Item;
import com.minnymin.zephyr.api.item.ItemManager;
import com.minnymin.zephyr.api.item.ItemRecipe;
import com.minnymin.zephyr.api.item.ItemRecipe.ItemRecipeIngredient;
import com.minnymin.zephyr.bukkit.ZephyrPlugin;

public class BukkitItemManager implements ItemManager, Listener {

	private Map<String, Item> itemMap;
	private Map<String, ActionItem> actionMap;
	
	@Override
	public void onEnable() {	
		itemMap = new HashMap<String, Item>();
		actionMap = new HashMap<String, ActionItem>();
		
		registerItem(new Wand());
		
		Bukkit.getPluginManager().registerEvents(this, ZephyrPlugin.getInstance());
	}
	
	@Override
	public void onDisable() {
	}
	
	@Override
	public Item getItem(Object obj) {
		if (!(obj instanceof ItemStack)) {
			return null;
		}
		ItemStack stack = (ItemStack) obj;
		String name = getDisplayName(stack);
		if (!itemMap.containsKey(name)) {
			return null;
		}
		return itemMap.get(name);
	}
	
	public ActionItem getActionItem(Object stack) {
		if (!(stack instanceof ItemStack)) {
			return null;
		}
		String name = getDisplayName((ItemStack)stack);
		if (!actionMap.containsKey(name)) {
			return null;
		}
		return actionMap.get(name);
	}

	@Override
	public Item getItem(String name) {
		return itemMap.get(name);
	}

	@Override
	public void registerItem(Item item) {
		this.itemMap.put(item.getItemName(), item);
		if (item instanceof ActionItem) {
			this.actionMap.put(item.getItemName(), (ActionItem)item);
		}
		if (item instanceof Listener) {
			Bukkit.getPluginManager().registerEvents((Listener)item, ZephyrPlugin.getInstance());
		}
		
		if (item.getRecipe() != null) {
			@SuppressWarnings("deprecation")
			ItemStack stack = new ItemStack(item.getItemId(), 1);
			ItemMeta meta = stack.getItemMeta();
			meta.setDisplayName(item.getItemNameColor() + item.getItemName());
			meta.setLore(item.getItemLore());
			stack.setItemMeta(meta);
			stack.setDurability((short)item.getItemData());
			Recipe recipe = createRecipe(item.getRecipe(), stack);
			Bukkit.addRecipe(recipe);
		}
	}
	
	@EventHandler
	public void onInteract(PlayerInteractEvent event) {
		if (event.getItem() != null) {
			ActionItem item = this.getActionItem(event.getItem());
			if (item != null) {
				item.onInteract(Zephyr.getUserManager().getUser(event.getPlayer().getUniqueId()));
			}
		}
	}
	
	private String getDisplayName(ItemStack item) {
		String name = item.hasItemMeta() && item.getItemMeta().hasDisplayName() ? item.getItemMeta().getDisplayName() : "";
		return ChatColor.stripColor(name);
	}
	
	@SuppressWarnings("deprecation")
	private Recipe createRecipe(ItemRecipe itemRecipe, ItemStack output) {
		if (itemRecipe.isShapeless()) {
			ShapelessRecipe recipe = new ShapelessRecipe(output);
			for (Entry<Character, ItemRecipeIngredient> entry : itemRecipe.getIngredients().entrySet()) {
				recipe.addIngredient(Material.getMaterial(entry.getValue().getId()), entry.getValue().getData());
			}
			return recipe;
		} else {
			ShapedRecipe recipe = new ShapedRecipe(output);
			recipe.shape(itemRecipe.getShape());
			for (Entry<Character, ItemRecipeIngredient> entry : itemRecipe.getIngredients().entrySet()) {
				recipe.setIngredient(entry.getKey(), Material.getMaterial(entry.getValue().getId()), entry.getValue().getData());
			}
			return recipe;
		}
	}

}
