package com.minnymin.zephyr.bukkit.nms.packet;

import org.bukkit.Location;

import com.minnymin.zephyr.bukkit.nms.NMSUtils;
import com.minnymin.zephyr.bukkit.util.reflection.InjectionUtils;
import com.minnymin.zephyr.bukkit.util.reflection.ReflectionUtils;

public class PacketParticleEffect extends Packet {

	public PacketParticleEffect(String name, Location loc, float offx, float offy, float offz, float speed, int count) {
		this(name, (float) loc.getX(), (float) loc.getY(), (float) loc.getZ(), offx, offy, offz, speed, count);
	}

	public PacketParticleEffect(String name, float locx, float locy, float locz, float offx, float offy, float offz,
			float speed, int count) {
		super("PacketPlayOutWorldParticles");
		Class<?> enumParticle = NMSUtils.getNMSClass("EnumParticle");
		for (Object obj : (Object[]) ReflectionUtils.invokeStaticMethod(enumParticle, "values", new Class<?>[0])) {
			try {
				if (InjectionUtils.getField(enumParticle, String.class, 0).get(obj) == name) {
					setValue(enumParticle, 0, obj);
					break;
				}
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		setValue(float.class, 0, locx);
		setValue(float.class, 1, locy);
		setValue(float.class, 2, locz);
		setValue(float.class, 3, offx);
		setValue(float.class, 4, offy);
		setValue(float.class, 5, offz);
		setValue(float.class, 6, speed);
		setValue(int.class, 0, count);
	}

}
