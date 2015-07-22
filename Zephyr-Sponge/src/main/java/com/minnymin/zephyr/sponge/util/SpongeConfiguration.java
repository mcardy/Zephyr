package com.minnymin.zephyr.sponge.util;

import java.io.IOException;

import ninja.leaping.configurate.ConfigurationNode;
import ninja.leaping.configurate.commented.CommentedConfigurationNode;
import ninja.leaping.configurate.loader.ConfigurationLoader;

import com.minnymin.zephyr.api.util.Configuration;

public class SpongeConfiguration implements Configuration {

	private ConfigurationLoader<CommentedConfigurationNode> loader;
	private ConfigurationNode node;
	
	public SpongeConfiguration(ConfigurationLoader<CommentedConfigurationNode> loader, String node) {
		this.loader = loader;
		try {
			this.node = this.loader.load().getNode(node);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T get(String key) {
		try {
			Object[] path = key.split("\\.");
			return (T) node.getNode(path).getValue();
		} catch (ClassCastException ex) {
			return null;
		}
	}
	
	@Override
	public boolean has(String key) {
		return this.node.getChildrenMap().containsKey(key);
	}

	@Override
	public void set(String key, Object data) {
		Object[] path = key.split("\\.");
		this.node.getNode(path).setValue(data);
		try {
			this.loader.save(node.getParent());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void setDefault(String key, Object value) {
		if (!node.getChildrenMap().containsKey(key)) {
			node.getNode(key).setValue(value);
			try {
				this.loader.save(node.getParent());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
}
