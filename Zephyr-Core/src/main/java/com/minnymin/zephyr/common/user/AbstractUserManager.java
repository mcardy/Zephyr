package com.minnymin.zephyr.common.user;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import com.minnymin.zephyr.api.user.User;
import com.minnymin.zephyr.api.user.UserManager;

public abstract class AbstractUserManager implements UserManager {

	protected Set<User> userSet = new HashSet<User>();
	
	@Override
	public void onEnable() {
	}
	
	@Override
	public void onDisable() {
		for (User user : this.userSet) {
			user.logout();
		}
	}
	
	@Override
	public User getUser(UUID id) {
		for (User user : this.userSet) {
			if (user.getUUID().equals(id)) {
				return user;
			}
		}
		return null;
	}
	
}
