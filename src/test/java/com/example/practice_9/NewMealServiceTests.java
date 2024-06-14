package com.example.practice_9;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.practice_9.entity.NewMeal;
import com.example.practice_9.service.ifs.NewMealService;

@SpringBootTest

public class NewMealServiceTests {

	@Autowired
	private NewMealService newMealService;
	
	@Test
	public void addTest() {
		newMealService.add("fish", "炸", 120);
	}
	
	@Test
	public void addTest2() {
//		NewMeal newMeal = new NewMeal("fish","煎",150);
//		newMealService.add(newMeal);
		// 通常宣告(new)一個物件會用變數去接，若宣告完之後該變數在接下來的程式區塊只使用一次，
		// 則可以省略變數，直接使用 new 物件：上面2行可以變成下行表示，此方式稱為匿名(沒有變數名稱)類別。
		newMealService.add(new NewMeal("fish","煎",150));
	}
	
	
}
