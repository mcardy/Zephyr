package com.minnymin.zephyr.user;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import com.minnymin.zephyr.Manager;

public abstract class UserManager implements Manager {

	protected Set<User> userSet;
	
	@Override
	public void onEnable() {
		this.userSet = new HashSet<User>();
	}
	
	@Override
	public void onDisable() {
	}
	
	public User getUser(UUID id) {
		for (User user : this.userSet) {
			if (user.getUUID().equals(id)) {
				return user;
			}
		}
		return null;
	}
	
}
