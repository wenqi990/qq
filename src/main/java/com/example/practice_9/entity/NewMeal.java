package com.example.practice_9.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

//control shift o
//control shift f 排版
@Entity
@Table(name = "new_meal")
//複合主鑑用 指定class名稱為所有主鑑集中管理的class
@IdClass(value= NewMealId.class)
public class NewMeal {

	@Id
	@Column(name = "name")
	private String name;

	@Id
	@Column(name = "cooking_style")
	private String cookingStyle;
	
	

	@Column(name = "price")
	private int price;

	public NewMeal() {
		super();
	}

	public NewMeal(String name, String cookingStyle, int price) {
		super();
		this.name = name;
		this.cookingStyle = cookingStyle;
		this.price = price;
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

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

}
