package com.minnymin.zephyr.bukkit.nms.packet;

public class PacketEntityDestroy extends Packet {

	public PacketEntityDestroy(int id) {
		super("PacketPlayOutEntityDestroy");
		setValue(int[].class, 0, new int[] {id});
	}

}
