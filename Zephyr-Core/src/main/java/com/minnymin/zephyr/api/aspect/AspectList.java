package com.minnymin.zephyr.api.aspect;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Represents a list of aspects
 *
 * @author minnymin3
 */
public class AspectList {

	/**
	 * Creates a new AspectList with given aspect and amount
	 * @param aspect An aspect to contain
	 * @param amount The amount of the aspect parameter
	 * @return New AspectList
	 */
	public static AspectList build(Aspect aspect, int amount) {
		return new AspectList().add(aspect, amount);
	}
	
	private Map<Aspect, Integer> values;
	
	/**
	 * Constructs a new AspectList
	 */
	public AspectList() {
		values = new HashMap<Aspect, Integer>();
	}
	
	/**
	 * Adds an aspect to this aspect list
	 * @param aspect The aspect to add
	 * @param amount The amount of aspect to add
	 * @return this for chaining
	 */
	public AspectList add(Aspect aspect, int amount) {
		if (values.containsKey(aspect)) {
			values.put(aspect, amount + values.get(aspect));
		} else {
			values.put(aspect, amount);
		}
		return this;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof AspectList) {
			return isEqual((AspectList)obj);
		} else if (obj instanceof Map) {
			return this.values.equals(obj);
		} else {
			return super.equals(obj);
		}
	}
	
	/**
	 * Returns the map of aspects
	 * @return
	 */
	public Map<Aspect, Integer> getAspects() {
		return this.values;
	}
	
	/**
	 * Checks if this aspect list equals another aspect list
	 * @param list
	 * @return
	 */
	public boolean isEqual(AspectList list) {
		return this.values.equals(list.values);
	}
	
	/**
	 * Adds the given list into this list
	 * @param list The list to combine
	 */
	public void merge(AspectList list) {
		for (Entry<Aspect, Integer> entry : list.values.entrySet()) {
			if (this.values.containsKey(entry.getKey())) {
				this.values.put(entry.getKey(), entry.getValue() + this.values.get(entry.getKey()));
			} else {
				this.values.put(entry.getKey(), entry.getValue());
			}
		}
	}
	
}
