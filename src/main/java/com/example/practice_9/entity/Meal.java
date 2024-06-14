package com.example.practice_9.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity//標是此類別 Entity類，並交由 Spring boot 是託管
//託管是指Spring boot 會自動建立此類別，並在我們需要使用時將注入到指定的變數
//此行用來跟資料表做連接: 指定要將此類別跟哪張表連接
//name等號後面的字串是資料表的名稱
@Table(name = "meal")
public class Meal {
	
	@Id//用來表明此屬性對應的欄位是PK(Primay Key，主鍵)
	//@Column:要用來指定此屬性要跟哪個欄位做連接:name等號後面的字串是資料表的名稱
	@Column(name = "name")
	private String name;
	
	@Column(name = "price")
	private int price;

	public Meal() {
		super();
	}

	public Meal(String name, int price) {
		super();
		this.name = name;
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
		

}
