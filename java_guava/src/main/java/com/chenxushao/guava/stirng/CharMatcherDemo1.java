package com.chenxushao.guava.stirng;

import com.google.common.base.CharMatcher;

public class CharMatcherDemo1 {

	public static void main(String[] args) {

		CharMatcher cm = CharMatcher.anyOf("gZ");
		// 是否sequence中有匹配的字符
		System.out.println(cm.matchesAnyOf("anything"));

		String str = "FirstName LastName +1 123 456 789 !@#$%^&*()_+|}{:\"?><";
		// Use a predefined constant (predefine CharMatcher)
		String digit = CharMatcher.DIGIT.retainFrom(str);
		System.out.println(digit);

		String javaLetter = CharMatcher.JAVA_LETTER.retainFrom(str);
		System.out.println(javaLetter);

		String javaLetterOrDigit = CharMatcher.JAVA_LETTER_OR_DIGIT
				.retainFrom(str);
		System.out.println(javaLetterOrDigit);

		int stringCount = CharMatcher.ANY.countIn(str);
		System.out.println(stringCount);

		int digitCount = CharMatcher.DIGIT.countIn(str);
		System.out.println(digitCount);

		// 保留a、l、e、x这些字符
		String keepAlex = CharMatcher.anyOf("alex").retainFrom("adlseaxdada");
		System.out.println(keepAlex);

		// removeFrom 去掉字符串中的数字
		String removeFrom = CharMatcher.DIGIT
				.removeFrom("some text 89983 and more");
		System.out.println(removeFrom);
	}
}
