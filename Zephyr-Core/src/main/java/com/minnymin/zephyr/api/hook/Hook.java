package com.minnymin.zephyr.api.hook;

public interface Hook {

	public boolean isProvided();
	
	public void load();
	public void unload();
	
}
