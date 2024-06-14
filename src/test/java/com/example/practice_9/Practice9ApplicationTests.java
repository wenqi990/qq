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

	@Autowired // �i�H�⥻�ӥ��Spring Boot �U�ު�����`�J���ܼƤ�
	// �o��O�� MealDao ��Ӫ���`�J���ܼ� mealDao ��
	private MealDao mealDao;

	// ���M @Service �O�[�b MealServiceImpl�A���o�O�n�N���� MealService Autowired �i��
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
		System.out.println("�п�J�Ĥ@�ӼƦr");
		int x = scan.nextInt();
		System.out.println("�п�J�ĤG�ӼƦr");
		int y = scan.nextInt();
		while(y ==0) {
			System.out.println("�ĤG�ӼƦr���ର0�A�Э��s��J�Ʀr");
			y = scan.nextInt();
		}
		System.out.println("x/y �����G:" + x/y);
	}

	@Test
	public void test1() {
		Scanner scan = new Scanner(System.in);
		System.out.println("�п�J�Ĥ@�ӼƦr");
		int x = scan.nextInt();
		System.out.println("�п�J�ĤG�ӼƦr");
		int y = scan.nextInt();
		compute(x,y);
	}
	
	private void compute(int x , int y) {
		if( y == 0 ) {
			System.out.println(0);
			return;
		}
		System.out.println("x/y �����G:" + x/y);
	}
	
	private void compute1(int x , int y) {
		try {
			System.out.println("x/y �����G:" + x/y);
		} catch (Exception e) { // �ҥ~/���`/���~
			System.out.println("�o�Ͳ��`:" + e.getMessage());
		}finally {
			System.out.println("=======================");
		}
		
	}
}
