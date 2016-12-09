package com.chenxushao.java.juc.threadsafe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NotThreadSafe {
	List<String> list = new ArrayList<String>();
	Map<String, String> map = new HashMap<String, String>();

	public String getLast() {
		return list.get(list.size() - 1);
	}

	public void addValue(String key, String value) {
		for (Map.Entry entry : map.entrySet()) {
		}
	}
}