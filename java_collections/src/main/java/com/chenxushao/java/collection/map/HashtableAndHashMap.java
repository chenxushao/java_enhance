package com.chenxushao.java.collection.map;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

public class HashtableAndHashMap {

	public static void main(String[] args) {
		Hashtable h = new Hashtable();
		// h.put("key", null);
		h.put(null, "value");
		Map map = new HashMap();
		map.put(null, "x");
		map.put(null, null);

	}

}
