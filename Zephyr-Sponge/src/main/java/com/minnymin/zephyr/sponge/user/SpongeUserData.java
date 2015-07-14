package com.minnymin.zephyr.sponge.user;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

import ninja.leaping.configurate.ConfigurationNode;
import ninja.leaping.configurate.commented.CommentedConfigurationNode;
import ninja.leaping.configurate.hocon.HoconConfigurationLoader;
import ninja.leaping.configurate.loader.ConfigurationLoader;

import com.minnymin.zephyr.user.UserData;

public class SpongeUserData extends UserData {

	private ConfigurationLoader<CommentedConfigurationNode> loader;
	private ConfigurationNode root;

	public SpongeUserData(UUID playerId) {
		File potentialFile = new File(new File("config/zephyr/player"), playerId.toString() + ".cfg");
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
	public <T> T getData(String key) {
		try {
			Object[] path = key.split("\\.");
			return (T) root.getNode(path).getValue();
		} catch (ClassCastException ex) {
			return null;
		}
	}

	@Override
	public void setData(String key, Object data) {
		Object[] path = key.split("\\.");
		this.root.getNode(path).setValue(data);
		try {
			this.loader.save(root);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void loadDefaultConfiguration() {
		if (root.getChildrenMap().containsKey("learned") || root.getNode("mana").isVirtual()
				|| root.getNode("regeneration").isVirtual() || root.getNode("level").isVirtual()
				|| root.getNode("progress").isVirtual()) {			
			this.root.getNode("learned").setValue(new ArrayList<String>());
			this.root.getNode("mana").setValue(100);
			this.root.getNode("regeneration").setValue(1);
			this.root.getNode("level").setValue(1);
			this.root.getNode("progress").setValue(0);

			try {
				this.loader.save(root);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
