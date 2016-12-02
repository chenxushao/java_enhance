package com.chenxushao.guava.collections;

import java.util.ArrayList;
import java.util.List;

import com.chenxushao.guava.Person;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

public class MultiMapDemo1 {

	public static void main(String[] args) {

		List<Person> personList = new ArrayList<Person>();

		Person p1 = new Person();
		p1.setId(1);
		p1.setName("p1");
		Person p2 = new Person();
		p2.setId(1);
		p2.setName("p2");
		Person p3 = new Person();
		p3.setId(3);
		p3.setName("p3");
		Person p4 = new Person();
		p4.setId(3);
		p4.setName("p4");

		personList.add(p1);
		personList.add(p2);
		personList.add(p3);
		personList.add(p4);

		Multimap<String, Person> multimap = ArrayListMultimap.create();

		for (Person p : personList) {
			multimap.put(String.valueOf(p.getId()), p);
		}

		System.out.println(multimap.get("1"));
	}
}
