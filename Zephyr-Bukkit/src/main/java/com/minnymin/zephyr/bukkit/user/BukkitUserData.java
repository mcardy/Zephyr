package com.minnymin.zephyr.bukkit.user;

import java.util.UUID;

import com.minnymin.zephyr.user.UserData;

public class BukkitUserData implements UserData {

	public BukkitUserData(UUID uuid) {
	}
	
	@Override
	public int getMana() {
		return 100;
	}

	@Override
	public void setMana(int mana) {

	}

	@Override
	public int getManaRegeneration() {
		return 1;
	}

	@Override
	public void setManaRegeneration(int mana) {

	}

}
