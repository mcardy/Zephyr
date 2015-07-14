package com.minnymin.zephyr.sponge.user;

import java.util.UUID;

import org.spongepowered.api.entity.player.Player;
import org.spongepowered.api.text.Texts;

import com.minnymin.zephyr.sponge.ZephyrPlugin;
import com.minnymin.zephyr.user.User;

public class SpongeUser extends User {

	private UUID uuid;
	
	public SpongeUser(Player player) {
		super(new SpongeUserData(player.getUniqueId()));
		this.uuid = player.getUniqueId();
	}
	
	public Player getPlayer() {
		return ZephyrPlugin.getGame().getServer().getPlayer(uuid).get();
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public <T> T getPlayerObject() {
		try {
			return (T) getPlayer();
		} catch (ClassCastException ex) {
			ZephyrPlugin.getLogger().warn("Unable to get player object from user: " + ex.getMessage());
			return null;
		}
	}

	@Override
	public UUID getUUID() {
		return uuid;
	}

	@Override
	public void sendMessage(String... message) {
		for (int i = 0; i < message.length; i++) {
			ZephyrPlugin.getGame().getServer().getPlayer(uuid).get().sendMessage(Texts.of(message[i]));
		}
	}
	
}
