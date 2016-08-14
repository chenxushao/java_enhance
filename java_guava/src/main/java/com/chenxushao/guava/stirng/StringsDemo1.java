package com.chenxushao.guava.stirng;

import com.google.common.base.Strings;

/**
 * Strings也是一个方便的类
 */
public class StringsDemo1 {
	public static void main(String[] args) {
		StringBuilder builder = new StringBuilder("foo");
		char c = 'x';
		for (int i = 0; i < 3; i++) {
			builder.append(c);
		}
		System.out.println(Strings.padEnd(builder.toString(), 20, 'y'));

		System.out.println(Strings.repeat("abc", 3));
		System.out.println(Strings.emptyToNull("") == null);
		System.out.println("hahhhh");

		String res = Strings.padEnd("start", 10, 'a');
		System.out.println(res);

		res = Strings.repeat("hello", 5);
		System.out.println(res);
	}
}