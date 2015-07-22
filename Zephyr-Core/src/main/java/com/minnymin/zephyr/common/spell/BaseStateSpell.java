package com.minnymin.zephyr.common.spell;

import com.minnymin.zephyr.api.spell.CastResult;
import com.minnymin.zephyr.api.spell.SpellContext;
import com.minnymin.zephyr.api.user.UserState;

public abstract class BaseStateSpell extends BaseSpell {

	private UserState state;
	private int duration;
	
	/**
	 * Duration is duration in seconds
	 */
	public BaseStateSpell(String name, String description, UserState state, int duration, int requiredLevel, int manaCost, int experience) {
		super(name, description, requiredLevel, manaCost, experience);
		this.state = state;
		this.duration = duration;
	}
	
	@Override
	public CastResult cast(SpellContext context) {
		context.getUser().addState(this.state, this.duration);
		return CastResult.SUCCESS;
	}

}
