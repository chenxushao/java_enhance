package com.chenxushao.java.collection.map;

import java.util.WeakHashMap;

/*
 * java集合中有一种特殊的Map类型(WeakHashMap)，在这种Map中存放了键对象的弱引用，当一个键对象被垃圾回收器
 回收时，那么相应的值对象的引用也会从Map中删除。WeakHashMap能够节约存储空间，可用来缓存那些非必须存在的数据。
 */
public class TestWeakHashMap {

	public static void main(String[] args) {
		WeakHashMap<Key, Value> weakMap = new WeakHashMap<Key, Value>();

		for (int i = 0; i < 10; i++) {
			Key key = new Key("Key" + String.valueOf(i));
			Value value = new Value("Value" + String.valueOf(i));
			weakMap.put(key, value);
		}

		System.gc();

		// 把cpu让给垃圾回收器线程
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

class Key {
	String id;

	public Key(String id) {
		this.id = id;
	}

	@Override
	public boolean equals(Object obj) {
		return (obj instanceof Key) && id.equals(((Key) obj).id);
	}

	@Override
	protected void finalize() throws Throwable {
		System.out.println("finalizing key" + id);
	}

	@Override
	public int hashCode() {
		return id.hashCode();
	}

	@Override
	public String toString() {
		return this.id;
	}
}

class Value {
	String id;

	public Value(String id) {
		this.id = id;
	}

	@Override
	public boolean equals(Object obj) {
		return (obj instanceof Key) && id.equals(((Key) obj).id);
	}

	@Override
	protected void finalize() throws Throwable {
		System.out.println("finalizing value " + id);
	}

	@Override
	public int hashCode() {
		return id.hashCode();
	}

	@Override
	public String toString() {
		return this.id;
	}
}
