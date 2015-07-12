package com.minnymin.zephyr.spell.target;

public class TargetHolder<T> {

	private T target;
	
	@SuppressWarnings("unchecked")
	public TargetHolder(Object target) {
		try {
			this.target = (T) target;
		} catch (ClassCastException ex) {
			this.target = null;
		}
	}
	
	public T get() {
		return this.target;
	}
	
	public boolean isAbsent() {
		return this.target == null;
	}
	
}
