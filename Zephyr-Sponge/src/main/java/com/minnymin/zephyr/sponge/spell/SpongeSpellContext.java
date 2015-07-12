package com.minnymin.zephyr.sponge.spell;

import org.spongepowered.api.entity.player.Player;

import com.minnymin.zephyr.spell.Spell;
import com.minnymin.zephyr.spell.SpellContext;
import com.minnymin.zephyr.spell.TargetHolder;
import com.minnymin.zephyr.spell.Targeted;
import com.minnymin.zephyr.user.User;

public class SpongeSpellContext extends SpellContext {

	public SpongeSpellContext(Spell spell, User user, String[] args) {
		super(spell, user, args);
	}

	@Override
	public <T> TargetHolder<T> getTarget() {
		if (this.spell.getClass().getAnnotation(Targeted.class) == null) {
			return new TargetHolder<T>(null);
		}
		Player player = user.<Player> getPlayerObject();
		int range = this.spell.getClass().getAnnotation(Targeted.class).range();

		switch (this.spell.getClass().getAnnotation(Targeted.class).type()) {
		case ENTITY:
			return new TargetHolder<T>(SpongeTargetUtils.getTargetEntity(player, range));
		case ENTITY_AREA:
			return new TargetHolder<T>(SpongeTargetUtils.getNearbyEntities(player, range));
		case LIVING:
			return new TargetHolder<T>(SpongeTargetUtils.getTargetLiving(player, range));
		case LIVING_AREA:
			return new TargetHolder<T>(SpongeTargetUtils.getNearbyLivings(player, range));
		case PLAYER:
			return new TargetHolder<T>(SpongeTargetUtils.getTargetPlayer(player, range));
		case PLAYER_AREA:
			return new TargetHolder<T>(SpongeTargetUtils.getNearbyPlayers(player, range));
		case SELF:
			return new TargetHolder<T>(SpongeTargetUtils.getTargetEntity(player, range));
		case BLOCK:
			return new TargetHolder<T>(SpongeTargetUtils.getTargetBlock(player, range));
		default:
			return new TargetHolder<T>(null);
		}
	}

}
