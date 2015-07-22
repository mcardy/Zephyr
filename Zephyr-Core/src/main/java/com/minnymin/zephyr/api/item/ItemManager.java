package com.minnymin.zephyr.api.item;

import com.minnymin.zephyr.api.Manager;

public interface ItemManager extends Manager {

	public Item getItem(Object itemStack);
	public Item getItem(String name);
	
	public void registerItem(Item item);
	
}
