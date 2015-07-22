package com.minnymin.zephyr.common.item;

import com.minnymin.zephyr.api.item.ActionItem;
import com.minnymin.zephyr.api.util.ColorCode;

public abstract class BaseActionItem extends BaseItem implements ActionItem {

	public BaseActionItem(int id, String name, ColorCode color, String... lore) {
		super(id, name, color, lore);
	}

}
