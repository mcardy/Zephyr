package com.minnymin.zephyr.api.item;

import java.util.HashMap;
import java.util.Map;

public class ItemRecipe {

	private Map<Character, ItemRecipeIngredient> recipe;
	private String[] shape;
	private boolean isShapeless;
	
	public ItemRecipe() {
		this(false);
	}
	
	public ItemRecipe(boolean shapeless) {
		this.isShapeless = shapeless;
		this.recipe = new HashMap<Character, ItemRecipeIngredient>();
	}
	
	public String[] getShape() {
		return this.shape;
	}
	
	public Map<Character, ItemRecipeIngredient> getIngredients() {
		return this.recipe;
	}
	
	public boolean isShapeless() {
		return this.isShapeless;
	}
	
	public ItemRecipe setIngredient(char pos, int id) {
		this.setIngredient(pos, id, 0);
		return this;
	}
	
	public ItemRecipe setIngredient(char pos, int id, int data) {
		this.recipe.put(pos, new ItemRecipeIngredient(id, data));
		return this;
	}
	
	public ItemRecipe setShape(String... shape) {
		this.shape = shape;
		return this;
	}
	
	public class ItemRecipeIngredient {
		
		private int id;
		private int data;
		
		ItemRecipeIngredient(int id, int data) {
			this.id = id;
			this.data = data;
		}
		
		public int getData() {
			return this.data;
		}
		
		public int getId() {
			return this.id;
		}
		
	}
	
}
