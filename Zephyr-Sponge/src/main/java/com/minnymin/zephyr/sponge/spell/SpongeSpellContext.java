package com.minnymin.zephyr.sponge.spell;

import org.spongepowered.api.entity.player.Player;

import com.google.common.base.Optional;
import com.minnymin.zephyr.api.spell.Spell;
import com.minnymin.zephyr.api.spell.target.Targeted;
import com.minnymin.zephyr.api.spell.target.Targeted.TargetType;
import com.minnymin.zephyr.api.user.User;
import com.minnymin.zephyr.common.spell.AbstractSpellContext;

public class SpongeSpellContext extends AbstractSpellContext {
	
	private TargetType type;
	private Object target;
	
	public SpongeSpellContext(Spell spell, User user, String[] args) {
		super(spell, user, args);
		
		if (this.spell.getClass().isAnnotationPresent(Targeted.class)) {
			Player player = user.<Player> getPlayer();
			int range = this.spell.getClass().getAnnotation(Targeted.class).range();
			this.type = this.spell.getClass().getAnnotation(Targeted.class).type();
			this.target = getTarget(type, player, range);	
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
		if (this.target != null) {
			return Optional.of((T) this.target);
		} else {
			return Optional.<T>absent();
		}
	}
	
	@Override
	public TargetType getTargetType() {
		return null;
	}
	
	public Object getTarget(TargetType type, Player player, int range) {
		switch (type) {
		case ENTITY:
			return SpongeTargetUtils.getTargetEntity(player, range);
		case ENTITY_AREA:
			return SpongeTargetUtils.getNearbyEntities(player, range);
		case LIVING:
			return SpongeTargetUtils.getTargetLiving(player, range);
		case LIVING_AREA:
			return SpongeTargetUtils.getNearbyLivings(player, range);
		case MONSTER:
			return SpongeTargetUtils.getTargetMonster(player, range);
		case MONSTER_AREA:
			return SpongeTargetUtils.getNearbyMonsters(player, range);
		case PLAYER:
			return SpongeTargetUtils.getTargetPlayer(player, range);
		case PLAYER_AREA:
			return SpongeTargetUtils.getNearbyPlayers(player, range);
		case SELF:
			return SpongeTargetUtils.getTargetEntity(player, range);
		case BLOCK:
			return SpongeTargetUtils.getTargetBlock(player, range);
		default:
			return null;
		}
	}

}
