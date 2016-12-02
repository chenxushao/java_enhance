package com.chenxushao.java.collection.iteration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/*
 * 
 * 下面给出一段多线程中安全遍历集合元素的示例。我们使用Iterator逐个扫描List中的元素，在多线程环境中，当遍历当前集合中的元素时，一般希望阻止其他线程添加或删除元素。安全遍历的实现方法如下：
 */
public class SafeCollectionIteration extends Object {
	public static void main(String[] args) {
		// 为了安全起见，仅使用同步列表的一个引用，这样可以确保控制了所有访问
		// 集合必须同步化，这里是一个List
		List<String> wordList = Collections
				.synchronizedList(new ArrayList<String>());

		// wordList中的add方法是同步方法，会获取wordList实例的对象锁
		wordList.add("Iterators");
		wordList.add("require");
		wordList.add("special");
		wordList.add("handling");

		// 获取wordList实例的对象锁，
		// 迭代时，阻塞其他线程调用add或remove等方法修改元素
		synchronized (wordList) {
			Iterator<String> iter = wordList.iterator();
			while (iter.hasNext()) {
				String s = (String) iter.next();
				System.out.println("found string: " + s + ", length="
						+ s.length());
			}
		}
	}
}
