package com.minnymin.zephyr.sponge;

import org.spongepowered.api.event.Subscribe;
import org.spongepowered.api.event.state.PreInitializationEvent;
import org.spongepowered.api.event.state.ServerStoppingEvent;
import org.spongepowered.api.plugin.Plugin;

import com.minnymin.util.directive.DirectiveHandler;
import com.minnymin.zephyr.PluginInfo;
import com.minnymin.zephyr.Zephyr;
import com.minnymin.zephyr.ZephyrPlugin;
import com.minnymin.zephyr.spell.SpellManager;
import com.minnymin.zephyr.sponge.spell.SpellCommand;
import com.minnymin.zephyr.sponge.spell.SpongeSpellManager;

@Plugin(id = PluginInfo.ARTIFACT_ID, name = PluginInfo.NAME, version = PluginInfo.VERSION)
public class SpongeZephyrPlugin implements ZephyrPlugin {
	
	private SpellManager spellManager;

	public SpongeZephyrPlugin() {
		Zephyr.setZephyrPlugin(this);
	}

	//@Inject
	//private Logger logger;

	@Subscribe
	public void onPreInit(PreInitializationEvent event) {
		DirectiveHandler handler = new DirectiveHandler(this, event.getGame());
		handler.addDirectives(SpellCommand.class);
		handler.registerDirectives();
		
		spellManager = new SpongeSpellManager();
	}

	@Subscribe
	public void disable(ServerStoppingEvent event) {

	}

	@Override
	public SpellManager getSpellManager() {
		return this.spellManager;
	}
	
}
