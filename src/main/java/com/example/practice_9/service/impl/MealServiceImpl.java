package com.example.practice_9.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.practice_9.entity.Meal;
import com.example.practice_9.repository.MealDao;
import com.example.practice_9.service.ifs.MealService;


@Service//�N�� class ��spring boot�U�ަ� service��
public class MealServiceImpl implements MealService{
	
	@Autowired
	private MealDao mealDao;

	@Override
	public void addMeal(Meal meal) {
//		if(meal.getName()== null || meal.getName().isBlank()) {
//			
//		}
		//hasText �w�g���P�_��r��O�_�O null�B�Ŧr��άO�ťզr��
		if(!StringUtils.hasText(meal.getName())) {
			System.out.println("��Ʈ榡���~!!");
			return;//���檺�ηN�b���������k�A�����{���~�򩹤U����
		}
		if(meal.getPrice() <= 0) {
			System.out.println("������~!!");
			return;
		}
		if(mealDao.existsById(meal.getName())) {//����g�k���P��!boo == true
			System.out.println("�\�I�W�٤w�s�b");
			return;
		}
//		Meal res = mealDao.save(meal);
		mealDao.save(meal);// save(meal) �O���Nmeal����Ʀs���Ʈw
		System.out.printf("�s�W�\�I���\: %s %d\n",meal.getName(),meal.getPrice());
	}

	@Override
	public void addMeals(List<Meal> mealList) {
		for(Meal item : mealList) {
			if(!StringUtils.hasText(item.getName())) {
				System.out.println("��Ʈ榡���~!!");
				return;//���檺�ηN�b���������k�A�����{���~�򩹤U����
			}
			if(item.getPrice() <= 0) {
				System.out.println("������~!!");
				return;
			}
			if(mealDao.existsById(item.getName())) {//����g�k���P��!boo == true
				System.out.println("�\�I�W�٤w�s�b");
				return;
		}
		
		}
		mealDao.saveAll(mealList);
		for(Meal item : mealList) {
			System.out.printf("�s�W�\�I���\: %s %d\n",item.getName(),item.getPrice());
		}
	}

	@Override
	public void updateMeal(Meal meal) {//��w�s�b����ư���s
		if(!StringUtils.hasText(meal.getName())) {
			System.out.println("��Ʈ榡���~!!");
			return;//���檺�ηN�b���������k�A�����{���~�򩹤U����
		}
		if(meal.getPrice() <= 0) {
			System.out.println("������~!!");
			return;
		}
		//�]������k����s��� �ҥH�n���ˬd��ƬO�_�w�s�b
//		Boolean boo = mealDao.existsById(meal.getName()); ���䪺��k�O�ھ�PK�d�߸�ƬO�_�s�b
		//existsById ��Id �����OPK �N�OEntity���U @Id�����
		if(!mealDao.existsById(meal.getName())) {//����g�k���P��!boo == false
			System.out.println("�\�I�W�٤��s�b");
			return;
		}
//		Meal res = mealDao.save(meal);
		//JPA��SAVE 1.PK��쪺��ƭY�w�s�b -> ��s��� 2.PK��쪺��ƭY���s�b ->�s�W���
		mealDao.save(meal);// save(meal) �O���Nmeal����Ʀs���Ʈw
		System.out.printf("�s�W�\�I���\: %s %d\n",meal.getName(),meal.getPrice());
	}

	@Override
	public void findAll() {
		// TODO Auto-generated method stub
		List<Meal> res = mealDao.findAll();
		for(Meal item : res) {
			System.out.printf("�s�W�\�I���\: %s %d\n",item.getName(),item.getPrice());
		}
	}

	@Override
	public void findByName(String name) {
		//meal �Q optional �]�_�� �D�n�ηN�OŪ�g�{�����H�i�H�h�P�_�q��Ʈw���^�Ӫ����(meal)
		//�O�_����ơA��Ƥ��s�b��(null)
		//�|��findById �O�]��name�o���OPK,JPA��k����ID�����N�OPK
		//Entity���ݩʦ��[@Id
		Optional<Meal> op = mealDao.findById(name);//�ߤ@��k
		if(op.isEmpty()) {// isEmpty()==true;��ܳQoptional �]�_�Ӫ�meal�S���
			System.out.println("�\�I���s�b");
			return;
			
		}
		Meal meal = op.get();//op.get() �O�NOptional �]�_�Ӫ�Entity(meal)���X
		System.out.printf("���o�\�I���\ %s %d \n", meal.getName(),meal.getPrice());
	}

	@Override
	public void existsByName(String name) {
		//�d�߸�ƥi�H�ΥH�U2�Ӥ�k:
		//1.findById �άO findby�ݩʦW��:�A�Ω��d�ߪ���ư����򪺳B�z �pfindByName����k
		//2. existById �άOexistsBy�ݩʦW��:��¥u�O�d�߸�ƬO�_�s�b�A������o��Ƨ@�~�ާ@
		 boolean res = mealDao.existsById(name);
		 if(res) {// res ==true 
			 System.out.printf("�\�I %s �s�b \n",name);
		 }else {
			 System.out.printf("�\�I %s ���s�b \n",name);
		 }
		
	}
	@Override
	public void findByPrice(int price) {
		List<Meal> res = mealDao.findByPrice(price);
		for(Meal item : res) {
			System.out.printf("�d���\�I���\: %s %d\n",item.getName(),item.getPrice());
		}
	}
	
}
