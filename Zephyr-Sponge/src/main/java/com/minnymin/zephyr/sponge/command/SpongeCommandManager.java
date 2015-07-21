package com.minnymin.zephyr.sponge.command;

import com.minnymin.zephyr.api.Manager;
import com.minnymin.zephyr.sponge.ZephyrPlugin;
import com.minnymin.zephyr.sponge.util.directive.DirectiveHandler;

public class SpongeCommandManager implements Manager {
	
	private DirectiveHandler handler;
	
	@Override
	public void onEnable() {
		handler = new DirectiveHandler(ZephyrPlugin.getInstance(), ZephyrPlugin.getGame());
		handler.addDirectives(SpellCommand.class);
		handler.addDirectives(UserCommand.class);
		handler.registerDirectives();
	}

	@Override
	public void onDisable() {
		handler = null;
	}

}
