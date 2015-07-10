package com.minnymin.zephyr.user;

import java.util.UUID;

public abstract class User {

	private UserData userData;
	
	private int mana;
	
	protected User(UserData data) {
		this.userData = data;
		this.mana = data.getMana();
	}
	
	public abstract UUID getUUID();
	public abstract Object getPlayerObject();
	public abstract void sendMessage(String... message);
	
	/**
	 * Drains mana from user's mana.
	 * Only accepts positive values
	 */
	public void drainMana(int toDrain) {
		if (mana < 0) {
			return;
		}
		setMana(getMana() - toDrain);
	}
	
	/**
	 * Gets the user's mana
	 */
	public int getMana() {
		return this.mana;
	}
	
	/**
	 * Checks if user has more than specified drain amount
	 * @return True if user has enough mana, false otherwise
	 */
	public boolean hasMana(int toDrain) {
		return getMana() - toDrain >= 0;
	}
	
	/**
	 * Sets the user's mana
	 */
	public void setMana(int mana) {
		if (mana > getUserData().getMana()) {
			this.mana = getUserData().getMana();
		} else {
			this.mana = mana;
		}
	}
	
	/**
	 * Gets user's maximum mana
	 */
	public int getMaximumMana() {
		return this.userData.getMana();
	}
	
	/**
	 * Get user's UserData
	 */
	public UserData getUserData() {
		return this.userData;
	}
	
	public void tick() {
		if (getMana() < getUserData().getMana()) {
			setMana(getMana() + getUserData().getManaRegeneration());
		}
	}
	
}
