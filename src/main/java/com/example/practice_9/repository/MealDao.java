package com.example.practice_9.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.practice_9.entity.Meal;

@Repository//把此介面交由Spring Boot 託管成資料處理類
public interface MealDao extends JpaRepository<Meal,String> {
	//繼承 JpaRepository 可以透過定義方法爾不需要實作就可以操作資料
	//JpaRepository<Meal,String> :Meal是指要操作哪個 Entity(class)
	//                           :String是指該Entity 中有加@Id的屬性的資料型態
	//                            例如:屬性的資料型態是 int，在這就要寫 Integer
	
	//自定義 JPA語法
	//1. JPA的查詢資料使用finally 後面接的屬性名稱 (不是@column的字串)
	//2. 因為要再查詢欄位price 不是PK 所以撈回來的資料可能有多筆 因此使用list 接
	//3. JPA語法要遵循小駝峰的寫法
	public List<Meal> findByPrice(int price);
	
	//existsBy 屬命名稱 查詢資料是否存在 回傳的是boolean值 
	//注意 exists 最後有個s
	public boolean existsByPrice(int price);
	
}
