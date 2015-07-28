package com.minnymin.zephyr.bukkit.item.wand;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Chest;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.Vector;

import com.minnymin.zephyr.api.Zephyr;
import com.minnymin.zephyr.api.aspect.Aspect;
import com.minnymin.zephyr.api.aspect.AspectList;
import com.minnymin.zephyr.api.item.WandItem;
import com.minnymin.zephyr.api.spell.Spell;
import com.minnymin.zephyr.api.spell.SpellManager;
import com.minnymin.zephyr.api.user.User;
import com.minnymin.zephyr.api.util.ColorCharacter;
import com.minnymin.zephyr.bukkit.item.SpellTome;
import com.minnymin.zephyr.bukkit.spell.BukkitSpellContext;
import com.minnymin.zephyr.bukkit.spell.BukkitTargetUtils;
import com.minnymin.zephyr.bukkit.util.InventoryGUI;
import com.minnymin.zephyr.bukkit.util.ParticleEffects;
import com.minnymin.zephyr.bukkit.util.ParticleEffects.Particle;
import com.minnymin.zephyr.common.item.BaseActionItem;

public abstract class BukkitWand extends BaseActionItem implements WandItem {

	private static final String boundLabel = ColorCharacter.GRAY + "Bound spell";

	private int maxLevel;

	public BukkitWand(String name, ColorCharacter color, int maxLevel, String descriptor) {
		super(280, name, color, new String[] { descriptor,
				ChatColor.GRAY + "Max bind level: " + ChatColor.RED + maxLevel });
		this.maxLevel = maxLevel;
	}

	@Override
	public void onRightClick(User user) {
		Player player = user.<Player> getPlayerObject();
		Block shelf = null;
		// Checks if targeting a bookshelf
		if ((shelf = BukkitTargetUtils.getTargetBlock(player, 5)) != null && shelf.getType() == Material.BOOKSHELF) {
			Collection<ItemStack> items;
			Runnable onComplete;
			// Adds items to items collection and creates onComplete task
			if (shelf.getRelative(BlockFace.UP).getType() == Material.CHEST) {
				final Chest chest = (Chest) shelf.getRelative(BlockFace.UP).getState();
				items = new ArrayList<ItemStack>();
				for (ItemStack item : chest.getInventory().getContents()) {
					if (item != null) {
						items.add(item);
					}
				}
				onComplete = new Runnable() {
					@Override
					public void run() {
						chest.getInventory().clear();
						chest.update();
					}
				};
			} else {
				final Set<org.bukkit.entity.Item> itemEntities = getItems(shelf.getRelative(BlockFace.UP).getLocation()
						.add(0.5, 0.5, 0.5));
				items = new ArrayList<ItemStack>();
				for (org.bukkit.entity.Item item : itemEntities) {
					items.add(item.getItemStack());
				}
				onComplete = new Runnable() {
					@Override
					public void run() {
						for (org.bukkit.entity.Item item : itemEntities) {
							item.remove();
						}
					};
				};
			}

			// Get all the aspects into one list
			AspectList aspects = new AspectList();
			for (ItemStack item : items) {
				aspects.merge(Aspect.getAspects(item.getType().toString(), item.getAmount()));
			}
			List<Spell> possibilities = Zephyr.getSpellManager().getSpellsForRecipe(aspects);

			if (possibilities.size() == 0) {
				player.sendMessage(ChatColor.RED + "There are no spells craftable with those aspects!");
			}

			// Checks the levels of the spells
			List<Spell> temp = new ArrayList<Spell>();
			for (Spell spell : possibilities) {
				if (spell.getLevel() > user.getLevel()) {
					player.sendMessage(ChatColor.RED + "You are not high enough level to craft " + spell.getName()
							+ ". " + user.getLevel() + " / " + spell.getLevel());
				} else {
					temp.add(spell);
				}
			}
			possibilities = temp;

			if (possibilities.size() > 0) {
				InventoryGUI gui = new InventoryGUI();
				int pos = 0;
				for (Spell spell : possibilities) {
					ItemStack tome = SpellTome.getSpellTome(spell, user);
					gui.setSlot(pos, tome, getCraftingRunnable(shelf, tome, onComplete));
					pos++;
				}
				gui.open(player);
			}
		} else {
			if (user.isCasting()) {
				user.setCasting(null, null);
			} else {
				SpellManager manager = Zephyr.getSpellManager();
				String spellName = getBoundSpell(player.getItemInHand());
				Spell spell = manager.getSpell(spellName);
				if (spell != null) {
					manager.cast(spell, new BukkitSpellContext(spell, user, new String[0]));
				}
			}
		}
	}

	private Runnable getCraftingRunnable(final Block bookshelf, final ItemStack item, final Runnable removalTask) {
		return new Runnable() {
			@Override
			public void run() {
				bookshelf.setType(Material.AIR);
				Location loc = bookshelf.getLocation().add(0.5, 1.5, 0.5);
				loc.getWorld().dropItem(loc, item).setVelocity(new Vector(0, 0, 0));
				ParticleEffects.sendParticle(Particle.ENCHANTMENT_TABLE, loc, 0.25F, 0.1F, 0.25F, 0, 50);
				removalTask.run();
			}
		};
	}

	@Override
	public void bindSpell(Object obj, Spell spell, User user) {
		if (!(obj instanceof ItemStack)) {
			user.sendMessage("You can only bind spells to wands!");
			return;
		}
		ItemStack stack = (ItemStack) obj;
		String name = stack.hasItemMeta() && stack.getItemMeta().hasDisplayName() ? stack.getItemMeta()
				.getDisplayName() : null;
		List<String> lore = stack.hasItemMeta() && stack.getItemMeta().hasLore() ? stack.getItemMeta().getLore() : null;
		if (name == null || !ChatColor.stripColor(name).equals(this.getName()) || lore == null) {
			user.sendMessage("You can only bind spells to wands!");
			return;
		}
		if (spell.getLevel() > this.maxLevel) {
			user.sendMessage(ColorCharacter.RED + "Your wand is too low level to bind " + spell.getName() + " to");
			return;
		}
		if (lore.get(lore.size() - 1).split(": ")[0].equals(boundLabel)) {
			lore.remove(lore.size() - 1);
		}
		if (spell != null) {
			lore.add(boundLabel + ": " + spell.getName());
		}
		ItemMeta meta = stack.getItemMeta();
		meta.setLore(lore);
		stack.setItemMeta(meta);
		user.sendMessage(ColorCharacter.BLUE + "Bound " + spell.getName() + " to your wand!");
	}

	@Override
	public String getBoundSpell(Object obj) {
		if (!(obj instanceof ItemStack)) {
			return null;
		}
		ItemStack stack = (ItemStack) obj;
		List<String> lore = stack.hasItemMeta() && stack.getItemMeta().hasLore() ? stack.getItemMeta().getLore() : null;
		if (lore == null) {
			return null;
		}
		String spell = "";
		if (lore.get(lore.size() - 1).split(": ")[0].equals(boundLabel)) {
			spell = lore.get(lore.size() - 1).split(": ")[1];
		}
		return spell;
	}

	private Set<org.bukkit.entity.Item> getItems(Location loc) {
		Set<org.bukkit.entity.Item> set = new HashSet<org.bukkit.entity.Item>();
		for (Entity en : loc.getChunk().getEntities()) {
			if (en.getType() == EntityType.DROPPED_ITEM) {
				if (en.getLocation().distance(loc) <= 1) {
					set.add((org.bukkit.entity.Item) en);
				}
			}
		}
		return set;
	}

}
