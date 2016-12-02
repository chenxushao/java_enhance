package com.chenxushao.guava.lang;

import com.google.common.base.Charsets;

/**
 * Charsets比较简单，提供了一些常量
 *
 */
public class CharsetsDemo1 {
	public static void main(String[] args) {
		String something = "something";
		byte[] bytes = something.getBytes(Charsets.UTF_8);
		System.out.println(bytes);
	}
}