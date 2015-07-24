package com.minnymin.zephyr.api.spell;

import com.minnymin.zephyr.api.aspect.AspectList;

public class SpellRecipe {

	private AspectList list;
	private Class<? extends Spell> prerequisite;
	
	public SpellRecipe(AspectList list) {
		this(list, null);
	}
	
	public SpellRecipe(AspectList list, Class<? extends Spell> prerequisite) {
		this.list = list;
		this.prerequisite = prerequisite;
	}
	
	public AspectList getAspects() {
		return this.list;
	}
	
	public Class<? extends Spell> getPrerequisite() {
		return this.prerequisite;
	}
	
	public boolean hasPrerequisite() {
		return this.prerequisite != null;
	}
	
}
