package com.minnymin.zephyr.bukkit.user;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.minnymin.zephyr.user.User;

public class BukkitUser extends User {

	private UUID uuid;
	
	protected BukkitUser(Player player) {
		super(new BukkitUserData(player.getUniqueId()));
		this.uuid = player.getUniqueId();
	}
	
	public Player getPlayer() {
		return Bukkit.getPlayer(this.uuid);
	}

	@Override
	public UUID getUUID() {
		return uuid;
	}

	@Override
	@SuppressWarnings("unchecked")
	public <T> T getPlayerObject() {
		try {
			return (T) getPlayer();
		} catch (ClassCastException ex) {
			Bukkit.getLogger().warning("[Zephyr] Unable to get player object from user: " + ex.getMessage());
			return null;
		}
	}

	@Override
	public void sendMessage(String... message) {
		for (int i = 0; i < message.length; i++) {
			getPlayer().sendMessage(message);
		}
	}
	
}
