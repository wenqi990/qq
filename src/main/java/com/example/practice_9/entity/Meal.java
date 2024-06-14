package com.example.practice_9.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity//�ЬO�����O Entity���A�å�� Spring boot �O�U��
//�U�ެO��Spring boot �|�۰ʫإߦ����O�A�æb�ڭ̻ݭn�ϥήɱN�`�J����w���ܼ�
//����ΨӸ��ƪ��s��: ���w�n�N�����O����i��s��
//name�����᭱���r��O��ƪ��W��
@Table(name = "meal")
public class Meal {
	
	@Id//�ΨӪ�����ݩʹ��������OPK(Primay Key�A�D��)
	//@Column:�n�Ψӫ��w���ݩʭn�������찵�s��:name�����᭱���r��O��ƪ��W��
	@Column(name = "name")
	private String name;
	
	@Column(name = "price")
	private int price;

	public Meal() {
		super();
	}

	public Meal(String name, int price) {
		super();
		this.name = name;
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
		

}
