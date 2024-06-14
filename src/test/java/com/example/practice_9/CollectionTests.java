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
		List<String> list = new ArrayList<>(); // 元素重複 有序 且允許null
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
		List<String> list = new ArrayList<>(); // 動態的 List
		list.add("A");
		list.add("B");
		list.add("A");
		list.add("C");
		// 固定長度的 List: 長度是根據 of 裡面有幾個元素決定，不可增刪。
		List<String> list1 = List.of("A", "B", "A", "C");
		// 固定長度的 List: 長度是根據 asList 裡面有幾個元素決定，不可增刪。
		List<String> list2 = Arrays.asList("A", "B", "A", "C");
	}

	@Test
	public void listTest2() {
		List<String> list = new ArrayList<>(); // 動態的 List
		list.add("A");
		list.add("B");
		list.add("A");
		list.add("C");
		System.out.println(list.size());
		// remove 可使用在 for loop
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
		// remove: 不可使用在 foreach
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
		list.addFirst("Q"); // 將元素新增到第一個位置。
		list.addLast("W"); // 將元素新增到最後一個位置。

		List<String> list1 = new ArrayList<>(); // 動態的 List
		list.add("A");
		list.add("B");
		list.add("C");
		list.add(0, "Q"); // 將元素新增到第一個位置。
		list.add(list.size(), "W"); // 將元素新增到最後一個位置。
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
		Collections.reverse(list); // 把 List 整個倒過來排列。
		for (String item : list) {
			System.out.println(item);
		}
	}

	@Test
	public void setTest() { // 不允許重複 且無序 允許null
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
		System.out.println("最大值: " + numbers.first());
		System.out.println("最小值: " + numbers.last());
		System.out.println("由小排到大: \n" + numbers);
		List<Integer> list = new ArrayList<>(numbers);
		Collections.reverse(list);
		System.out.println("==========================");
		System.out.println("由大排到小: \n" + list);
	}

	@Test
	public void workTest1() {
		List<Integer> list = new ArrayList<>();
		for (int i = 1; i <= 10; i++) {
			list.add(i);
		}
		// 打亂 list
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
		// Map<key> 的資料型態， value 的資料型態。
		Map<String, Integer> map = new HashMap<>();
		map.put("A", 65);
		map.put("B", 66);
		map.put("A", 70);
		// map 相同的 Key，其對應的 value 會後蓋前。
		System.out.println(map);
		// 遍歷 map 時，將遍歷的對象轉成 entrySet，可以直接取得每個 map item 的 key 和 value。
		for (Entry<String, Integer> item : map.entrySet()) {
			System.out.println("key:　" + item.getKey());
			System.out.println("value: " + item.getValue());
		}
		// 取得 key 對應的 value
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
		// 數字 index
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
