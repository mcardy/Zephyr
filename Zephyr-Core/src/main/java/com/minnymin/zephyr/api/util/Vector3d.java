package com.minnymin.zephyr.api.util;

import java.util.UUID;

public class Vector3d {

	private double x;
	private double y;
	private double z;
	
	private UUID world;
	
	public Vector3d(double x, double y, double z, UUID world) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.world = world;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public double getZ() {
		return z;
	}

	public UUID getWorld() {
		return world;
	}

	public void setX(double x) {
		this.x = x;
	}

	public void setY(double y) {
		this.y = y;
	}

	public void setZ(double z) {
		this.z = z;
	}

	public void setWorld(UUID world) {
		this.world = world;
	}
	
}
