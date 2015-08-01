package com.minnymin.zephyr.bukkit.hook;

import java.util.Collection;

import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import com.minnymin.zephyr.api.hook.WorldGuardHook;
import com.minnymin.zephyr.api.spell.SpellContext;
import com.minnymin.zephyr.api.spell.target.Targeted;
import com.minnymin.zephyr.api.spell.target.Targeted.TargetType;
import com.minnymin.zephyr.bukkit.ZephyrPlugin;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.flags.DefaultFlag;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;

public class BukkitWorldGuardHook implements WorldGuardHook {

	private WorldGuardPlugin wg;

	@Override
	public boolean canUserTargetBlock(SpellContext context) {
		if (context.getTargetType() != TargetType.BLOCK || !context.hasTarget()) {
			return true;
		}
		return wg.canBuild(context.getUser().<Player>getPlayer(), context.<Block>getTarget().get());
	}

	@Override
	public boolean canUserTargetEntity(SpellContext context) {
		if (context.getSpell().getClass().getAnnotation(Targeted.class).friendly() || !context.hasTarget()) {
			return true;	
		}
		
		// Check caster's region first
		for (ProtectedRegion region : wg.getRegionManager(context.getUser().<Player>getPlayer().getWorld()).getApplicableRegions(
				context.getUser().<Player>getPlayer().getLocation())) {
			if (region.getFlag(DefaultFlag.PVP) != null) {
				return false;
			}
		}
		
		// Check target's region
		if (context.getTargetType() == TargetType.PLAYER_AREA) {
			Collection<Player> players = context.<Collection<Player>> getTarget().get();
			for (Player player : players) {
				for (ProtectedRegion region : wg.getRegionManager(player.getWorld()).getApplicableRegions(
						player.getLocation())) {
					if (region.getFlag(DefaultFlag.PVP) != null) {
						return false;
					}
				}
			}
		} else if (context.getTargetType() == TargetType.PLAYER_AREA) {
			Player player = context.<Player>getTarget().get();
			for (ProtectedRegion region : wg.getRegionManager(player.getWorld()).getApplicableRegions(
					player.getLocation())) {
				if (region.getFlag(DefaultFlag.PVP) != null) {
					return false;
				}
			}
		}
		
		// All good!
		return true;
	}

	@Override
	public boolean isProvided() {
		return Bukkit.getPluginManager().getPlugin("WorldGuard") instanceof WorldGuardPlugin;
	}

	@Override
	public void load() {
		this.wg = (WorldGuardPlugin) Bukkit.getPluginManager().getPlugin("WorldGuard");
		ZephyrPlugin.getInstance().getLogger().info("WorldGuard detected and hooked");
	}

	@Override
	public void unload() {
	}

}
