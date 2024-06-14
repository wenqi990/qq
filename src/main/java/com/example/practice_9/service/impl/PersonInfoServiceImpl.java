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
			System.out.println("��Ƥ��o����!!");
			return;
		}
		// idList �Ψӻ`�� PersonInfoList ���Ҧ��� id�C
		List<String> idList = new ArrayList<>();
		for (PersonInfo item : personInfoList) {
			// �Ѽ��ˬd
			if (!StringUtils.hasText(item.getId())) {
				System.out.println("id ���o����!!");
				return;
			}
			if (!StringUtils.hasText(item.getName())) {
				System.out.println("name ���o����!!");
				return;
			}
			if (item.getAge() <= 0) {
				System.out.println("age ���~!!");
				return;
			}
			// �`��id
			idList.add(item.getId());
//			// �ˬd�O�_�w�s�b
//			if (personInfoDao.existsById(item.getId())) {
//				System.out.println("��Ƥw�s�b!!");
//				return;
//			}
		}
		// �z�L Dao �� findByIdIn ���ˬd personInfoList �����Ҧ� id �O�_���s�b�C
		List<PersonInfo> res = personInfoDao.findByIdIn(idList);
		if(!res.isEmpty()) {// �Y�����šA��ܦܤ֦��@�� id �O�w�s�b�� DB�C
			System.out.println("��Ƥw�s�b!!");
			return;
		}
		// ============================================================
		
		personInfoDao.saveAll(personInfoList);
		System.out.println("��Ʒs�W���\!!");
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
		// JPA �쥻���Ѫ���k���A�u������k�O�Q Optional �]���A
		// �D�n�O�����ϥΪ̭n�h�P�_���^����T�O�_��null(���s�b)
		Optional<PersonInfo> op = personInfoDao.findById(id);
		// �P�_�Q Optional �]����T�O�_�� null �A�i�H�� isEmpty() �� isPresent() �P�_
		if(op.isEmpty()) {
			System.out.println("�䤣�������T!!");
			return;
		}
		// �N Optional ������T���X
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
