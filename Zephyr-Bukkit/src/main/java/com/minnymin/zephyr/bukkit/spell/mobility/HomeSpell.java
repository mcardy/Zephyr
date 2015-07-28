package com.minnymin.zephyr.bukkit.spell.mobility;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import com.minnymin.zephyr.api.aspect.Aspect;
import com.minnymin.zephyr.api.aspect.AspectList;
import com.minnymin.zephyr.api.spell.CastResult;
import com.minnymin.zephyr.api.spell.SpellContext;
import com.minnymin.zephyr.api.spell.SpellRecipe;
import com.minnymin.zephyr.api.user.UserData;
import com.minnymin.zephyr.bukkit.util.ParticleEffects;
import com.minnymin.zephyr.bukkit.util.ParticleEffects.Particle;
import com.minnymin.zephyr.common.spell.BaseSpell;

public class HomeSpell extends BaseSpell {

	public HomeSpell() {
		super("home", "Go home!", 4, 200);
	}

	@Override
	public CastResult cast(SpellContext context) {
		Player player = context.<Player>getPlayer();
		if (context.getArguments().length == 1 && context.getArguments()[0].equalsIgnoreCase("set")) {
			Location homeLocation = player.getLocation();
			UserData data = context.getUser().getUserData();
			data.set("home.x", homeLocation.getX());
			data.set("home.y", homeLocation.getY());
			data.set("home.z", homeLocation.getZ());
			data.set("home.pitch", homeLocation.getPitch());
			data.set("home.yaw", homeLocation.getYaw());
			data.set("home.world", homeLocation.getWorld().getUID().toString());
			player.sendMessage("Home set!");
		} else {
			UserData data = context.getUser().getUserData();
			if (!data.has("home.x")) {
				player.sendMessage(ChatColor.RED + "You do not have a home set! Set it with /cast home set");
				return CastResult.FAILURE;
			}
			double x = data.get("home.x");
			double y = data.get("home.y");
			double z = data.get("home.z");
			float pitch = data.get("home.pitch");
			float yaw = data.get("home.yaw");
			UUID world = UUID.fromString(data.<String>get("home.world"));
			
			Location loc = new Location(Bukkit.getWorld(world), x, y, z);
			loc.setPitch(pitch);
			loc.setYaw(yaw);
			
			ParticleEffects.sendParticle(Particle.ENDER, player.getLocation(), 1, 1, 1, 1, 16);
			player.teleport(loc);
			player.getWorld().playEffect(player.getLocation(), Effect.ENDER_SIGNAL, 0);
		}
		
		return CastResult.SUCCESS;
	}

	@Override
	public SpellRecipe getRecipe() {
		return new SpellRecipe(AspectList.build(Aspect.ENDER, 64).add(Aspect.MOVEMENT, 64), BlinkSpell.class);
	}

	
	
}
