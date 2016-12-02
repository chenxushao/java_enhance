package com.chenxushao.java.basis.syntax;

import java.util.ArrayList;
import java.util.List;

//增强的for循环:for...each
public class ForEachLoopSample {

	public static void main(String[] args) {

		int[] array = { 1, 3, 4, 41, 12, 11, -1, -33 };
		// 旧式写法
		System.out.println("旧式写法");
		for (int i = 0; i < array.length; i++) {
			System.out.print(array[i] + " ");
		}

		System.out.println();
		System.out.println("新式写法");
		// 新式写法:增强型for循环
		for (int element : array) {
			System.out.print(element + " ");
		}

		System.out.println();
		String[] names = { "cuser", "pfei", "wneng" };
		for (String name : names) {
			System.out.print(name + " ");
		}

		System.out.println();
		// 数组本身也是对象
		int[][] array2dim = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
		for (int[] arr : array2dim) {
			for (int element : arr) {
				System.out.print(element + " ");
			}

		}

		System.out.println();
		List<String> alist = new ArrayList<String>();
		alist.add("one");
		alist.add("two");
		alist.add("three");

		for (String str : alist) {
			System.out.print(str + " ");
		}
		// for...each的语法如下：
		/*
		 * for ( type element : array or collection ){ //do something
		 * Sysstem.out.println(element); }
		 */

		// 缺点：无法确定遍历到了第几个元素

	}

}
