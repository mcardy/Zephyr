package com.minnymin.zephyr.aspect;

import java.util.HashMap;
import java.util.Map;

public class AspectList {

	public static AspectList build(Aspect aspect, int amount) {
		return new AspectList().add(aspect, amount);
	}
	
	private Map<Aspect, Integer> values;
	
	public AspectList() {
		values = new HashMap<Aspect, Integer>();
	}
	
	public AspectList add(Aspect aspect, int amount) {
		values.put(aspect, amount);
		return this;
	}
	
	public boolean isEqual(AspectList list) {
		return this.values.equals(list.values);
	}
	
}
