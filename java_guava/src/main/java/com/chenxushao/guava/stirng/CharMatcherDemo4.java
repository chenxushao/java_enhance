package com.chenxushao.guava.stirng;

import com.google.common.base.CharMatcher;

public class CharMatcherDemo4 {

	public static void main(String[] args) {

		// 换行使用空格替换
		// CharMatcher.BREAKING_WHITESPACE.replaceFrom(strinfWithlineBreaks," ");
		// 多个空字符使用' ' 替换
		String tabsAndSpaces = "String with         spaces and        tabs";
		String scrubbed = CharMatcher.WHITESPACE.collapseFrom(tabsAndSpaces,
				' ');
		System.out.println(scrubbed);

		// 去掉首尾空字符，多个空字符使用' ' 替换
		String tabsAndSpaces2 = "    String with         spaces and        tabs";
		scrubbed = CharMatcher.WHITESPACE.trimAndCollapseFrom(tabsAndSpaces2,
				' ');
		System.out.println(scrubbed);

		// 只保留数字
		String lettersAndNumbers = "foo988yxbar12323";
		String retained = CharMatcher.JAVA_DIGIT.retainFrom(lettersAndNumbers);
		System.out.println(retained);

		// 匹配多个模式
		String lettersAndOthers = "foo988xasfas   dfasd123";
		CharMatcher cm = CharMatcher.JAVA_DIGIT.or(CharMatcher.WHITESPACE);
		retained = cm.retainFrom(lettersAndOthers);
		System.out.println(retained);
	}
}