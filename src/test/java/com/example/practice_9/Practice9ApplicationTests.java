package com.example.practice_9;

import java.util.Scanner;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.practice_9.entity.Meal;
import com.example.practice_9.repository.MealDao;
import com.example.practice_9.service.ifs.MealService;

@SpringBootTest
class Practice9ApplicationTests {

	@Autowired // 可以把本來交由Spring Boot 託管的物件注入到變數中
	// 這邊是把 MealDao 整個物件注入到變數 mealDao 中
	private MealDao mealDao;

	// 雖然 @Service 是加在 MealServiceImpl，但卻是要將介面 MealService Autowired 進來
	@Autowired
	private MealService mealService;

	@Test
	public void addmealTest() {
//		Meal meal = new Meal("beef", 120);
		mealService.addMeal(new Meal("beef", 150));
	}

	@Test
	public void test() {
		Scanner scan = new Scanner(System.in);
		System.out.println("請輸入第一個數字");
		int x = scan.nextInt();
		System.out.println("請輸入第二個數字");
		int y = scan.nextInt();
		while(y ==0) {
			System.out.println("第二個數字不能為0，請重新輸入數字");
			y = scan.nextInt();
		}
		System.out.println("x/y 的結果:" + x/y);
	}

	@Test
	public void test1() {
		Scanner scan = new Scanner(System.in);
		System.out.println("請輸入第一個數字");
		int x = scan.nextInt();
		System.out.println("請輸入第二個數字");
		int y = scan.nextInt();
		compute(x,y);
	}
	
	private void compute(int x , int y) {
		if( y == 0 ) {
			System.out.println(0);
			return;
		}
		System.out.println("x/y 的結果:" + x/y);
	}
	
	private void compute1(int x , int y) {
		try {
			System.out.println("x/y 的結果:" + x/y);
		} catch (Exception e) { // 例外/異常/錯誤
			System.out.println("發生異常:" + e.getMessage());
		}finally {
			System.out.println("=======================");
		}
		
	}
}
