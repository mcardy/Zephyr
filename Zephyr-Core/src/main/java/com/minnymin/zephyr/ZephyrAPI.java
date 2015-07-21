package com.minnymin.zephyr;

public interface ZephyrAPI {
	
	public void addManager(Manager manager);
	public <T extends Manager> T getManager(Class<T> cls);
	
}
