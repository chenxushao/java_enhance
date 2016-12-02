package com.chenxushao.java.collection.queue;

import java.util.PriorityQueue;

//PriorityQueue是基于优先堆的一个无界队列，非线程安全
public class PriorityQueueDemo1 {

	public static void main(String[] args) {

		// 被添加的元素，必须实现Comparable接口，否则报错;也可以在创建PriorityQueue时，传入Comparator的实现进去
		PriorityQueue<Person> priorityQueue = new PriorityQueue<Person>();
		Person p1 = new Person("cxs", 31);
		Person p2 = new Person("kzy", 22);
		priorityQueue.add(p1);
		priorityQueue.add(p2);

		System.out.println(priorityQueue.size());

		System.out.println(priorityQueue.poll());
		System.out.println(priorityQueue.poll());
	}

}
