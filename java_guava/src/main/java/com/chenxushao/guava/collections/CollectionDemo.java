package com.chenxushao.guava.collections;

import java.util.List;
import java.util.Set;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.google.common.collect.Sets.SetView;

public class CollectionDemo {

	public static void main(String[] args) {
		Set<Integer> setA = Sets.newHashSet();
		setA.add(1);
		setA.add(2);
		setA.add(3);
		setA.add(4);
		setA.add(5);

		Set<Integer> setB = Sets.newHashSet();
		setB.add(4);
		setB.add(5);
		setB.add(6);
		setB.add(7);
		setB.add(8);

		// 并集
		SetView<Integer> union = Sets.union(setA, setB);
		System.out.println("union:" + union);

		// 差集
		SetView<Integer> difference = Sets.difference(setA, setB);
		System.out.println("difference:" + difference);

		// 交集
		SetView<Integer> intersection = Sets.intersection(setA, setB);
		System.out.println("intersection:" + intersection);

		List<String> pList = Lists.newArrayList();
		pList.add("a");
		pList.add("b");
		pList.add(null);
		pList.add("d");
		// Preconditions.checkArgument(person != null,"person");
		Preconditions.checkNotNull(pList);
		Preconditions.checkState(setA.size() > 0);

		// Preconditions.checkPositionIndex(index, plist.size());
		// Preconditions.checkElementIndex(index, plist.size());
	}
}
