package com.chenxushao.java.basis.syntax;

import java.util.ArrayList;
import java.util.List;

public class TestConcurrentModificationException {

	public static void main(String[] args) {
		List<Object> list = new ArrayList<Object>();
		list.add(new Object());
		list.add(new Object());
		list.add(new Object());
		list.add(new Object());
		list.add(new Object());
		for (Object o : list) {
			list.remove(o);
		}
	}

}
