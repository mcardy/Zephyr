package com.minnymin.zephyr.common.user;

import com.minnymin.zephyr.api.user.User;
import com.minnymin.zephyr.api.user.UserState;


public abstract class BaseUserState implements UserState {

	private String name;
	private String description;
	private int tickTime;
	
	public BaseUserState(String name, String description, int tickTime) {
		this.name = name;
		this.description = description;
		this.tickTime = tickTime;
	}
	
	@Override
	public String getName() {
		return this.name;
	}
	
	@Override
	public String getDescription() {
		return this.description;
	}
	
	@Override
	public int getTickTime() {
		return tickTime;
	}
	
	@Override
	public abstract void onApplied(User user);
	@Override
	public abstract void onRemoved(User user);
	@Override
	public abstract void tick(User user);
	
}
