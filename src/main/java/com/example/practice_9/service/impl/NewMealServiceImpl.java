package com.example.practice_9.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.practice_9.entity.NewMeal;
import com.example.practice_9.entity.NewMealId;
import com.example.practice_9.repository.NewMealDao;
import com.example.practice_9.service.ifs.NewMealService;

@Service
public class NewMealServiceImpl implements NewMealService {

	@Autowired
	private NewMealDao newMealDao;

	@Override
	public void add(String name, String cookingStyle, int price) {
		// �Ѽ��ˬd
		if (!StringUtils.hasText(name)) {
			System.out.println("�\�I�W�ٿ��~!!");
			return;
		}
		if (!StringUtils.hasText(cookingStyle)) {
			System.out.println("�\�I�Ʋz�覡���~!!");
			return;
		}
		if (price <= 0) {
			System.out.println("�\�I������~!!");
			return;
		}
		// �ˬdPK�O�_�w�s�b
		if (newMealDao.existsById(new NewMealId(name, cookingStyle))) {
			System.out.println("�\�I�w�s�b!!");
			return;
		}
		// save: PK�w�s�b --> ��s :PK���s�b --> �s�W
		newMealDao.save(new NewMeal(name, cookingStyle, price));
		System.out.println("�s�W�\�I���\");

	}

	@Override
	public void update(String name, String cookingStyle, int price) {
		if (!StringUtils.hasText(name)) {
			System.out.println("�\�I�W�ٿ��~!!");
			return;
		}
		if (!StringUtils.hasText(cookingStyle)) {
			System.out.println("�\�I�Ʋz�覡���~!!");
			return;
		}
		if (price <= 0) {
			System.out.println("�\�I������~!!");
			return;
		}
		// �ˬdPK�O�_�w�s�b
		if (!newMealDao.existsById(new NewMealId(name, cookingStyle))) {
			System.out.println("�\�I���s�b!!");
			return;
		}
		// save: PK�w�s�b --> ��s :PK���s�b --> �s�W
		newMealDao.save(new NewMeal(name, cookingStyle, price));
		System.out.println("��s�\�I���\");

	}

	@Override
	public void add(NewMeal newMeal) {
		// �Ѽ��ˬd
		if (!StringUtils.hasText(newMeal.getName())) {
			System.out.println("�\�I�W�ٿ��~!!");
			return;
		}
		if (!StringUtils.hasText(newMeal.getCookingStyle())) {
			System.out.println("�\�I�Ʋz�覡���~!!");
			return;
		}
		if (newMeal.getPrice() <= 0) {
			System.out.println("�\�I������~!!");
			return;
		}
		// �ˬdPK�O�_�w�s�b
		if (newMealDao.existsById(new NewMealId(newMeal.getName(), newMeal.getCookingStyle()))) {
			System.out.println("�\�I�w�s�b!!");
			return;
		}
		// save: PK�w�s�b --> ��s :PK���s�b --> �s�W
		newMealDao.save(newMeal);
		System.out.println("�s�W�\�I���\");

	}

}
