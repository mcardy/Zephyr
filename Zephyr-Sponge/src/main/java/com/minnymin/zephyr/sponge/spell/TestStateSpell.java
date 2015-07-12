package com.minnymin.zephyr.sponge.spell;

import com.minnymin.zephyr.spell.StateSpell;
import com.minnymin.zephyr.sponge.state.TestState;

public class TestStateSpell extends StateSpell {

	public TestStateSpell() {
		super("state", "A test state spell", new TestState(), 10, 1, 10, 10);
	}

}
