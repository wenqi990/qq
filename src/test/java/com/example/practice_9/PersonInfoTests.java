package com.example.practice_9;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;

import com.example.practice_9.entity.PersonInfo;
import com.example.practice_9.repository.PersonInfoDao;
import com.example.practice_9.service.ifs.PersonInfoService;
import com.example.practice_9.vo.JoinVo;

@SpringBootTest
public class PersonInfoTests {

	@Autowired
	private PersonInfoService personInfoService;
	
	@Autowired
	private PersonInfoDao dao;

	@Test
	public void createTest() {
//		     PersonInfo p     = new PersonInfo()
		List<PersonInfo> list = new ArrayList<>();
		list.add(new PersonInfo("E01", "EEA", 10, "Taichung"));
		list.add(new PersonInfo("E02", "EFE", 25, "Japan"));
		list.add(new PersonInfo("F02", "GGG", 45, "Taiwan"));
		personInfoService.create(list);
	}

	@Test
	public void createTest2() {
		// 使用 list.of 出來的長度固定，即初始化的資料有幾筆，就固定了，無法再對此 list 增刪。
		List<PersonInfo> list = List.of( //
				new PersonInfo("A01", "AAA", 12, "Tainan"), //
				new PersonInfo("A02", "AA2", 20, "Taipei"), //
				new PersonInfo("B02", "BBB", 35, "KH"));

		// 使用 Arrays.asList 出來的長度固定，即初始化的資料有幾筆，就固定了，無法再對此 list 增刪。
		List<PersonInfo> list1 = Arrays.asList(
				new PersonInfo("A01", "AAA", 12, "Tainan"), //
				new PersonInfo("A02", "AA2", 20, "Taipei"), //
				new PersonInfo("B02", "BBB", 35, "KH") //
				);

		// 使用 ArrayList<>() 產生出來的長度動態。
		List<PersonInfo> list2 = new ArrayList<>();
		list2.add(new PersonInfo("A01", "AAA", 12, "Tainan"));
		list2.add(new PersonInfo("A02", "AA2", 20, "Taipei"));
		list2.add(new PersonInfo("B02", "BBB", 35, "KH"));
		personInfoService.create(list);
	}

	@Test
	public void findAllTest() {
		personInfoService.getAll();
	}
	
	@Test 
	public void findTest() {
		personInfoService.findByAgeLessThanEqualOrderByAgeAsc(20);
	}
	
	@Test
	public void findTest6() {
		List<PersonInfo> res = dao.findByAgeLessThanOrAgeGreaterThan(12 , 50);
		for(PersonInfo item: res) {
//			System.out.println("id:" + item.getId() + "; name:" + item.getName());
			System.out.printf("id: %s; name:%s; age:%d; city:%s \n", item.getId() , item.getName() , item.getAge() , item.getCity());
		}
	}
	
	@Test
	public void findTest7() {
		List<PersonInfo> res = dao.findByAgeBetween(12, 50);
		for(PersonInfo item: res) {
//			System.out.println("id:" + item.getId() + "; name:" + item.getName());
			System.out.printf("id: %s; name:%s; age:%d; city:%s \n", item.getId() , item.getName() , item.getAge() , item.getCity());
		}
	}
	
	@Test
	public void findTest7_1() {
		List<PersonInfo> res = dao.findByAgeBetweenOrderByAgeDesc(12, 50);
		for(PersonInfo item: res) {
//			System.out.println("id:" + item.getId() + "; name:" + item.getName());
			System.out.printf("id: %s; name:%s; age:%d; city:%s \n", item.getId() , item.getName() , item.getAge() , item.getCity());
		}
	}
	
	@Test
	public void findTest7_2() {
		List<PersonInfo> res = dao.findTop3ByAgeBetweenOrderByAgeDesc(12, 50);
		for(PersonInfo item: res) {
//			System.out.println("id:" + item.getId() + "; name:" + item.getName());
			System.out.printf("id: %s; name:%s; age:%d; city:%s \n", item.getId() , item.getName() , item.getAge() , item.getCity());
		}
	}
	
	@Test
	public void findTest8() {
		List<PersonInfo> res = dao.findByCityContaining("Tai");
		for(PersonInfo item: res) {
//			System.out.println("id:" + item.getId() + "; name:" + item.getName());
			System.out.printf("id: %s; name:%s; age:%d; city:%s \n", item.getId() , item.getName() , item.getAge() , item.getCity());
		}
	}
	
	@Test
	public void findTest9() {
		List<PersonInfo> res = dao.findByAgeGreaterThanAndCityContainingOrderByAgeDesc(20, "Tai");
		for(PersonInfo item: res) {
//			System.out.println("id:" + item.getId() + "; name:" + item.getName());
			System.out.printf("id: %s; name:%s; age:%d; city:%s \n", item.getId() , item.getName() , item.getAge() , item.getCity());
		}
	}
	
	@Test
	public void findTest10() {
		List<PersonInfo> res = dao.findByCityIn(List.of("KH","Taipei","Tainan"));
		for(PersonInfo item: res) {
//			System.out.println("id:" + item.getId() + "; name:" + item.getName());
			System.out.printf("id: %s; name:%s; age:%d; city:%s \n", item.getId() , item.getName() , item.getAge() , item.getCity());
		}
	}
	
	@Test //增
	public void insertTest() {
		int res = dao.insert("A99", "A99", 25, "Tainan");
		System.out.println(res);
	}
	
	@Test //修	
	public void updateTest() {
		int res = dao.update("A99", 18, "Taipei");
		System.out.println(res);
	}
	
	@Test //刪	
	public void deleteTest() {
		int res = dao.delete("A99");
		System.out.println(res);
	}
	
	@Test //選擇撈取
	public void selectAllTest() {
		List<PersonInfo> res = dao.selectAll();
		System.out.println(res.size());
	}
	
	@Test // 撈取比對
	public void selectAllByAgeTest() {
		List<PersonInfo> res = dao.selectAllByAge(18);
		System.out.println(res.size());
    }
	
	@Test // 不重複
	public void selectDistinctTest() {
		List<PersonInfo> res = dao.selectDistinctCity();
		System.out.println(res.size());
		res.forEach(item ->{
			System.out.println(item.getCity());
		});
    }
	
	@Test // 排序
	public void selectAllByAgeOrderByTest() {
		List<PersonInfo> res = dao.selectAllByAgeOrderByCity(18);
		System.out.println(res.size());
		res.forEach(item ->{
			System.out.println(item.getCity());
		});
    }
	
	@Test // 排序 + 名字
	public void selectAllByAgeOrderByTest2() {
		List<PersonInfo> res = dao.selectAllByAgeOrderByCityAndName(18);
		System.out.println(res.size());
		res.forEach(item ->{
			System.out.println(item.getCity() + ": " + item.getName());
		});
    }
	
	@Test // 限制
	public void selectAllByAgeLimitTest() {
		List<PersonInfo> res = dao.selectAllByAgeLimit(18, 3);
		System.out.println(res.size());
		res.forEach(item ->{
			System.out.println(item.getCity() + ": " + item.getName());
		});
    }
	
	@Test // 限制 從第幾筆限制
	public void selectAllByAgeLimitTest2() {
		List<PersonInfo> res = dao.selectAllByAgeLimit2(18, 2, 3);
		System.out.println(res.size());
		res.forEach(item ->{
			System.out.println(item.getCity() + ": " + item.getName());
		});
    }
	
	@Test // 之間
	public void selectAllByAgeBetweenTest() {
		List<PersonInfo> res = dao.selectAllByAgeBetween(18, 30);
		System.out.println(res.size());
		res.forEach(item ->{
			System.out.println(item.getCity() + ": " + item.getName());
		});
    }
	
	@Test // 不連續區間
	public void selectAllByCityInTest() {
		List<PersonInfo> res = dao.selectAllByCityIn("Taipei", "Taiwan", "Japan");
		System.out.println(res.size());
		res.forEach(item ->{
			System.out.println(item.getCity() + ": " + item.getName());
		});
    }
	
	@Test // 撈取關鍵字
	public void selectAllByCityLikeTest() {
		List<PersonInfo> res = dao.selectAllByCityLike("Tai");
		System.out.println(res.size());
		res.forEach(item ->{
			System.out.println(item.getCity() + ": " + item.getName());
		});
    }
	
	@Test // 撈取關鍵字
	public void selectAllByCityRegexpTest() {
		List<PersonInfo> res = dao.selectAllByCityRegexp("Tai");
		System.out.println(res.size());
		res.forEach(item ->{
			System.out.println(item.getCity() + ": " + item.getName());
		});
    }
	
	@Test // 撈取關鍵字
	public void selectAllByCityRegexpTest2() {
		List<PersonInfo> res = dao.selectAllByCityRegexp2("Tai", "k");
		System.out.println(res.size());
		res.forEach(item ->{
			System.out.println(item.getCity() + ": " + item.getName());
		});
    }
	
	@Test // 跨表欄位資料
	public void joinTest() {
		List<Map<String, Object>> res = dao.joinTables();
		System.out.println(res);
	}
	
	
	@Test // 跨表欄位資料
	public void joinTest2() {
		List<JoinVo> res = dao.joinTables2();
		System.out.println(res);
		res.forEach(item ->{
			System.out.printf(" %s : %s : %d \n", item.getId(), item.getName(), item.getBelance());
		});
	}
	
	@Test // 跨表欄位資料
	public void leftJoinTest() {
		List<JoinVo> res = dao.leftJoinTables();
		System.out.println(res);
		res.forEach(item ->{
			System.out.printf(" %s : %s : %d \n", item.getId(), item.getName(), item.getBelance());
		});
	}
	
	@Test // 限制查詢
	public void JoinLimitTest() {
		List<JoinVo> res = dao.joinTablesLimit(PageRequest.of(0, 2));
		System.out.println(res);
		res.forEach(item ->{
			System.out.printf(" %s : %s : %d \n", item.getId(), item.getName(), item.getBelance());
		});
	}
	
	@Test // 子查詢
	public void subQueryTest() {
		List<PersonInfo> res = dao.selectAllByIdIn();
		System.out.println(res);
		res.forEach(item ->{
			System.out.printf(" %s : %s :\n", item.getId(), item.getName());
		});
	}
	
	@Test // 子查詢
	public void selectExistsTest() {
		List<PersonInfo> res = dao.selectAllByIdExists("A01");
		System.out.println(res);
		if(res.isEmpty()) {
			System.out.println("Not found!!");
			return;
		}
		res.forEach(item ->{
			System.out.printf(" %s : %s :\n", item.getId(), item.getName());
		});
	}
	
	@Test //更新
	public void updateCaseWhenTest() {
		int res = dao.updateUsingCaseWhen("A01", "AAA", 12, null);
		System.out.println(res);
	}
	
	
	@Test // 分類加總
	public void groupByTest() {
		List<Map<String, Object>> res = dao.groupBy();
		res.forEach(item ->{
			item.forEach((k,v) -> {
				System.out.println(k + ": " + v);
			});
		});
	}
	
	@Test // 分類加總篩選
	public void groupByHavingTest() {
		List<Map<String, Object>> res = dao.groupByHaving(30);
		res.forEach(item ->{
			item.forEach((k,v) -> {
				System.out.println(k + ": " + v);
			});
		});
	}
}
