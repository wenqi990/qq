	package com.example.practice_9.repository;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.practice_9.entity.PersonInfo;
import com.example.practice_9.vo.JoinVo;

@Repository
public interface PersonInfoDao extends JpaRepository<PersonInfo, String>{
	
	public List<PersonInfo> findByAgeGreaterThan(int age);

	public List<PersonInfo> findByAgeLessThanEqualOrderByAgeAsc(int age);
	// orderBy 的排序預設是 Asc ，所以上下兩個方法的結果會一樣。
	public List<PersonInfo> findByAgeLessThanEqualOrderByAge(int age);
	
	public List<PersonInfo> findByAgeLessThanOrAgeGreaterThan(int age1 , int age2);
	
	public List<PersonInfo> findByAgeLessThanEqualAndAgeGreaterThanEqual(int age1 , int age2);
	// 上下兩個方法結果一樣
	// Between 屬於連續區間，前面只會有一個屬性，但方法的參數會有兩個(有包含)。
	public List<PersonInfo> findByAgeBetween(int age1 , int age2);

	public List<PersonInfo> findByAgeBetweenOrderByAgeDesc(int age1 , int age2);
	
	public List<PersonInfo> findTop3ByAgeBetweenOrderByAgeDesc(int age1 , int age2);
	// 限制取回筆數: 使用 top數字 或 first數字 : 寫在 find 和 By 中間 
	public List<PersonInfo> findFirst3ByAgeBetweenOrderByAgeDesc(int age1 , int age2);
	// 使用 Containing，參數中的 Keyword 不分大小寫，其查詢的結果會一樣
	public List<PersonInfo> findByCityContaining(String KeyWord);
	
	public List<PersonInfo> findByAgeGreaterThanAndCityContainingOrderByAgeDesc(int age , String KeyWord);
	
	public List<PersonInfo> findByCityIn(List<String> cityList);
	
	public List<PersonInfo> findByIdIn(List<String> idlist);
	
	//==================================================================
	
	// JPQL，nativeQuery 預設是 false。
	// nativeQuery = true 時，語法中的表和欄位名稱是使用 資料庫中資料表(@Table 後面的名字)和欄位(@Column 中的名字)的名稱。
	// insert 只能使用於 nativeQuery = true。 // limit 也是
	// 方法的回傳型態用 int 接，可用來表示新增成功資料的筆數，0代表沒有新增任何資料。
	// values()中的 ?數字，指的是方法 insert()中參數的位置。 
	// JPQL: insert/update/delete 要求要加上 @Transactional 和 @Modifying。
	// @Transactional import 的 library， javax 和 springframework 都可以使用。
	//   兩者差異可參照 PPT spring boot_02 @Transactional 部分。
	@Transactional
	@Modifying
	@Query(value = "insert into person_info (id, name, age, city) values (?1, ?2, ?3, ?4)",
			nativeQuery = true)         
	public int insert(String inputId, String inputName, int intputAge, String inputCity );
	
	@Transactional
	@Modifying
	@Query(value = "insert into person_info (id, name, age, city)" // 增
			+ " values (:inputId, :inputName, :intputAge, :inputCity)",
			nativeQuery = true)         
	public int insert2(
			@Param("inputId") String id, //
			@Param("inputName") String name, //
			@Param("intputAge") int age, //
			@Param("inputCity") String city ); //
	
	// insert info if not exists
	// 建議使用 JPA 的 save
	@Transactional
	@Modifying
	@Query(value = "insert into person_info (id, name, age, city)" // 增
			+ " select :inputId, :inputName, :intputAge, :inputCity " 
			+ " where not exists (select 1 from person_info where id =:inputId)", 
			nativeQuery = true)         
	public int insert3(
			@Param("inputId") String id, //
			@Param("inputName") String name, //
			@Param("intputAge") int age, //
			@Param("inputCity") String city ); //
	
	//==================  update
	// 回傳型態是 int 表示資料更新成功筆數，0表示沒有任何資料更新成功。
	// 記得要加 where 條件式，沒加條件會更新全部資料。
	@Transactional
	@Modifying
	@Query(value = "update person_info set age = ?2, city = ?3 "
			+ " where id = ?1 ", nativeQuery = true)
	public int update(String id, int age, String city);
	
	// nativeQuery = false(預設) 時，語法中表的名稱是 Entity 的類別名稱，欄位名稱則是屬性變數的名稱。
	@Transactional
	@Modifying            //entity名稱          //屬性變數名稱
	@Query(value = "update PersonInfo set age = ?2, city = ?3 "
			+ " where id = ?1 ")
	public int update2(String id, int age, String city);
	
	//================== delete
	@Transactional
	@Modifying
	@Query("delete from PersonInfo where id = ?1 ")
	public int delete(String id);
	
	//================== select
	// 語法中有 * 表示所有欄位，要使用 nativeQuery = true 。
	@Query(value = "select * from person_info", nativeQuery = true )
	public List<PersonInfo> selectAll();
	
	// nativeQuery = true，只能撈取所有欄位。
	@Query(value = "select id, name, age, city from person_info", nativeQuery = true)
	public List<PersonInfo> selectAll1();
	
	// nativeQuery = false，要撈取的資料欄位必須要用 建構方法 的寫法: new 類別名稱(屬性名稱)
	// new PersonInfo(id, name, age, city): 這個建構方法一定要存在。
	// 就是看語法中會用到撈幾個參數，其對應的建構方法就得要存在。
	@Query(value = "select new PersonInfo( id, name, age, city ) from PersonInfo")
	public List<PersonInfo> selectAll2();
	
	// 數字大小可直接用比較符號(>, <, =)。
	@Query(value = "select * from person_info where age >= ?1", nativeQuery = true )
	public List<PersonInfo> selectAllByAge(int age);
	
	// !不等於可以用1.!=  2.<> 來表示。
	@Query(value = "select * from person_info where age <> ?1", nativeQuery = true )
	public List<PersonInfo> selectAllByAge2(int age);
	
	// 只需要撈出不重複的 city 資料，使用 new PersonInfo(city)，所以 Entity PersonInfo 中也要有對應的建構方法。
	@Query("select distinct new PersonInfo(city) from PersonInfo")
	public List<PersonInfo> selectDistinctCity();
	
	// 別名(Alias): 在欄位或表名稱的後面加上 as 別名(名稱自定義)，as 可省略。
	// 別名使用在 Entity 名稱後面表示整個 Entity，所以 select 別名 可以當作撈取一整個(所有欄位) Entity。
//	@Query(value = "select PI from PersonInfo  PI where age > ?1"); 省略 as。
	@Query(value = "select PI from PersonInfo as PI where age > ?1")
	public List<PersonInfo> selectAllByAge3(int age);
	
	//==================  Order
	
	// order by 欄位: 預設是 ASC，可不寫。
	// order by 欄位是指將篩選完的的資料再根據指定的欄位排序，所以排序的欄位不會是方法的參數。
	@Query(value = "select PI from PersonInfo as PI where age > ?1 order by city desc")
	public List<PersonInfo> selectAllByAgeOrderByCity(int age); // 排序
	
	// order by city desc, name asc: 在 city 有相同值的情形下，才會用到 name 去排序(asc 是預設，可省略)
	// 若只有指定一個欄位排序，有相同值的情形下，預設會使用 PK 的欄位 + ASC 排序。
	@Query(value = "select PI from PersonInfo as PI where age > ?1 order by city desc, name asc")
	public List<PersonInfo> selectAllByAgeOrderByCityAndName(int age); // 排序
	
	//==================  Limit
	
	// limit: 只能使用在 nativeQuery = true。
	@Query(value = "select * from person_info where age > ?1 limit ?2", nativeQuery = true)
	public List<PersonInfo> selectAllByAgeLimit(int age, int limit);
	
	// limit: 數字1, 數字2: 數字1表示起始索引位置，數字2表示回傳的限制筆數。
	// limit: 數字1: 表示索引位置從0開始，等同於 limit 0, 數字1。
	@Query(value = "select * from person_info where age > ?1 limit ?2, ?3", nativeQuery = true)
	public List<PersonInfo> selectAllByAgeLimit2(int age, int startIndex, int limit);
	
	// order by + limit
	@Query(value = "select * from person_info where age > ?1 order by city limit ?2", nativeQuery = true)
	public List<PersonInfo> selectAllByAgeOrderByCityLimit(int age, int limit);
	
	//==================  Between
	
	// between 參數1 and 參數2:
	// 1. 兩個參數間要用 and 連接。
	// 2. 語法的邏輯是 大於等於(>=) 參數1 and 小於等於(<=) 參數2
	@Query(value = "select * from person_info where age between ?1 and ?2 ", nativeQuery = true)
	public List<PersonInfo> selectAllByAgeBetween(int age1, int age2);
	
	@Query(value = "select PI from PersonInfo as PI where age between ?1 and ?2 ")
	public List<PersonInfo> selectAllByAgeBetween2(int age1, int age2);
	
	//==================  In And Not In
	
	// in 是屬於不連續的區間
	// JPQL 的寫法不允許動態參數，無彈性: 因此建議用 JPA 的 in。
	// JPA in 的寫法: findByCityIn(List<String> citylist);
	@Query(value = "select PI from PersonInfo as PI where city in (?1, ?2, ?3)")
	public List<PersonInfo> selectAllByCityIn(String city1, String city2, String city3);
	
	//==================  Like And Not Like
	
	// like 要搭配 % 才會具有模糊比對的效果:% 可視為 0~任意字元。
	// 不搭配 %，是完全比對。
	// 搜尋的關鍵字不區分大小寫。
	// %北: 以北當結尾，前面是 %，所以可以是 0~多個字元: 北、新北、台北都符合。
	// 北%: 以北當開頭，後面的 % 可以是 0~多個字元
	// %北%: 只要有北的都符合。
//	@Query(value = "select PI from PersonInfo as PI where city like %?1% ")
	@Query(value = "select * from person_info where city like %?1% ", nativeQuery = true)
	public List<PersonInfo> selectAllByCityLike(String city);
	
	// regexp 關鍵字 其效果等同於 Like %關鍵字%。
	// 關鍵字前後不能加 %，加了會報錯。
	// 只能用於 nativeQuery = true，
	@Query(value = "select * from person_info where city Regexp ?1 ", nativeQuery = true)
	public List<PersonInfo> selectAllByCityRegexp(String city);
	
	// 因為邏輯運算符號(| 或 &)不屬於原本 SQL 語法中認定的字符，所以要用 concat 將之與參數串接。
	// concat 是將方法中的所有參數中串接成字串，所以邏輯符號記得要加上單引號。
	@Query(value = "select * from person_info where city Regexp concat (?1, '|', ?2) ", nativeQuery = true)
	public List<PersonInfo> selectAllByCityRegexp2(String city1, String city2);
	
	//==================  Join
	
	// 因為撈取的欄位是跨不同的表，沒有對應的 Entity 可以將資料 mapping 回來。
	// 所以使用 Map<String, Object> 表示: Key 是欄位名稱，value 是欄位對應的值。
	// 因為取回來的資料可能會有多筆，用 List 接。
	// 要解析回傳的結果，就一定使用雙層 for 迴圈，List 一層，Map 一層。
	// join 不建議使用在 nativeQuery = true
	@Query(value ="select p.id, p.name, a.balance from person_info as p join atm as a "
			+ " on p.id = a.account", nativeQuery = true)
	public List<Map<String, Object>> joinTables();
	
	// 因為 JoinVo 沒有被 spring boot 託管，所以前面需要加上完整路徑。
	@Query("select new com.example.practice_9.vo.JoinVo(P.id, P.name, A.balance) "
			+ " from PersonInfo as P join Atm as A "
			+ " on P.id = A.account")
	public List<JoinVo>joinTables2();
	
	// join + where 條件: where 條件式要寫在最後面。
	// 若是需要 order by，一樣是接續在 where 條件式後面。
	@Query("select new com.example.practice_9.vo.JoinVo(P.id, P.name, A.balance) "
			+ " from PersonInfo as P join Atm as A "
			+ " on P.id = A.account where id = ?1")
	public List<JoinVo>joinTables3(String id);
	
	// join + limit : join 建議使用 nativeQuery = false，limit 則只能使用於 nativeQuery = true。
	// 分頁(Pageable)就是指將所有符合條件的資料，分成每一頁有幾筆資料:例如: 資料有10筆，每頁3筆，總共就會有4頁。
	// limit 就是會把符合條件的資料回傳限制在幾筆，就是回傳前幾筆資料。
	// 這樣的概念等同於把分頁中第一頁的資料回傳。
	// 定義方法時使用 Pageable，呼叫該方法時是使用 PageRequest.of(int page_index，int size)
	// page_index: 從0開始，即我們所謂的第一頁。
	// size: 每頁有幾筆資料。
	// PageRequest(0, 5): 表示要回傳前五筆資料，等同於 limit 5。
	// Pageable import 的 library: org.springframework.data.domain.Pageable;
	@Query("select new com.example.practice_9.vo.JoinVo(P.id, P.name, A.balance) "
			+ " from PersonInfo as P join Atm as A "
			+ " on P.id = A.account ")
	public List<JoinVo>joinTablesLimit(Pageable pageable);
	//==================  left join
	
	// 表1 left join 表2: 以 left join 為中心分左右兩邊的表，左邊的就是左表(表1)，右邊的就是右表(表2)。
	// left join 是以左表為主，右表為輔: 即左表的所有資料都會出現，不管在右表中資料是否有值(沒值就帶 null)。
	// 因為沒值會回 null，所以 JoinVo 中的 balance 的資料型態要改成 Integer 才能得到結果。
	@Query("select new com.example.practice_9.vo.JoinVo(P.id, P.name, A.balance) "
			+ " from PersonInfo as P left join Atm as A "
			+ " on P.id = A.account")
	public List<JoinVo>leftJoinTables();
	
	
	@Query(value = "select PI from PersonInfo as PI where id in (select A.account from Atm as A)")
	public List<PersonInfo> selectAllByIdIn();
	
	@Query(value = "select * from person_info where id in (select account from atm)", 
			nativeQuery = true)
	public List<PersonInfo> selectAllByIdIn2();
	
	// exists 後面的子查詢結果，若為 true。才會繼續執行一開始的外查詢:反之則不會執行。
	@Query(value = "select * from person_info where exists (select * from atm where account = ?1)", 
			nativeQuery = true)
	public List<PersonInfo> selectAllByIdExists(String id);
	
	// case when 條件式 then A else B end: 假設條件式成立，則是 A 不然是 B。
	// then P.name: 是指使用基本 Entity PersonInfo 中的值(即原本 DB 中的值)
	// 每一句 case when 的後面都要加上 end。
	@Transactional
	@Modifying
	@Query("update PersonInfo as P set "
			+ " P.name = case when :inputName is null then P.name else :inputName end, " //
			+ " P.age = case when :inputAge <= 0 then P.age else :inputAge end, " //
			+ " P.city = case when :inputCity is null then P.city else :inputCity end" //
			+ " where P.id = :inputId")
	public int updateUsingCaseWhen( // 
			@Param("inputId") String id, //
			@Param("inputName") String name, //
			@Param("inputAge") int age, //
			@Param("inputCity") String city); //
	
	// coalesce(參數1, 參數2,...): 依序判斷參數中的值是否為 null，會返回第一個非 null 的值，若全都 null 則回傳 null。
	// coalesce(:inputName, P.name): 會依序判斷 :inputName 是否為 null，若是，就判斷下一個參數 P.name
	// coalesce 只能判斷 null，數值的判斷還是得要用 case when。
	@Transactional
	@Modifying
	@Query("update PersonInfo as P set "
			+ " P.name = coalesce(:inputName, P.name), " //
			+ " P.age = case when :inputAge <= 0 then P.age else :inputAge end, " //
			+ " P.city = coalesce(:inputCity, P.city)" //
			+ " where P.id = :inputId")
	public int updateUsingCaseWhen2( // 合併
			@Param("inputId") String id, //
			@Param("inputName") String name, //
			@Param("inputAge") int age, //
			@Param("inputCity") String city); //
	
	
	@Query(value = "select city, avg(age) as age_avg from person_info group by city", nativeQuery = true)
	public List<Map<String, Object>> groupBy();
	
	// 要對整個 group by 之後的資料進一步篩選，要用 having: having 要使用在 group by 後面。
	// having 後面的篩選欄位，若是有使用別名，則是別名的名字。
	@Query(value = "select city, avg(age) as age_avg from person_info "
			+ " group by city having age_avg > ?1", nativeQuery = true)
	public List<Map<String, Object>> groupByHaving(int age);
}
