package com.minnymin.zephyr.spell;

import com.minnymin.zephyr.user.User;

public class SpellContext {

	private String[] args;
	private User user;
	
	public SpellContext(User user, String[] args) {
		this.args = args;
		this.user = user;
	}
	
	public String[] getArguments() {
		return this.args;
	}
	
	public User getUser() {
		return this.user;
	}
	
	public Object getPlayer() {
		return this.user.getPlayerObject();
	}
	
}
