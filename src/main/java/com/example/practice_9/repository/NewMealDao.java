package com.example.practice_9.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.practice_9.entity.NewMeal;
import com.example.practice_9.entity.NewMealId;

@Repository
public interface NewMealDao extends JpaRepository<NewMeal,NewMealId>{ 
	//<T, ID>T��new meal
	//NewMealId �ƦX���䪺�g�k	
	// ����ƫ��A
	
	// findBy �᭱���ݩʦW�١A���O�m�p���g�k�G��ܿz�����(�ݩ�)
	public List<NewMeal> findByName(String name);
	// �h���ݩʥ� And �άO Or �s���C����޿�Ϊk���� && �M || 
	public List<NewMeal> findByNameAndPrice(String name, int price);
	// �Ʀr�i�H����j�p�G�j��(GreaterThan)�G�j�󵥩�(GreaterThanEqual)�G�p��(LessThan)�G�p�󵥩�(LessThanEqual)
	// �W������k findByNameAndPrice ���� Price �O��������C
	public List<NewMeal> findByNameAndPriceGreaterThan(String name, int price);
	
	public List<NewMeal> findByCookingStyle(String cookingStyle);
}
