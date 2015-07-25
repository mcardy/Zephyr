package com.minnymin.zephyr.api;

public interface ZephyrAPI {
	
	public void addManager(Manager manager);
	public <T extends Manager> T getManager(Class<T> cls);
		
}
