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
		// 參數檢查
		if (!StringUtils.hasText(name)) {
			System.out.println("餐點名稱錯誤!!");
			return;
		}
		if (!StringUtils.hasText(cookingStyle)) {
			System.out.println("餐點料理方式錯誤!!");
			return;
		}
		if (price <= 0) {
			System.out.println("餐點價格錯誤!!");
			return;
		}
		// 檢查PK是否已存在
		if (newMealDao.existsById(new NewMealId(name, cookingStyle))) {
			System.out.println("餐點已存在!!");
			return;
		}
		// save: PK已存在 --> 更新 :PK不存在 --> 新增
		newMealDao.save(new NewMeal(name, cookingStyle, price));
		System.out.println("新增餐點成功");

	}

	@Override
	public void update(String name, String cookingStyle, int price) {
		if (!StringUtils.hasText(name)) {
			System.out.println("餐點名稱錯誤!!");
			return;
		}
		if (!StringUtils.hasText(cookingStyle)) {
			System.out.println("餐點料理方式錯誤!!");
			return;
		}
		if (price <= 0) {
			System.out.println("餐點價格錯誤!!");
			return;
		}
		// 檢查PK是否已存在
		if (!newMealDao.existsById(new NewMealId(name, cookingStyle))) {
			System.out.println("餐點不存在!!");
			return;
		}
		// save: PK已存在 --> 更新 :PK不存在 --> 新增
		newMealDao.save(new NewMeal(name, cookingStyle, price));
		System.out.println("更新餐點成功");

	}

	@Override
	public void add(NewMeal newMeal) {
		// 參數檢查
		if (!StringUtils.hasText(newMeal.getName())) {
			System.out.println("餐點名稱錯誤!!");
			return;
		}
		if (!StringUtils.hasText(newMeal.getCookingStyle())) {
			System.out.println("餐點料理方式錯誤!!");
			return;
		}
		if (newMeal.getPrice() <= 0) {
			System.out.println("餐點價格錯誤!!");
			return;
		}
		// 檢查PK是否已存在
		if (newMealDao.existsById(new NewMealId(newMeal.getName(), newMeal.getCookingStyle()))) {
			System.out.println("餐點已存在!!");
			return;
		}
		// save: PK已存在 --> 更新 :PK不存在 --> 新增
		newMealDao.save(newMeal);
		System.out.println("新增餐點成功");

	}

}
