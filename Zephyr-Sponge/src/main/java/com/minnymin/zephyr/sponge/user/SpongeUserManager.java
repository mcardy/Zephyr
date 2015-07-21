package com.minnymin.zephyr.sponge.user;

import org.spongepowered.api.entity.player.Player;
import org.spongepowered.api.event.Subscribe;
import org.spongepowered.api.event.entity.player.PlayerJoinEvent;
import org.spongepowered.api.event.entity.player.PlayerQuitEvent;

import com.minnymin.zephyr.api.Zephyr;
import com.minnymin.zephyr.api.user.User;
import com.minnymin.zephyr.common.user.AbstractUserManager;
import com.minnymin.zephyr.sponge.ZephyrPlugin;

public class SpongeUserManager extends AbstractUserManager {

	@Override
	public void onEnable() {
		super.onEnable();
		ZephyrPlugin.getGame().getEventManager().register(ZephyrPlugin.getInstance(), this);
		ZephyrPlugin.getGame().getScheduler().getTaskBuilder().interval(1).execute(new SpongeUserRunnable())
				.name("zephyr:usertick").submit(Zephyr.getAPI());
	}
	
	@Subscribe
	public void onUserConnect(PlayerJoinEvent event) {
		Player player = event.getEntity();
		this.userSet.add(new SpongeUser(player));
	}

	@Subscribe
	public void onUserDisconnect(PlayerQuitEvent event) {
		Player player = event.getEntity();
		this.userSet.remove(this.getUser(player.getUniqueId()));
	}

	private class SpongeUserRunnable implements Runnable {
		@Override
		public void run() {
			for (User u : userSet) {
				u.tick();
			}
		}
	}
}
