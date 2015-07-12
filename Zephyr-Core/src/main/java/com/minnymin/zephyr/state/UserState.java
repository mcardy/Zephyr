package com.minnymin.zephyr.state;

import com.minnymin.zephyr.user.User;

public abstract class UserState {

	private String name;
	private String description;
	
	public UserState(String name, String description) {
		this.name = name;
		this.description = description;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getDescription() {
		return this.description;
	}
	
	public abstract void onApplied(User user);
	public abstract void onRemoved(User user);
	public abstract void tick(User user);
	
}
