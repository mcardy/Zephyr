package com.minnymin.zephyr.bukkit.spell;

import org.bukkit.entity.Player;

import com.google.common.base.Optional;
import com.minnymin.zephyr.api.spell.Spell;
import com.minnymin.zephyr.api.spell.target.Targeted;
import com.minnymin.zephyr.api.spell.target.Targeted.TargetType;
import com.minnymin.zephyr.api.user.User;
import com.minnymin.zephyr.common.spell.AbstractSpellContext;

public class BukkitSpellContext extends AbstractSpellContext {

	private Object target;
	
	public BukkitSpellContext(Spell spell, User user, String[] args) {
		super(spell, user, args);
		
		if (this.spell.getClass().isAnnotationPresent(Targeted.class)) {
			Player player = user.<Player> getPlayer();
			int range = this.spell.getClass().getAnnotation(Targeted.class).range();
			this.target = getTarget(this.spell.getClass().getAnnotation(Targeted.class).type(), player, range);	
		} else {
			this.target = null;
		}
	}
	
	@Override
	public boolean hasTarget() {
		return getTarget().isPresent();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <T> Optional<T> getTarget() {
		try {
			if (this.target != null) {
				return Optional.of((T) this.target);
			} else {
				return Optional.<T>absent();
			}
		} catch (ClassCastException ex) {
			return Optional.<T>absent();
		}
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
