package com.minnymin.zephyr.bukkit.spell.state;

import org.bukkit.entity.Player;

import com.minnymin.zephyr.api.aspect.Aspect;
import com.minnymin.zephyr.api.aspect.AspectList;
import com.minnymin.zephyr.api.spell.SpellRecipe;
import com.minnymin.zephyr.api.user.User;
import com.minnymin.zephyr.common.spell.BaseStateSpell;
import com.minnymin.zephyr.common.user.BaseUserState;

public class SpeedSpell extends BaseStateSpell {

	public SpeedSpell() {
		super("speed", "Makes you go fast!", new SpeedState(), 30, 3, 200, 20);
	}
	
	@Override
	public SpellRecipe getRecipe() {
		return new SpellRecipe(AspectList.build(Aspect.MOVEMENT, 16).add(Aspect.WIND, 16));
	}
	
	private static class SpeedState extends BaseUserState {

		public SpeedState() {
			super("Speed", "Makes you go fast!", 0);
		}

		@Override
		public void onApplied(User user) {
			user.<Player>getPlayer().setWalkSpeed(0.4F);
			user.sendMessage("You can now run faster");
		}

		@Override
		public void onRemoved(User user) {
			user.<Player>getPlayer().setWalkSpeed(0.2F);
			user.sendMessage("You've returned to normal speed");
		}

		@Override
		public void tick(User user) {			
		}
		
	}

}
