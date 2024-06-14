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
	// orderBy ���Ƨǹw�]�O Asc �A�ҥH�W�U��Ӥ�k�����G�|�@�ˡC
	public List<PersonInfo> findByAgeLessThanEqualOrderByAge(int age);
	
	public List<PersonInfo> findByAgeLessThanOrAgeGreaterThan(int age1 , int age2);
	
	public List<PersonInfo> findByAgeLessThanEqualAndAgeGreaterThanEqual(int age1 , int age2);
	// �W�U��Ӥ�k���G�@��
	// Between �ݩ�s��϶��A�e���u�|���@���ݩʡA����k���ѼƷ|�����(���]�t)�C
	public List<PersonInfo> findByAgeBetween(int age1 , int age2);

	public List<PersonInfo> findByAgeBetweenOrderByAgeDesc(int age1 , int age2);
	
	public List<PersonInfo> findTop3ByAgeBetweenOrderByAgeDesc(int age1 , int age2);
	// ������^����: �ϥ� top�Ʀr �� first�Ʀr : �g�b find �M By ���� 
	public List<PersonInfo> findFirst3ByAgeBetweenOrderByAgeDesc(int age1 , int age2);
	// �ϥ� Containing�A�ѼƤ��� Keyword �����j�p�g�A��d�ߪ����G�|�@��
	public List<PersonInfo> findByCityContaining(String KeyWord);
	
	public List<PersonInfo> findByAgeGreaterThanAndCityContainingOrderByAgeDesc(int age , String KeyWord);
	
	public List<PersonInfo> findByCityIn(List<String> cityList);
	
	public List<PersonInfo> findByIdIn(List<String> idlist);
	
	//==================================================================
	
	// JPQL�AnativeQuery �w�]�O false�C
	// nativeQuery = true �ɡA�y�k������M���W�٬O�ϥ� ��Ʈw����ƪ�(@Table �᭱���W�r)�M���(@Column �����W�r)���W�١C
	// insert �u��ϥΩ� nativeQuery = true�C // limit �]�O
	// ��k���^�ǫ��A�� int ���A�i�ΨӪ�ܷs�W���\��ƪ����ơA0�N��S���s�W�����ơC
	// values()���� ?�Ʀr�A�����O��k insert()���Ѽƪ���m�C 
	// JPQL: insert/update/delete �n�D�n�[�W @Transactional �M @Modifying�C
	// @Transactional import �� library�A javax �M springframework ���i�H�ϥΡC
	//   ��̮t���i�ѷ� PPT spring boot_02 @Transactional �����C
	@Transactional
	@Modifying
	@Query(value = "insert into person_info (id, name, age, city) values (?1, ?2, ?3, ?4)",
			nativeQuery = true)         
	public int insert(String inputId, String inputName, int intputAge, String inputCity );
	
	@Transactional
	@Modifying
	@Query(value = "insert into person_info (id, name, age, city)" // �W
			+ " values (:inputId, :inputName, :intputAge, :inputCity)",
			nativeQuery = true)         
	public int insert2(
			@Param("inputId") String id, //
			@Param("inputName") String name, //
			@Param("intputAge") int age, //
			@Param("inputCity") String city ); //
	
	// insert info if not exists
	// ��ĳ�ϥ� JPA �� save
	@Transactional
	@Modifying
	@Query(value = "insert into person_info (id, name, age, city)" // �W
			+ " select :inputId, :inputName, :intputAge, :inputCity " 
			+ " where not exists (select 1 from person_info where id =:inputId)", 
			nativeQuery = true)         
	public int insert3(
			@Param("inputId") String id, //
			@Param("inputName") String name, //
			@Param("intputAge") int age, //
			@Param("inputCity") String city ); //
	
	//==================  update
	// �^�ǫ��A�O int ��ܸ�Ƨ�s���\���ơA0��ܨS�������Ƨ�s���\�C
	// �O�o�n�[ where ���󦡡A�S�[����|��s������ơC
	@Transactional
	@Modifying
	@Query(value = "update person_info set age = ?2, city = ?3 "
			+ " where id = ?1 ", nativeQuery = true)
	public int update(String id, int age, String city);
	
	// nativeQuery = false(�w�]) �ɡA�y�k�����W�٬O Entity �����O�W�١A���W�٫h�O�ݩ��ܼƪ��W�١C
	@Transactional
	@Modifying            //entity�W��          //�ݩ��ܼƦW��
	@Query(value = "update PersonInfo set age = ?2, city = ?3 "
			+ " where id = ?1 ")
	public int update2(String id, int age, String city);
	
	//================== delete
	@Transactional
	@Modifying
	@Query("delete from PersonInfo where id = ?1 ")
	public int delete(String id);
	
	//================== select
	// �y�k���� * ��ܩҦ����A�n�ϥ� nativeQuery = true �C
	@Query(value = "select * from person_info", nativeQuery = true )
	public List<PersonInfo> selectAll();
	
	// nativeQuery = true�A�u�༴���Ҧ����C
	@Query(value = "select id, name, age, city from person_info", nativeQuery = true)
	public List<PersonInfo> selectAll1();
	
	// nativeQuery = false�A�n�����������쥲���n�� �غc��k ���g�k: new ���O�W��(�ݩʦW��)
	// new PersonInfo(id, name, age, city): �o�ӫغc��k�@�w�n�s�b�C
	// �N�O�ݻy�k���|�Ψ켴�X�ӰѼơA��������غc��k�N�o�n�s�b�C
	@Query(value = "select new PersonInfo( id, name, age, city ) from PersonInfo")
	public List<PersonInfo> selectAll2();
	
	// �Ʀr�j�p�i�����Τ���Ÿ�(>, <, =)�C
	@Query(value = "select * from person_info where age >= ?1", nativeQuery = true )
	public List<PersonInfo> selectAllByAge(int age);
	
	// !������i�H��1.!=  2.<> �Ӫ�ܡC
	@Query(value = "select * from person_info where age <> ?1", nativeQuery = true )
	public List<PersonInfo> selectAllByAge2(int age);
	
	// �u�ݭn���X�����ƪ� city ��ơA�ϥ� new PersonInfo(city)�A�ҥH Entity PersonInfo ���]�n���������غc��k�C
	@Query("select distinct new PersonInfo(city) from PersonInfo")
	public List<PersonInfo> selectDistinctCity();
	
	// �O�W(Alias): �b���Ϊ�W�٪��᭱�[�W as �O�W(�W�٦۩w�q)�Aas �i�ٲ��C
	// �O�W�ϥΦb Entity �W�٫᭱��ܾ�� Entity�A�ҥH select �O�W �i�H��@�����@���(�Ҧ����) Entity�C
//	@Query(value = "select PI from PersonInfo  PI where age > ?1"); �ٲ� as�C
	@Query(value = "select PI from PersonInfo as PI where age > ?1")
	public List<PersonInfo> selectAllByAge3(int age);
	
	//==================  Order
	
	// order by ���: �w�]�O ASC�A�i���g�C
	// order by ���O���N�z�粒������ƦA�ھګ��w�����ƧǡA�ҥH�ƧǪ���줣�|�O��k���ѼơC
	@Query(value = "select PI from PersonInfo as PI where age > ?1 order by city desc")
	public List<PersonInfo> selectAllByAgeOrderByCity(int age); // �Ƨ�
	
	// order by city desc, name asc: �b city ���ۦP�Ȫ����ΤU�A�~�|�Ψ� name �h�Ƨ�(asc �O�w�]�A�i�ٲ�)
	// �Y�u�����w�@�����ƧǡA���ۦP�Ȫ����ΤU�A�w�]�|�ϥ� PK ����� + ASC �ƧǡC
	@Query(value = "select PI from PersonInfo as PI where age > ?1 order by city desc, name asc")
	public List<PersonInfo> selectAllByAgeOrderByCityAndName(int age); // �Ƨ�
	
	//==================  Limit
	
	// limit: �u��ϥΦb nativeQuery = true�C
	@Query(value = "select * from person_info where age > ?1 limit ?2", nativeQuery = true)
	public List<PersonInfo> selectAllByAgeLimit(int age, int limit);
	
	// limit: �Ʀr1, �Ʀr2: �Ʀr1��ܰ_�l���ަ�m�A�Ʀr2��ܦ^�Ǫ�����ơC
	// limit: �Ʀr1: ��ܯ��ަ�m�q0�}�l�A���P�� limit 0, �Ʀr1�C
	@Query(value = "select * from person_info where age > ?1 limit ?2, ?3", nativeQuery = true)
	public List<PersonInfo> selectAllByAgeLimit2(int age, int startIndex, int limit);
	
	// order by + limit
	@Query(value = "select * from person_info where age > ?1 order by city limit ?2", nativeQuery = true)
	public List<PersonInfo> selectAllByAgeOrderByCityLimit(int age, int limit);
	
	//==================  Between
	
	// between �Ѽ�1 and �Ѽ�2:
	// 1. ��ӰѼƶ��n�� and �s���C
	// 2. �y�k���޿�O �j�󵥩�(>=) �Ѽ�1 and �p�󵥩�(<=) �Ѽ�2
	@Query(value = "select * from person_info where age between ?1 and ?2 ", nativeQuery = true)
	public List<PersonInfo> selectAllByAgeBetween(int age1, int age2);
	
	@Query(value = "select PI from PersonInfo as PI where age between ?1 and ?2 ")
	public List<PersonInfo> selectAllByAgeBetween2(int age1, int age2);
	
	//==================  In And Not In
	
	// in �O�ݩ󤣳s�򪺰϶�
	// JPQL ���g�k�����\�ʺA�ѼơA�L�u��: �]����ĳ�� JPA �� in�C
	// JPA in ���g�k: findByCityIn(List<String> citylist);
	@Query(value = "select PI from PersonInfo as PI where city in (?1, ?2, ?3)")
	public List<PersonInfo> selectAllByCityIn(String city1, String city2, String city3);
	
	//==================  Like And Not Like
	
	// like �n�f�t % �~�|�㦳�ҽk��諸�ĪG:% �i���� 0~���N�r���C
	// ���f�t %�A�O�������C
	// �j�M������r���Ϥ��j�p�g�C
	// %�_: �H�_�����A�e���O %�A�ҥH�i�H�O 0~�h�Ӧr��: �_�B�s�_�B�x�_���ŦX�C
	// �_%: �H�_��}�Y�A�᭱�� % �i�H�O 0~�h�Ӧr��
	// %�_%: �u�n���_�����ŦX�C
//	@Query(value = "select PI from PersonInfo as PI where city like %?1% ")
	@Query(value = "select * from person_info where city like %?1% ", nativeQuery = true)
	public List<PersonInfo> selectAllByCityLike(String city);
	
	// regexp ����r ��ĪG���P�� Like %����r%�C
	// ����r�e�ᤣ��[ %�A�[�F�|�����C
	// �u��Ω� nativeQuery = true�A
	@Query(value = "select * from person_info where city Regexp ?1 ", nativeQuery = true)
	public List<PersonInfo> selectAllByCityRegexp(String city);
	
	// �]���޿�B��Ÿ�(| �� &)���ݩ�쥻 SQL �y�k���{�w���r�šA�ҥH�n�� concat �N���P�ѼƦ걵�C
	// concat �O�N��k�����Ҧ��ѼƤ��걵���r��A�ҥH�޿�Ÿ��O�o�n�[�W��޸��C
	@Query(value = "select * from person_info where city Regexp concat (?1, '|', ?2) ", nativeQuery = true)
	public List<PersonInfo> selectAllByCityRegexp2(String city1, String city2);
	
	//==================  Join
	
	// �]�����������O�󤣦P����A�S�������� Entity �i�H�N��� mapping �^�ӡC
	// �ҥH�ϥ� Map<String, Object> ���: Key �O���W�١Avalue �O���������ȡC
	// �]�����^�Ӫ���ƥi��|���h���A�� List ���C
	// �n�ѪR�^�Ǫ����G�A�N�@�w�ϥ����h for �j��AList �@�h�AMap �@�h�C
	// join ����ĳ�ϥΦb nativeQuery = true
	@Query(value ="select p.id, p.name, a.balance from person_info as p join atm as a "
			+ " on p.id = a.account", nativeQuery = true)
	public List<Map<String, Object>> joinTables();
	
	// �]�� JoinVo �S���Q spring boot �U�ޡA�ҥH�e���ݭn�[�W������|�C
	@Query("select new com.example.practice_9.vo.JoinVo(P.id, P.name, A.balance) "
			+ " from PersonInfo as P join Atm as A "
			+ " on P.id = A.account")
	public List<JoinVo>joinTables2();
	
	// join + where ����: where ���󦡭n�g�b�̫᭱�C
	// �Y�O�ݭn order by�A�@�ˬO����b where ���󦡫᭱�C
	@Query("select new com.example.practice_9.vo.JoinVo(P.id, P.name, A.balance) "
			+ " from PersonInfo as P join Atm as A "
			+ " on P.id = A.account where id = ?1")
	public List<JoinVo>joinTables3(String id);
	
	// join + limit : join ��ĳ�ϥ� nativeQuery = false�Alimit �h�u��ϥΩ� nativeQuery = true�C
	// ����(Pageable)�N�O���N�Ҧ��ŦX���󪺸�ơA�����C�@�����X�����:�Ҧp: ��Ʀ�10���A�C��3���A�`�@�N�|��4���C
	// limit �N�O�|��ŦX���󪺸�Ʀ^�ǭ���b�X���A�N�O�^�ǫe�X����ơC
	// �o�˪��������P���������Ĥ@������Ʀ^�ǡC
	// �w�q��k�ɨϥ� Pageable�A�I�s�Ӥ�k�ɬO�ϥ� PageRequest.of(int page_index�Aint size)
	// page_index: �q0�}�l�A�Y�ڭ̩ҿת��Ĥ@���C
	// size: �C�����X����ơC
	// PageRequest(0, 5): ��ܭn�^�ǫe������ơA���P�� limit 5�C
	// Pageable import �� library: org.springframework.data.domain.Pageable;
	@Query("select new com.example.practice_9.vo.JoinVo(P.id, P.name, A.balance) "
			+ " from PersonInfo as P join Atm as A "
			+ " on P.id = A.account ")
	public List<JoinVo>joinTablesLimit(Pageable pageable);
	//==================  left join
	
	// ��1 left join ��2: �H left join �����ߤ����k���䪺��A���䪺�N�O����(��1)�A�k�䪺�N�O�k��(��2)�C
	// left join �O�H�����D�A�k����: �Y�����Ҧ���Ƴ��|�X�{�A���ަb�k����ƬO�_����(�S�ȴN�a null)�C
	// �]���S�ȷ|�^ null�A�ҥH JoinVo ���� balance ����ƫ��A�n�令 Integer �~��o�쵲�G�C
	@Query("select new com.example.practice_9.vo.JoinVo(P.id, P.name, A.balance) "
			+ " from PersonInfo as P left join Atm as A "
			+ " on P.id = A.account")
	public List<JoinVo>leftJoinTables();
	
	
	@Query(value = "select PI from PersonInfo as PI where id in (select A.account from Atm as A)")
	public List<PersonInfo> selectAllByIdIn();
	
	@Query(value = "select * from person_info where id in (select account from atm)", 
			nativeQuery = true)
	public List<PersonInfo> selectAllByIdIn2();
	
	// exists �᭱���l�d�ߵ��G�A�Y�� true�C�~�|�~�����@�}�l���~�d��:�Ϥ��h���|����C
	@Query(value = "select * from person_info where exists (select * from atm where account = ?1)", 
			nativeQuery = true)
	public List<PersonInfo> selectAllByIdExists(String id);
	
	// case when ���� then A else B end: ���]���󦡦��ߡA�h�O A ���M�O B�C
	// then P.name: �O���ϥΰ� Entity PersonInfo ������(�Y�쥻 DB ������)
	// �C�@�y case when ���᭱���n�[�W end�C
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
	
	// coalesce(�Ѽ�1, �Ѽ�2,...): �̧ǧP�_�ѼƤ����ȬO�_�� null�A�|��^�Ĥ@�ӫD null ���ȡA�Y���� null �h�^�� null�C
	// coalesce(:inputName, P.name): �|�̧ǧP�_ :inputName �O�_�� null�A�Y�O�A�N�P�_�U�@�ӰѼ� P.name
	// coalesce �u��P�_ null�A�ƭȪ��P�_�٬O�o�n�� case when�C
	@Transactional
	@Modifying
	@Query("update PersonInfo as P set "
			+ " P.name = coalesce(:inputName, P.name), " //
			+ " P.age = case when :inputAge <= 0 then P.age else :inputAge end, " //
			+ " P.city = coalesce(:inputCity, P.city)" //
			+ " where P.id = :inputId")
	public int updateUsingCaseWhen2( // �X��
			@Param("inputId") String id, //
			@Param("inputName") String name, //
			@Param("inputAge") int age, //
			@Param("inputCity") String city); //
	
	
	@Query(value = "select city, avg(age) as age_avg from person_info group by city", nativeQuery = true)
	public List<Map<String, Object>> groupBy();
	
	// �n���� group by ���᪺��ƶi�@�B�z��A�n�� having: having �n�ϥΦb group by �᭱�C
	// having �᭱���z�����A�Y�O���ϥΧO�W�A�h�O�O�W���W�r�C
	@Query(value = "select city, avg(age) as age_avg from person_info "
			+ " group by city having age_avg > ?1", nativeQuery = true)
	public List<Map<String, Object>> groupByHaving(int age);
}
