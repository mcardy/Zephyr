package com.minnymin.zephyr.api.user;

public interface UserState {

	public String getDescription();

	public String getName();

	public int getTickTime();

	public void onApplied(User user);

	public void onRemoved(User user);

	public void tick(User user);

}