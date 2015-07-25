package com.minnymin.zephyr.api.item;

import com.minnymin.zephyr.api.user.User;

public interface ActionItem extends Item {

	public void onLeftClick(User user);
	public void onRightClick(User user);
		
}
