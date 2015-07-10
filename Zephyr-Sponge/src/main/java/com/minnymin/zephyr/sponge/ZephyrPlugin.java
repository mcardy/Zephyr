package com.minnymin.zephyr.sponge;

import org.slf4j.Logger;
import org.spongepowered.api.Game;
import org.spongepowered.api.event.Subscribe;
import org.spongepowered.api.event.state.PreInitializationEvent;
import org.spongepowered.api.event.state.ServerStoppingEvent;
import org.spongepowered.api.plugin.Plugin;

import com.google.inject.Inject;
import com.minnymin.util.directive.DirectiveHandler;
import com.minnymin.zephyr.PluginInfo;
import com.minnymin.zephyr.Zephyr;
import com.minnymin.zephyr.ZephyrAPI;
import com.minnymin.zephyr.spell.SpellManager;
import com.minnymin.zephyr.sponge.command.SpellCommand;
import com.minnymin.zephyr.sponge.command.UserCommand;
import com.minnymin.zephyr.sponge.spell.SpongeSpellManager;
import com.minnymin.zephyr.sponge.user.SpongeUserManager;
import com.minnymin.zephyr.user.UserManager;

@Plugin(id = PluginInfo.ARTIFACT_ID, name = PluginInfo.NAME, version = PluginInfo.VERSION)
public class ZephyrPlugin implements ZephyrAPI {

	@Inject
	private Logger logger;
	private Game game;

	private SpellManager spellManager;
	private UserManager userManager;

	public ZephyrPlugin() {
		Zephyr.setAPISingleton(this);
	}

	@Subscribe
	public void onPreInit(PreInitializationEvent event) {
		this.game = event.getGame();
		this.spellManager = new SpongeSpellManager();
		this.userManager = new SpongeUserManager();

		DirectiveHandler handler = new DirectiveHandler(this, event.getGame());
		handler.addDirectives(SpellCommand.class);
		handler.addDirectives(UserCommand.class);
		handler.registerDirectives();
	}

	@Subscribe
	public void disable(ServerStoppingEvent event) {
	}

	@Override
	public SpellManager getSpellManager() {
		return this.spellManager;
	}

	@Override
	public UserManager getUserManager() {
		return this.userManager;
	}

	public static ZephyrPlugin getInstance() {
		return (ZephyrPlugin) Zephyr.getAPI();
	}

	public static Game getGame() {
		return ((ZephyrPlugin) Zephyr.getAPI()).game;
	}

	public static Logger getLogger() {
		return ((ZephyrPlugin) Zephyr.getAPI()).logger;
	}

}
