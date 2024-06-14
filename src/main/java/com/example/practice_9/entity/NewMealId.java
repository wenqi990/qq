package com.example.practice_9.entity;

import java.io.Serializable;

public class NewMealId implements Serializable {
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private String cookingStyle;

	public NewMealId() {
		super();
		// TODO Auto-generated constructor stub
	}

	public NewMealId(String name, String cookingStyle) {
		super();
		this.name = name;
		this.cookingStyle = cookingStyle;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCookingStyle() {
		return cookingStyle;
	}

	public void setCookingStyle(String cookingStyle) {
		this.cookingStyle = cookingStyle;
	}

}
