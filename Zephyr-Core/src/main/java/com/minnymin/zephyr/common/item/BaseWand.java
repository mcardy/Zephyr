package com.minnymin.zephyr.common.item;

import com.minnymin.zephyr.api.item.WandItem;
import com.minnymin.zephyr.api.util.ColorCharacter;

public abstract class BaseWand extends BaseActionItem implements WandItem {

	public BaseWand(int id, String name, ColorCharacter color, String... lore) {
		super(id, name, color, lore);
	}

}
