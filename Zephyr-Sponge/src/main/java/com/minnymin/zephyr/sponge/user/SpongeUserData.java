package com.minnymin.zephyr.sponge.user;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import ninja.leaping.configurate.ConfigurationNode;
import ninja.leaping.configurate.commented.CommentedConfigurationNode;
import ninja.leaping.configurate.hocon.HoconConfigurationLoader;
import ninja.leaping.configurate.loader.ConfigurationLoader;

import com.minnymin.zephyr.api.Zephyr;
import com.minnymin.zephyr.api.spell.Spell;
import com.minnymin.zephyr.common.user.AbstractUserData;
import com.minnymin.zephyr.sponge.ZephyrPlugin;

public class SpongeUserData extends AbstractUserData {

	private ConfigurationLoader<CommentedConfigurationNode> loader;
	private ConfigurationNode root;

	public SpongeUserData(UUID playerId) {
		File potentialFile = new File(new File(ZephyrPlugin.getConfigDirectory(), "players"), playerId.toString() + ".cfg");
		if (!potentialFile.exists()) {
			potentialFile.getParentFile().mkdirs();
			try {
				potentialFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		this.loader = HoconConfigurationLoader.builder().setFile(potentialFile).build();
		try {
			this.root = this.loader.load();
		} catch (IOException e) {
			this.root = null;
		}

		loadDefaultConfiguration();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <T> T get(String key) {
		try {
			Object[] path = key.split("\\.");
			return (T) root.getNode(path).getValue();
		} catch (ClassCastException ex) {
			return null;
		}
	}
	
	@Override
	public boolean has(String key) {
		return this.root.getChildrenMap().containsKey(key);
	}

	@Override
	public void set(String key, Object data) {
		Object[] path = key.split("\\.");
		this.root.getNode(path).setValue(data);
		try {
			this.loader.save(root);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void setDefault(String key, Object value) {
		if (!root.getChildrenMap().containsKey(key)) {
			root.getNode(key).setValue(value);
			try {
				this.loader.save(root);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void loadDefaultConfiguration() {	
		List<String> spellList = new ArrayList<String>();
		for (Spell spell : Zephyr.getSpellManager().getSpellsForLevel(1)) {
			spellList.add(spell.getName());
		}
		setDefault("learned", spellList);
		setDefault("mana", 100);
		setDefault("regeneration", 1);
		setDefault("level", 1);
		setDefault("progress", 1);
	}

}
