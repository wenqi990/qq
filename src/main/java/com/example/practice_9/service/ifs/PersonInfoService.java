package com.example.practice_9.service.ifs;

import java.util.List;

import com.example.practice_9.entity.PersonInfo;

public interface PersonInfoService {
	
	public void create(List<PersonInfo> personInfoList);

	public void getAll();
	
	public void findById(String id);
	
	public void findByAgeGreaterThan(int age);
	
	public void findByAgeLessThanEqualOrderByAgeAsc(int age);
}
