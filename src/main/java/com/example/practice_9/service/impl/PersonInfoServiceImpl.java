package com.example.practice_9.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.practice_9.entity.PersonInfo;
import com.example.practice_9.repository.PersonInfoDao;
import com.example.practice_9.service.ifs.PersonInfoService;

@Service
public class PersonInfoServiceImpl implements PersonInfoService {

	@Autowired
	private PersonInfoDao personInfoDao;

	@Override
	public void create(List<PersonInfo> personInfoList) {
		if (personInfoList.isEmpty()) {
			System.out.println("資料不得為空!!");
			return;
		}
		// idList 用來蒐集 PersonInfoList 中所有的 id。
		List<String> idList = new ArrayList<>();
		for (PersonInfo item : personInfoList) {
			// 參數檢查
			if (!StringUtils.hasText(item.getId())) {
				System.out.println("id 不得為空!!");
				return;
			}
			if (!StringUtils.hasText(item.getName())) {
				System.out.println("name 不得為空!!");
				return;
			}
			if (item.getAge() <= 0) {
				System.out.println("age 錯誤!!");
				return;
			}
			// 蒐集id
			idList.add(item.getId());
//			// 檢查是否已存在
//			if (personInfoDao.existsById(item.getId())) {
//				System.out.println("資料已存在!!");
//				return;
//			}
		}
		// 透過 Dao 的 findByIdIn 來檢查 personInfoList 中的所有 id 是否有存在。
		List<PersonInfo> res = personInfoDao.findByIdIn(idList);
		if(!res.isEmpty()) {// 若不為空，表示至少有一個 id 是已存在於 DB。
			System.out.println("資料已存在!!");
			return;
		}
		// ============================================================
		
		personInfoDao.saveAll(personInfoList);
		System.out.println("資料新增成功!!");
	}

	@Override
	public void getAll() {
		List<PersonInfo> res = personInfoDao.findAll();
		for(PersonInfo item: res) {
//			System.out.println("id:" + item.getId() + "; name:" + item.getName());
			System.out.printf("id: %s; name:%s; age:%d; city:%s \n", item.getId() , item.getName() , item.getAge() , item.getCity());
		}
	}

//	@Override
//	public void findAll() {
//		List<PersonInfo> res = personInfoDao.findAll();
//		for(PersonInfo item: res) {
////			System.out.println("id:" + item.getId() + "; name:" + item.getName());
//			System.out.printf("id: %s; name:%s; age:%d; city:%s \n", item.getId() , item.getName() , item.getAge() , item.getCity());
//		}
//	}

	@Override
	public void findById(String id) {
		// JPA 原本提供的方法中，只有此方法是被 Optional 包住的，
		// 主要是提醒使用者要去判斷取回的資訊是否為null(不存在)
		Optional<PersonInfo> op = personInfoDao.findById(id);
		// 判斷被 Optional 包住的資訊是否為 null ，可以用 isEmpty() 或 isPresent() 判斷
		if(op.isEmpty()) {
			System.out.println("找不到相關資訊!!");
			return;
		}
		// 將 Optional 中的資訊取出
		PersonInfo res = op.get();
		System.out.printf("id: %s; name:%s; age:%d; city:%s \n", res.getId() , res.getName() , res.getAge() , res.getCity());
	}


	@Override
	public void findByAgeGreaterThan(int age) {
		 List<PersonInfo> res =personInfoDao.findByAgeGreaterThan(age);
		 for(PersonInfo item: res) {
//				System.out.println("id:" + item.getId() + "; name:" + item.getName());
				System.out.printf("id: %s; name:%s; age:%d; city:%s \n", item.getId() , item.getName() , item.getAge() , item.getCity());
			}
	}

	@Override
	public void findByAgeLessThanEqualOrderByAgeAsc(int age) {
		List<PersonInfo> res =personInfoDao.findByAgeLessThanEqualOrderByAgeAsc(age);
		for(PersonInfo item: res) {
//			System.out.println("id:" + item.getId() + "; name:" + item.getName());
			System.out.printf("id: %s; name:%s; age:%d; city:%s \n", item.getId() , item.getName() , item.getAge() , item.getCity());
		}
	}
}
