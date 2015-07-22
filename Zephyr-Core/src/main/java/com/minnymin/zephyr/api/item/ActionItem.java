package com.minnymin.zephyr.api.item;

import com.minnymin.zephyr.api.user.User;

public interface ActionItem extends Item {

	public void onInteract(User user);
	
}
