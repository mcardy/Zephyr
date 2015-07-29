package com.minnymin.zephyr.bukkit.user;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.minnymin.zephyr.common.user.AbstractUser;

public class BukkitUser extends AbstractUser {

	private UUID uuid;
	
	protected BukkitUser(Player player) {
		super(new BukkitUserData(player.getUniqueId()));
		this.uuid = player.getUniqueId();
	}

	@Override
	public UUID getUUID() {
		return uuid;
	}

	@Override
	@SuppressWarnings("unchecked")
	public <T> T getPlayer() {
		try {
			return (T) Bukkit.getPlayer(this.uuid);
		} catch (ClassCastException ex) {
			Bukkit.getLogger().warning("[Zephyr] Unable to get player object from user: " + ex.getMessage());
			return null;
		}
	}

	@Override
	public void sendMessage(String... message) {
		for (int i = 0; i < message.length; i++) {
			this.<Player>getPlayer().sendMessage(message);
		}
	}
	
}
