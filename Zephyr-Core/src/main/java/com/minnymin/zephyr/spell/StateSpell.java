package com.minnymin.zephyr.spell;

import com.minnymin.zephyr.spell.Targeted.TargetType;
import com.minnymin.zephyr.state.UserState;

@Targeted(type = TargetType.SELF)
public abstract class StateSpell extends Spell {

	private UserState state;
	private int duration;
	
	public StateSpell(String name, String description, UserState state, int duration, int requiredLevel, int manaCost, int experience) {
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
