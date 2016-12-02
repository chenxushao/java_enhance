package com.chenxushao.guava.stirng;

import com.google.common.base.CharMatcher;

public class CharMatcherDemo2 {

	public static void main(String[] args) {

		// 符合不是给定参数的字符，例如：isNot('_') 排除掉下划线以外的字符

		// anyOf : 符合在给定参数字符内的任一字符 例如：anyOf("a83h") 找到字符’a’,’8′,’3′,’h’任一个。

		// remove
		// is : 符合与给定参数相同的字符 ，例如：is('_') 判断知否有下划线
		System.out.println(CharMatcher.is('a').removeFrom("bazaar")); // "bzr"

		// retain
		System.out.println(CharMatcher.is('a').retainFrom("bazaar")); // "aaa"

		// replace
		System.out.println(CharMatcher.is('a').replaceFrom("radar", 'o')); // "rodor"

		System.out.println(CharMatcher.is('a').replaceFrom("yaha", "oo")); // "yoohoo"

		System.out.println(CharMatcher.anyOf("ab").trimFrom("abacatbab")); // "cat"

		System.out.println(CharMatcher.anyOf("eko").collapseFrom("bookkeeper",
				'-'));// "b-p-r"
	}
}
