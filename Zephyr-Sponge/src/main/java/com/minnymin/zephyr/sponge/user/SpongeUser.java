package com.minnymin.zephyr.sponge.user;

import java.util.UUID;

import org.spongepowered.api.entity.player.Player;
import org.spongepowered.api.text.Texts;

import com.minnymin.zephyr.common.user.AbstractUser;
import com.minnymin.zephyr.sponge.ZephyrPlugin;

public class SpongeUser extends AbstractUser {

	private UUID uuid;
	
	public SpongeUser(Player player) {
		super(new SpongeUserData(player.getUniqueId()));
		this.uuid = player.getUniqueId();
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public <T> T getPlayer() {
		try {
			return (T) ZephyrPlugin.getGame().getServer().getPlayer(uuid).get();
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
			this.<Player>getPlayer().sendMessage(Texts.of(message[i]));
		}
	}
	
}
