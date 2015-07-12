package com.minnymin.zephyr.user;


public abstract class UserState {

	private String name;
	private String description;
	private int tickTime;
	
	public UserState(String name, String description, int tickTime) {
		this.name = name;
		this.description = description;
		this.tickTime = tickTime;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getDescription() {
		return this.description;
	}
	
	public int getTickTime() {
		return tickTime;
	}
	
	public abstract void onApplied(User user);
	public abstract void onRemoved(User user);
	public abstract void tick(User user);
	
}
