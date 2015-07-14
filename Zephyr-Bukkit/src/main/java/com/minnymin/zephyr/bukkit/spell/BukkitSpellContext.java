package com.minnymin.zephyr.bukkit.spell;

import org.bukkit.entity.Player;

import com.minnymin.zephyr.spell.Spell;
import com.minnymin.zephyr.spell.SpellContext;
import com.minnymin.zephyr.spell.target.TargetHolder;
import com.minnymin.zephyr.spell.target.Targeted;
import com.minnymin.zephyr.spell.target.Targeted.TargetType;
import com.minnymin.zephyr.user.User;

public class BukkitSpellContext extends SpellContext {

	private Object target;
	
	public BukkitSpellContext(Spell spell, User user, String[] args) {
		super(spell, user, args);
		
		if (this.spell.getClass().isAnnotationPresent(Targeted.class)) {
			Player player = user.<Player> getPlayerObject();
			int range = this.spell.getClass().getAnnotation(Targeted.class).range();
			this.target = getTarget(this.spell.getClass().getAnnotation(Targeted.class).type(), player, range);	
		} else {
			this.target = null;
		}
	}
	
	@Override
	public boolean hasTarget() {
		return !getTarget().isAbsent();
	}
	
	@Override
	public <T> TargetHolder<T> getTarget() {
		return new TargetHolder<T>(this.target);
	}

	public Object getTarget(TargetType type, Player player, int range) {
		switch (type) {
		case ENTITY:
			return BukkitTargetUtils.getTargetEntity(player, range);
		case ENTITY_AREA:
			return BukkitTargetUtils.getNearbyEntities(player, range);
		case LIVING:
			return BukkitTargetUtils.getTargetLiving(player, range);
		case LIVING_AREA:
			return BukkitTargetUtils.getNearbyLivings(player, range);
		case MONSTER:
			return BukkitTargetUtils.getTargetMonster(player, range);
		case MONSTER_AREA:
			return BukkitTargetUtils.getNearbyMonsters(player, range);
		case PLAYER:
			return BukkitTargetUtils.getTargetPlayer(player, range);
		case PLAYER_AREA:
			return BukkitTargetUtils.getNearbyPlayers(player, range);
		case SELF:
			return BukkitTargetUtils.getTargetEntity(player, range);
		case BLOCK:
			return BukkitTargetUtils.getTargetBlock(player, range);
		default:
			return null;
		}
	}

}
