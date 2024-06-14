package com.example.practice_9.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.practice_9.entity.NewMeal;
import com.example.practice_9.entity.NewMealId;

@Repository
public interface NewMealDao extends JpaRepository<NewMeal,NewMealId>{ 
	//<T, ID>T為new meal
	//NewMealId 複合組鍵的寫法	
	// 的資料型態
	
	// findBy 後面接屬性名稱，都是駝峰式寫法：表示篩選條件(屬性)
	public List<NewMeal> findByName(String name);
	// 多個屬性用 And 或是 Or 連接。表示邏輯用法中的 && 和 || 
	public List<NewMeal> findByNameAndPrice(String name, int price);
	// 數字可以比較大小：大於(GreaterThan)：大於等於(GreaterThanEqual)：小於(LessThan)：小於等於(LessThanEqual)
	// 上面的方法 findByNameAndPrice 中的 Price 是完全等於。
	public List<NewMeal> findByNameAndPriceGreaterThan(String name, int price);
	
	public List<NewMeal> findByCookingStyle(String cookingStyle);
}
