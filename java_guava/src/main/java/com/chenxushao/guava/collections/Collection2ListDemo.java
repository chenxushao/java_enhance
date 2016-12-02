package com.chenxushao.guava.collections;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.google.common.collect.Lists;

//Collection->List示例
public class Collection2ListDemo {

	public static void main(String[] args) {
		Collection<String> coll = new ArrayList<String>();
		coll.add("a");
		coll.add("ab");
		List<String> list = Lists.newArrayList(coll);
		System.out.println(list);

	}
}
