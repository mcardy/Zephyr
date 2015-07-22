package com.minnymin.zephyr.common.item;

import java.util.ArrayList;
import java.util.List;

import com.minnymin.zephyr.api.item.Item;
import com.minnymin.zephyr.api.item.ItemRecipe;
import com.minnymin.zephyr.api.util.ColorCode;

public abstract class BaseItem implements Item {

	private List<String> lore;
	private String name;
	private ColorCode color;
	
	private int id;
	private int data;
	
	public BaseItem(int id, String name, ColorCode color, String... lore) {
		this(id, 0, name, color, lore);
	}
	
	public BaseItem(int id, int data, String name, ColorCode color, String... lore) {
		this.id = id;
		this.data = data;
		this.name = name;
		this.color = color;
		this.lore = new ArrayList<String>();
		for (String s : lore) {
			this.lore.add(s);
		}
	}
	
	@Override
	public int getItemId() {
		return this.id;
	}
	
	@Override
	public int getItemData() {
		return this.data;
	}
	
	@Override
	public String getName() {
		return this.name;
	}
	
	@Override
	public String getItemName() {
		return this.name;
	}
	
	@Override
	public ColorCode getItemNameColor() {
		return this.color;
	}
	
	@Override
	public List<String> getItemLore() {
		return this.lore;
	}
	
	@Override
	public ItemRecipe getRecipe() {
		return null;
	}
	
}
