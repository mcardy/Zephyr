package com.minnymin.zephyr.bukkit.user;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import com.minnymin.zephyr.api.user.User;
import com.minnymin.zephyr.bukkit.ZephyrPlugin;
import com.minnymin.zephyr.common.user.AbstractUserManager;

public class BukkitUserManager extends AbstractUserManager implements Listener {

	@Override
	public void onEnable() {
		super.onEnable();
		Bukkit.getPluginManager().registerEvents(this, ZephyrPlugin.getInstance());
		Bukkit.getScheduler().runTaskTimer(ZephyrPlugin.getInstance(), new BukkitUserRunnable(), 0, 1);
		
		for (Player player : Bukkit.getOnlinePlayers()) {
			this.userSet.add(new BukkitUser(player));
		}
	}
	
	@EventHandler
	public void onUserConnect(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		this.userSet.add(new BukkitUser(player));
	}

	@EventHandler
	public void onUserDisconnect(PlayerQuitEvent event) {
		User user = this.getUser(event.getPlayer().getUniqueId());
		user.logout();
		this.userSet.remove(user);
	}

	private class BukkitUserRunnable implements Runnable {
		@Override
		public void run() {
			for (User u : userSet) {
				u.tick();
			}
		}
	}
	
}
