package com.example.practice_9.service.ifs;

import java.util.List;

import com.example.practice_9.entity.Meal;

public interface MealService {
	
	public void addMeal(Meal meal);
	
	public void addMeals(List<Meal>mealList);
	
	//updateMeal 是把新的Meal當參數
	public void updateMeal(Meal meal);
	
	
	//查詢全部餐點
	public void findAll();
	
	//根據餐點名稱查詢
	public void findByName(String name);
	
	
	
	//根據餐點名稱查看該餐點是否已存在
	public void existsByName(String name);

	void findByPrice(int price);
	
	
}
