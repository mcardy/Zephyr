package com.minnymin.zephyr.bukkit.nms.packet;

import org.bukkit.entity.Player;

import com.minnymin.zephyr.bukkit.nms.NMSUtils;
import com.minnymin.zephyr.bukkit.util.reflection.InjectionUtils;
import com.minnymin.zephyr.bukkit.util.reflection.ReflectionUtils;

public class Packet {

	private Object nmsPacket;
	
	public Packet(String cls) {
		nmsPacket = NMSUtils.getNMSObject(cls);
	}
	
	public void setValue(Class<?> fieldType, int index, Object value) {
		InjectionUtils.setField(nmsPacket.getClass(), fieldType, index, nmsPacket, value);
	}
	
	public void send(Player player) {
		try {
			Object nmsPlayer = NMSUtils.getHandle(player);
			Object connection = nmsPlayer.getClass().getField("playerConnection").get(nmsPlayer);
			ReflectionUtils.getMethod(connection.getClass(), "sendPacket").invoke(connection, nmsPacket);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
}
