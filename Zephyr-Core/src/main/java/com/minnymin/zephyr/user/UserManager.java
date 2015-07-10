package com.minnymin.zephyr.user;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public abstract class UserManager {

	protected Set<User> userSet;
	
	public UserManager() {
		this.userSet = new HashSet<User>();
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
