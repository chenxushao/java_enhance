package com.chenxushao.java.basis.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

//正则表达式:匹配
public class TestRegularExpression {

	public static void main(String[] args) {
		Pattern p1 = Pattern.compile("ab*c");//匹配以a开头,以c结尾，a与c之间有0到N个b的字符串
		Pattern p2 = Pattern.compile("a[0-9]{2,}b");//a与b之间有2个或2个以上数字的字符串
		Pattern p3 = Pattern.compile("a[^0-9]{2,}b");//a与b之间有2个或2个以上非数字的字符串
		Pattern p4 = Pattern.compile("a[\\d]{2}b");//a与b之间有2个数字的字符串
		Pattern p5 = Pattern.compile("a[\\D]{2,}b");//a与b之间有2个或2个以上非数字的字符串
		Pattern p6 = Pattern.compile(".*");//匹配任意字符
		Pattern p7 = Pattern.compile("a[0-9]{2}b");
		Pattern p8 = Pattern.compile("a[0-9]{2}b");
		Pattern p9 = Pattern.compile("a[0-9]{2}b");
		Pattern p10 = Pattern.compile("a[0-9]{2}b");
		Pattern p11 = Pattern.compile("a[0-9]{2}b");
		Pattern p12 = Pattern.compile("a[0-9]{2}b");
		Pattern p13 = Pattern.compile("a[0-9]{2}b");
		
		
		Matcher m1 = p1.matcher("abbbc");
		Matcher m2 = p2.matcher("a4446b");
		Matcher m3 = p3.matcher("aeh*}b");
	    Matcher m4 = p4.matcher("a45b");
	    Matcher m5 = p5.matcher("aMeiq中国-b");
	    Matcher m6 = p6.matcher("eqeeqe83334354378211");
	    
		System.out.println(m1.matches());
		System.out.println(m2.matches());
		System.out.println(m3.matches());
		System.out.println(m4.matches());
		System.out.println(m5.matches());
		System.out.println(m6.matches());
		
		System.out.println("abc= "+"abc".matches("..."));
	}

}
