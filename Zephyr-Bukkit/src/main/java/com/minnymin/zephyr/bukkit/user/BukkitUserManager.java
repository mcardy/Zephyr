package com.minnymin.zephyr.bukkit.user;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import com.minnymin.zephyr.bukkit.ZephyrPlugin;
import com.minnymin.zephyr.user.User;
import com.minnymin.zephyr.user.UserManager;

public class BukkitUserManager extends UserManager implements Listener {

	public BukkitUserManager() {
		Bukkit.getPluginManager().registerEvents(this, ZephyrPlugin.getInstance());
		Bukkit.getScheduler().runTaskTimer(ZephyrPlugin.getInstance(), new BukkitUserRunnable(), 0, 1);
	}

	@EventHandler
	public void onUserConnect(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		this.userSet.add(new BukkitUser(player));
	}

	@EventHandler
	public void onUserDisconnect(PlayerQuitEvent event) {
		Player player = event.getPlayer();
		this.userSet.remove(this.getUser(player.getUniqueId()));
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
