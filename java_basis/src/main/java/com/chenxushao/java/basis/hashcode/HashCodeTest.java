package com.chenxushao.java.basis.hashcode;

import java.util.HashSet;
import java.util.Set;

public class HashCodeTest {

	public static void main(String[] args) {
		Person p1 = new Person("陈绪绍", 22);
		Person p2 = new Person("陈绪绍", 22);

		System.out.println(p1 == p2);

		System.out.println(p1.equals(p2));

		System.out.println(p1.hashCode());
		System.out.println(p2.hashCode());

		Set<Person> hashSet = new HashSet<Person>();
		hashSet.add(p1);
		hashSet.add(p2);

		System.out.println(hashSet.size());
	}

}
