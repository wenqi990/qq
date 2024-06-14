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
		newMealService.add("fish", "��", 120);
	}
	
	@Test
	public void addTest2() {
//		NewMeal newMeal = new NewMeal("fish","��",150);
//		newMealService.add(newMeal);
		// �q�`�ŧi(new)�@�Ӫ���|���ܼƥh���A�Y�ŧi��������ܼƦb���U�Ӫ��{���϶��u�ϥΤ@���A
		// �h�i�H�ٲ��ܼơA�����ϥ� new ����G�W��2��i�H�ܦ��U���ܡA���覡�٬��ΦW(�S���ܼƦW��)���O�C
		newMealService.add(new NewMeal("fish","��",150));
	}
	
	
}
