package com.minnymin.zephyr.api.user;

import java.util.UUID;

import com.minnymin.zephyr.api.Manager;

public interface UserManager extends Manager {

	public User getUser(UUID id);

}