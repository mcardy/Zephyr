package com.minnymin.zephyr.sponge.state;

import com.minnymin.zephyr.user.User;
import com.minnymin.zephyr.user.UserState;

public class TestState extends UserState {

	public TestState() {
		super("Test State", "A test state", 5);
	}

	@Override
	public void onApplied(User user) {
		user.sendMessage("A test state");
	}

	@Override
	public void onRemoved(User user) {
		user.sendMessage("A test state removed!");
	}

	@Override
	public void tick(User user) {
		user.sendMessage("Tick!");
	}

}
