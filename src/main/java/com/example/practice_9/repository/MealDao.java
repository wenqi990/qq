package com.example.practice_9.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.practice_9.entity.Meal;

@Repository//�⦹�������Spring Boot �U�ަ���ƳB�z��
public interface MealDao extends JpaRepository<Meal,String> {
	//�~�� JpaRepository �i�H�z�L�w�q��k�����ݭn��@�N�i�H�ާ@���
	//JpaRepository<Meal,String> :Meal�O���n�ާ@���� Entity(class)
	//                           :String�O����Entity �����[@Id���ݩʪ���ƫ��A
	//                            �Ҧp:�ݩʪ���ƫ��A�O int�A�b�o�N�n�g Integer
	
	//�۩w�q JPA�y�k
	//1. JPA���d�߸�ƨϥ�finally �᭱�����ݩʦW�� (���O@column���r��)
	//2. �]���n�A�d�����price ���OPK �ҥH���^�Ӫ���ƥi�঳�h�� �]���ϥ�list ��
	//3. JPA�y�k�n��`�p�m�p���g�k
	public List<Meal> findByPrice(int price);
	
	//existsBy �ݩR�W�� �d�߸�ƬO�_�s�b �^�Ǫ��Oboolean�� 
	//�`�N exists �̫ᦳ��s
	public boolean existsByPrice(int price);
	
}
