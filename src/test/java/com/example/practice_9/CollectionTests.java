package com.example.practice_9;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

import org.junit.jupiter.api.Test;

public class CollectionTests {

	@Test
	public void listTest() {
		List<String> list = new ArrayList<>(); // �������� ���� �B���\null
		list.add("A");
		list.add("B");
		list.add("A");
		list.add("C");
		list.add("null");
		list.add(null);
		for (String item : list) {
			System.out.println(item);
		}
	}

	@Test
	public void listTest1() {
		List<String> list = new ArrayList<>(); // �ʺA�� List
		list.add("A");
		list.add("B");
		list.add("A");
		list.add("C");
		// �T�w���ת� List: ���׬O�ھ� of �̭����X�Ӥ����M�w�A���i�W�R�C
		List<String> list1 = List.of("A", "B", "A", "C");
		// �T�w���ת� List: ���׬O�ھ� asList �̭����X�Ӥ����M�w�A���i�W�R�C
		List<String> list2 = Arrays.asList("A", "B", "A", "C");
	}

	@Test
	public void listTest2() {
		List<String> list = new ArrayList<>(); // �ʺA�� List
		list.add("A");
		list.add("B");
		list.add("A");
		list.add("C");
		System.out.println(list.size());
		// remove �i�ϥΦb for loop
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
			list.remove(i);
		}
		System.out.println(list.size());
		System.out.println("====================");
		list.add("A");
		list.add("B");
		list.add("A");
		list.add("C");
		System.out.println(list.size());
		// remove: ���i�ϥΦb foreach
		for (String item : list) {
			System.out.println(item);
			list.remove(item);
		}

	}

	@Test
	public void listTest3() {
		LinkedList<String> list = new LinkedList<>();
		list.add("A");
		list.add("B");
		list.add("C");
		list.addFirst("Q"); // �N�����s�W��Ĥ@�Ӧ�m�C
		list.addLast("W"); // �N�����s�W��̫�@�Ӧ�m�C

		List<String> list1 = new ArrayList<>(); // �ʺA�� List
		list.add("A");
		list.add("B");
		list.add("C");
		list.add(0, "Q"); // �N�����s�W��Ĥ@�Ӧ�m�C
		list.add(list.size(), "W"); // �N�����s�W��̫�@�Ӧ�m�C
	}

	@Test
	public void listTest4() {
		LinkedList<String> list = new LinkedList<>();
		list.add("A");
		list.add("B");
		list.add("C");
		for (String item : list) {
			System.out.println(item);
		}
		System.out.println("=======================");
		Iterator<String> listIter = list.iterator();
		while (listIter.hasNext()) {
			System.out.println(listIter.next());
		}
		System.out.println("=======================");
		Collections.reverse(list); // �� List ��ӭ˹L�ӱƦC�C
		for (String item : list) {
			System.out.println(item);
		}
	}

	@Test
	public void setTest() { // �����\���� �B�L�� ���\null
		Set<String> set = new HashSet<>();
		set.add("A");
		set.add("B");
		set.add("A");
		set.add("C");
		for (String item : set) {
			System.out.println(item);
		}
	}

	@Test
	public void workTest() {
		TreeSet<Integer> numbers = new TreeSet<>();
		Random ran = new Random();
		while (numbers.size() < 5) {
			int num = ran.nextInt(10) + 1;
			numbers.add(num);

		}
		System.out.println("�̤j��: " + numbers.first());
		System.out.println("�̤p��: " + numbers.last());
		System.out.println("�Ѥp�ƨ�j: \n" + numbers);
		List<Integer> list = new ArrayList<>(numbers);
		Collections.reverse(list);
		System.out.println("==========================");
		System.out.println("�Ѥj�ƨ�p: \n" + list);
	}

	@Test
	public void workTest1() {
		List<Integer> list = new ArrayList<>();
		for (int i = 1; i <= 10; i++) {
			list.add(i);
		}
		// ���� list
		Collections.shuffle(list);
		list = list.subList(0, 5);
		TreeSet<Integer> set = new TreeSet<>(list);
		System.out.println("min: " + set.first());
		System.out.println("max: " + set.last());
		System.out.println(set);
		list = new ArrayList(set);
		Collections.reverse(list);
		System.out.println(list);
	}

	@Test
	public void mapTest() {
		// Map<key> ����ƫ��A�A value ����ƫ��A�C
		Map<String, Integer> map = new HashMap<>();
		map.put("A", 65);
		map.put("B", 66);
		map.put("A", 70);
		// map �ۦP�� Key�A������� value �|��\�e�C
		System.out.println(map);
		// �M�� map �ɡA�N�M������H�ন entrySet�A�i�H�������o�C�� map item �� key �M value�C
		for (Entry<String, Integer> item : map.entrySet()) {
			System.out.println("key:�@" + item.getKey());
			System.out.println("value: " + item.getValue());
		}
		// ���o key ������ value
		Integer value = map.get("A");
		System.out.println(value);
		value = map.get("a");
		System.out.println(value);
	}

	@Test
	public void twoSumTest() {
		List<Integer> list = List.of(2, 7, 11, 15);
		int target = 9;
		List<Integer> indexList = new ArrayList<>();
		// �Ʀr index
		Map<Integer, Integer> map = new HashMap<>();

		for (int i = 0; i < list.size(); i++) {
			int num = target - list.get(i);
			if (map.containsKey(num)) { //
				indexList.add(map.get(num));
				indexList.add(i);
				break;
			}
			map.put(list.get(i), i);
		}
		System.out.println(indexList);
	}

}
