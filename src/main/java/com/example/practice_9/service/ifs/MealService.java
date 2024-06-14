package com.example.practice_9.service.ifs;

import java.util.List;

import com.example.practice_9.entity.Meal;

public interface MealService {
	
	public void addMeal(Meal meal);
	
	public void addMeals(List<Meal>mealList);
	
	//updateMeal �O��s��Meal��Ѽ�
	public void updateMeal(Meal meal);
	
	
	//�d�ߥ����\�I
	public void findAll();
	
	//�ھ��\�I�W�٬d��
	public void findByName(String name);
	
	
	
	//�ھ��\�I�W�٬d�ݸ��\�I�O�_�w�s�b
	public void existsByName(String name);

	void findByPrice(int price);
	
	
}
