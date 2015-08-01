package com.minnymin.zephyr.api.util;

import java.util.UUID;

public class Vector3i {

	private int x;
	private int y;
	private int z;
	
	private UUID world;
	
	public Vector3i(int x, int y, int z, UUID world) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.world = world;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getZ() {
		return z;
	}

	public UUID getWorld() {
		return world;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void setZ(int z) {
		this.z = z;
	}

	public void setWorld(UUID world) {
		this.world = world;
	}
	
	
	
}
