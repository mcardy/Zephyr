package com.minnymin.zephyr.common.item;

import com.minnymin.zephyr.api.item.ActionItem;
import com.minnymin.zephyr.api.user.User;
import com.minnymin.zephyr.api.util.ColorCharacter;

public abstract class BaseActionItem extends BaseItem implements ActionItem {

	public BaseActionItem(int id, String name, ColorCharacter color, String... lore) {
		super(id, name, color, lore);
	}

	@Override
	public void onLeftClick(User user) {		
	}
	
	@Override
	public void onRightClick(User user) {		
	}
	
}
