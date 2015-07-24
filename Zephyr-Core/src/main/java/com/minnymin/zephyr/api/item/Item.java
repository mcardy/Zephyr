package com.minnymin.zephyr.api.item;

import java.util.List;

import com.minnymin.zephyr.api.util.ColorCharacter;

public interface Item {
	
	public String getName();
	
	public String getItemName();
	public ColorCharacter getItemNameColor();
	public List<String> getItemLore();
	
	public int getItemId();
	public int getItemData();
	public ItemRecipe getRecipe();
	
}
