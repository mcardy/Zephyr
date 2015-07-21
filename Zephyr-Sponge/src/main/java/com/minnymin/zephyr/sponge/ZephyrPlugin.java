package com.minnymin.zephyr.sponge;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.spongepowered.api.Game;
import org.spongepowered.api.event.Subscribe;
import org.spongepowered.api.event.state.InitializationEvent;
import org.spongepowered.api.event.state.PreInitializationEvent;
import org.spongepowered.api.event.state.ServerStoppingEvent;
import org.spongepowered.api.plugin.Plugin;
import org.spongepowered.api.service.config.ConfigDir;

import com.google.inject.Inject;
import com.minnymin.zephyr.Manager;
import com.minnymin.zephyr.PluginInfo;
import com.minnymin.zephyr.Zephyr;
import com.minnymin.zephyr.ZephyrAPI;
import com.minnymin.zephyr.sponge.aspect.SpongeAspectRegister;
import com.minnymin.zephyr.sponge.command.SpongeCommandManager;
import com.minnymin.zephyr.sponge.projectile.SpongeProjectileHandler;
import com.minnymin.zephyr.sponge.spell.SpongeSpellManager;
import com.minnymin.zephyr.sponge.user.SpongeUserManager;

/**
 * Using Sponge v2.1
 * 
 * @author michael
 *
 */
@Plugin(id = PluginInfo.ARTIFACT_ID, name = PluginInfo.NAME, version = PluginInfo.VERSION)
public class ZephyrPlugin implements ZephyrAPI {
	
	public static ZephyrPlugin getInstance() {
		return (ZephyrPlugin) Zephyr.getAPI();
	}

	public static File getConfigDirectory() {
		return ((ZephyrPlugin) Zephyr.getAPI()).configDirectory;
	}
	
	public static Game getGame() {
		return ((ZephyrPlugin) Zephyr.getAPI()).game;
	}

	public static Logger getLogger() {
		return ((ZephyrPlugin) Zephyr.getAPI()).logger;
	}
	
	@Inject
	private Logger logger;
	@Inject
	private Game game;
	@Inject
	@ConfigDir(sharedRoot = false)
	private File configDirectory;
	
	private List<Manager> managers = new ArrayList<Manager>();
	private boolean loaded = false;

	public ZephyrPlugin() {
		Zephyr.addManager(new SpongeAspectRegister());
		Zephyr.addManager(new SpongeCommandManager());
		Zephyr.addManager(new SpongeProjectileHandler());
		Zephyr.addManager(new SpongeSpellManager());
		Zephyr.addManager(new SpongeUserManager());
	}
	
	@Subscribe
	public void onPreInit(PreInitializationEvent event) {
		Zephyr.setAPISingleton(this);
	}
	
	@Subscribe
	public void onInit(InitializationEvent event) {
		for (Manager manager : managers) {
			manager.onEnable();
		}
		loaded = true;
	}

	@Subscribe
	public void disable(ServerStoppingEvent event) {
		for (Manager manager : managers) {
			manager.onDisable();
		}
		managers.clear();
	}

	@Override
	public void addManager(Manager manager) {
		managers.add(manager);
		if (this.loaded) {
			manager.onEnable();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T extends Manager> T getManager(Class<T> cls) {
		for (Manager manager : managers) {
			if (cls.isAssignableFrom(manager.getClass())) {
				return (T) manager;
			}
		}
		return null;
	}

}
