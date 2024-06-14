package com.example.practice_9.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.practice_9.entity.Meal;
import com.example.practice_9.repository.MealDao;
import com.example.practice_9.service.ifs.MealService;


@Service//將此 class 讓spring boot託管成 service類
public class MealServiceImpl implements MealService{
	
	@Autowired
	private MealDao mealDao;

	@Override
	public void addMeal(Meal meal) {
//		if(meal.getName()== null || meal.getName().isBlank()) {
//			
//		}
		//hasText 已經有判斷到字串是否是 null、空字串或是空白字串
		if(!StringUtils.hasText(meal.getName())) {
			System.out.println("資料格式錯誤!!");
			return;//此行的用意在於跳離此方法，不讓程式繼續往下執行
		}
		if(meal.getPrice() <= 0) {
			System.out.println("價格錯誤!!");
			return;
		}
		if(mealDao.existsById(meal.getName())) {//左邊寫法等同於!boo == true
			System.out.println("餐點名稱已存在");
			return;
		}
//		Meal res = mealDao.save(meal);
		mealDao.save(meal);// save(meal) 是指將meal的資料存到資料庫
		System.out.printf("新增餐點成功: %s %d\n",meal.getName(),meal.getPrice());
	}

	@Override
	public void addMeals(List<Meal> mealList) {
		for(Meal item : mealList) {
			if(!StringUtils.hasText(item.getName())) {
				System.out.println("資料格式錯誤!!");
				return;//此行的用意在於跳離此方法，不讓程式繼續往下執行
			}
			if(item.getPrice() <= 0) {
				System.out.println("價格錯誤!!");
				return;
			}
			if(mealDao.existsById(item.getName())) {//左邊寫法等同於!boo == true
				System.out.println("餐點名稱已存在");
				return;
		}
		
		}
		mealDao.saveAll(mealList);
		for(Meal item : mealList) {
			System.out.printf("新增餐點成功: %s %d\n",item.getName(),item.getPrice());
		}
	}

	@Override
	public void updateMeal(Meal meal) {//對已存在的資料做更新
		if(!StringUtils.hasText(meal.getName())) {
			System.out.println("資料格式錯誤!!");
			return;//此行的用意在於跳離此方法，不讓程式繼續往下執行
		}
		if(meal.getPrice() <= 0) {
			System.out.println("價格錯誤!!");
			return;
		}
		//因為此方法為更新資料 所以要先檢查資料是否已存在
//		Boolean boo = mealDao.existsById(meal.getName()); 左邊的方法是根據PK查詢資料是否存在
		//existsById 的Id 指的是PK 就是Entity有下 @Id的欄位
		if(!mealDao.existsById(meal.getName())) {//左邊寫法等同於!boo == false
			System.out.println("餐點名稱不存在");
			return;
		}
//		Meal res = mealDao.save(meal);
		//JPA的SAVE 1.PK欄位的資料若已存在 -> 更新資料 2.PK欄位的資料若不存在 ->新增資料
		mealDao.save(meal);// save(meal) 是指將meal的資料存到資料庫
		System.out.printf("新增餐點成功: %s %d\n",meal.getName(),meal.getPrice());
	}

	@Override
	public void findAll() {
		// TODO Auto-generated method stub
		List<Meal> res = mealDao.findAll();
		for(Meal item : res) {
			System.out.printf("新增餐點成功: %s %d\n",item.getName(),item.getPrice());
		}
	}

	@Override
	public void findByName(String name) {
		//meal 被 optional 包起來 主要用意是讀寫程式的人可以去判斷從資料庫撈回來的資料(meal)
		//是否有資料，資料不存在為(null)
		//會用findById 是因為name這欄位是PK,JPA方法中的ID指的就是PK
		//Entity的屬性有加@Id
		Optional<Meal> op = mealDao.findById(name);//唯一方法
		if(op.isEmpty()) {// isEmpty()==true;表示被optional 包起來的meal沒資料
			System.out.println("餐點不存在");
			return;
			
		}
		Meal meal = op.get();//op.get() 是將Optional 包起來的Entity(meal)取出
		System.out.printf("取得餐點成功 %s %d \n", meal.getName(),meal.getPrice());
	}

	@Override
	public void existsByName(String name) {
		//查詢資料可以用以下2個方法:
		//1.findById 或是 findby屬性名稱:適用於對查詢的資料做後續的處理 如findByName的方法
		//2. existById 或是existsBy屬性名稱:單純只是查詢資料是否存在，不對取得資料作業操作
		 boolean res = mealDao.existsById(name);
		 if(res) {// res ==true 
			 System.out.printf("餐點 %s 存在 \n",name);
		 }else {
			 System.out.printf("餐點 %s 不存在 \n",name);
		 }
		
	}
	@Override
	public void findByPrice(int price) {
		List<Meal> res = mealDao.findByPrice(price);
		for(Meal item : res) {
			System.out.printf("查詢餐點成功: %s %d\n",item.getName(),item.getPrice());
		}
	}
	
}
