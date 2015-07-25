package com.minnymin.zephyr.bukkit.util;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import com.minnymin.zephyr.bukkit.ZephyrPlugin;

/**
 * Ported from Zephyrus II
 *
 * @author minnymin3
 */
public class InventoryGUI implements Listener {

	private static InventoryGUIListener LISTENER_INSTANCE;
	
	private static InventoryGUIListener getListenerInstance() {
		if (LISTENER_INSTANCE == null) {
			LISTENER_INSTANCE = new InventoryGUIListener();
			Bukkit.getPluginManager().registerEvents(LISTENER_INSTANCE, ZephyrPlugin.getInstance());
		}
		return LISTENER_INSTANCE;
	}
	
	private Inventory inv;
	private Map<Integer, Runnable> buttonmap;
	private Set<String> playerlist;

	public InventoryGUI() {
		inv = Bukkit.createInventory(null, 9);
		playerlist = new HashSet<String>();
		buttonmap = new HashMap<Integer, Runnable>();
		getListenerInstance().add(this);
	}

	public InventoryGUI(String name) {
		inv = Bukkit.createInventory(null, 9, name);
		playerlist = new HashSet<String>();
		buttonmap = new HashMap<Integer, Runnable>();
		getListenerInstance().add(this);
	}
	
	/**
	 * Opens the inventory
	 * 
	 * @param player
	 */
	public void open(final Player player) {
		player.openInventory(inv);
		playerlist.add(player.getName());
		// Close inventory after 10 seconds if not used
		Bukkit.getScheduler().runTaskLater(ZephyrPlugin.getInstance(), new Runnable() {
			@Override
			public void run() {
				if (playerlist.contains(player.getName())) {
					player.closeInventory();
				}
			}
		}, 200);
	}

	/**
	 * Sets the item button in the slot
	 * 
	 * @param slot The slot to set
	 * @param item The item to set
	 * @param button The button action
	 * @return The instance of the InventoryGUI (used for easy construction)
	 */
	public InventoryGUI setSlot(int slot, ItemStack item, Runnable button) {
		setSize(slot);
		inv.setItem(slot, item);
		buttonmap.put(slot, button);
		return this;
	}
	
	public boolean hasOpen(Player player) {
		return playerlist.contains(player.getName());
	}
	
	public void remove() {
		getListenerInstance().remove(this);
	}
	
	private void setSize(int size) {
		if (size > 54)
			return;
		size = size % 9 == 0 ? size : size + 9 - (size % 9);
		if (size < 9) {
			size = 9;
		}
		if (size != inv.getSize()) {
			Inventory newinv = Bukkit.createInventory(null, size);
			newinv.setContents(inv.getContents());
			this.inv = newinv;
		}
	}

	public static abstract class InventoryButton implements Runnable {
		/**
		 * The method that is called when a slot is clicked
		 * 
		 * @param player The player who clicked
		 * @param slot The slot that was clicked
		 */
		public abstract void onClick(Player player, int slot);
		
		@Override
		public void run() {}
	}
	
	/**
	 * Cached all inventory guis and listens for them
	 *
	 * @author minnymin3
	 */
	private static class InventoryGUIListener implements Listener {
		
		private Set<InventoryGUI> set;
		
		public InventoryGUIListener() {
			set = new HashSet<InventoryGUI>();
		}
		
		private void add(InventoryGUI gui) {
			this.set.add(gui);
		}
		
		private void remove(InventoryGUI gui) {
			this.set.remove(gui);
		}
		
		@EventHandler
		public void onClose(InventoryCloseEvent event) {
			for (InventoryGUI gui : set) {
				if (gui.playerlist.contains(event.getPlayer().getName())) {
					gui.playerlist.remove(event.getPlayer().getName());
				}
				if (gui.playerlist.isEmpty()) {
					remove(gui);
				}
			}
		}

		@EventHandler
		public void onClick(InventoryClickEvent event) {
			for (InventoryGUI gui : set) {
				if (gui.playerlist.contains(event.getWhoClicked().getName())) {
					event.setCancelled(true);
					if (event.getInventory().getType() != InventoryType.PLAYER) {
						Runnable button = gui.buttonmap.get(event.getSlot());
						if (button != null) {
							try {
								if (button instanceof InventoryButton) {
									((InventoryButton)button).onClick(Bukkit.getPlayer(event.getWhoClicked().getUniqueId()), event.getSlot() + 1);
								} else {
									button.run();
								}
							} catch (Exception ex) {
								ex.printStackTrace();
							}
							event.getWhoClicked().closeInventory();
						}
					}
				}
			}
		}
		
	}

}