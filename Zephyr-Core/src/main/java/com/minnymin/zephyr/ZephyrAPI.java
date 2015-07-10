package com.minnymin.zephyr;

import com.minnymin.zephyr.spell.SpellManager;
import com.minnymin.zephyr.user.UserManager;

public interface ZephyrAPI {

	public SpellManager getSpellManager();
	public UserManager getUserManager();
	
}
