package com.example.practice_9.service.ifs;

import com.example.practice_9.entity.NewMeal;

public interface NewMealService {
	
	public void add(String name,String cookingStyle, int price);
	
	public void add(NewMeal newMeal);
	
	public void update(String name,String cookingStyle, int price);
	
	
}
