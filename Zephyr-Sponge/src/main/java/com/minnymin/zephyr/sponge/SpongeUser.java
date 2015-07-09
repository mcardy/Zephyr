package com.minnymin.zephyr.sponge;

import org.spongepowered.api.util.command.CommandSource;

import com.minnymin.zephyr.user.User;

public class SpongeUser extends User {

	private CommandSource src;
	
	public SpongeUser(CommandSource src) {
		this.src = src;
	}
	
	public CommandSource getSource() {
		return this.src;
	}
	
}
