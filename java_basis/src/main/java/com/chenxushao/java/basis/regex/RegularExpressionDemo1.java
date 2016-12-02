package com.chenxushao.java.basis.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegularExpressionDemo1 {

	public static void main(String[] args) {
		// 什么是正则表达式？
		/*
		 * System.out.println("abc".matches("..."));
		 * System.out.println("abc".matches(".{3}"));
		 * 
		 * System.out.println("a8729a".replaceAll("\\d", "-"));//替换
		 * 
		 * Pattern p = Pattern.compile("[a-z]{3}"); Matcher m =
		 * p.matcher("adw"); System.out.println(m.matches());
		 */

		// 初步认识：.,*,+,?
		/*
		 * System.out.println("a".matches("."));
		 * System.out.println("aa".matches("a*"));
		 * System.out.println("aaa".matches("a+"));
		 * System.out.println("".matches("a*"));
		 * System.out.println("".matches("a+"));
		 * System.out.println("".matches("a?"));
		 * System.out.println("a".matches("a?"));
		 * System.out.println("4443898719648".matches("\\d{3,100}"));
		 * 
		 * System.out.println("192.168.0.2".matches(
		 * "\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}"));
		 * System.out.println("192".matches("[0-2][0-9][0-9]"));
		 */

		/*
		 * //范围 System.out.println("a".matches("[a-z]"));
		 * System.out.println("M".matches("[a-z]"));
		 * System.out.println("M".matches("[^a-z]"));
		 * System.out.println("M".matches("[a-zA-Z]"));//等价
		 * System.out.println("M".matches("[a-z]|[A-Z]"));//等价
		 * System.out.println("M".matches("[a-z[A-Z]]"));//等价
		 * System.out.println("a".matches("[abc]"));
		 * System.out.println("M".matches("[LMN]"));
		 * System.out.println("O".matches("[M-Q]"));
		 * System.out.println("a".matches("[abc]"));
		 */

		/*
		 * //认识：\s,\w,\d System.out.println(" ".matches("\\s"));//
		 * /s表示所有空格符(空白字符),等同于模式[\t\n\x0B\r]
		 * System.out.println(" \t\r\n".matches("\\s{4}"));
		 * System.out.println("a_8".matches("\\w{3}"));//
		 * /w表示单词字符，等同于模式[a-zA-Z_0-9]
		 * System.out.println("\\".matches("\\\\"));//匹配一个\
		 */

		// 边界
		/*
		 * System.out.println("hello sir".matches("^h.*"));// ^在开头表示字符开始
		 * System.out.println("hello sir".matches(".*sir$"));// &表示结尾
		 * System.out.println("hello sir".matches("^h[a-z]{1,3}o\\b.*"));//
		 * \b表示单词边界，比如空格
		 * 
		 * System.out.println(" ".matches("."));
		 */

		/*
		 * Pattern p = Pattern.compile("\\d{3,5}"); String s =
		 * "123-31333-381838-3833"; Matcher m = p.matcher(s);
		 * System.out.println(m.matches());//matches()是匹配整个字符串
		 * 
		 * m.reset();//matches()与find()相互之间有影响，注意使用reset方法
		 * 
		 * System.out.println(m.find());//find()从串中查找是否有子串匹配模式,下一次将从剩下的子串中查找
		 * System.out.println(m.find()); System.out.println(m.find());
		 * System.out.println(m.find()); System.out.println(m.find());
		 * System.out.println(m.find());
		 * 
		 * System.out.println(m.lookingAt());//每次都从头开始找
		 * System.out.println(m.lookingAt()); System.out.println(m.lookingAt());
		 * System.out.println(m.lookingAt());
		 */

		/*
		 * Pattern p = Pattern.compile("\\d{3,5}"); String s =
		 * "123-31333-381838-3833"; Matcher m = p.matcher(s); m.find();
		 * m.find(); m.find(); m.find(); System.out.println(m.group(0));
		 */

		// 替换
		// Pattern p =
		// Pattern.compile("java",Pattern.CASE_INSENSITIVE);//CASE_INSENSITIVE：对大小写不敏感
		// Matcher m =
		// p.matcher("java Java JAVa JaVa IloveJAVA you hateJava afasdfasdf");
		// System.out.println(m.replaceAll("JAVA"));//将串中所有java(不区分大小写)替换为JAVA
		// 要求将匹配的偶数个次序的java替换为小写的java,奇数次序的java替换为JAVA。
		/*
		 * int i = 0; StringBuffer buf = new StringBuffer(); while(m.find()){
		 * i++; if(i%2 == 0) m.appendReplacement(buf, "java"); else
		 * m.appendReplacement(buf, "JAVA"); } m.appendTail(buf);//添加未曾匹配的到buf末尾
		 * System.out.println(buf);
		 */

		// 分组：要求把所有数字取出来
		Pattern p = Pattern.compile("(\\d{3,5})([a-z]{2})");// 正则表达式的大括号()表示分组，有几对大括号就表示分了几组
		Matcher m = p.matcher("123aa-34345bb-234cc-00");
		while (m.find()) {
			System.out.println(m.group(1));
		}

		/*
		 * String ip = "192.168.010.2"; Pattern p =
		 * Pattern.compile("\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}"); Matcher
		 * m = p.matcher(ip);
		 * 
		 * System.out.println(m.matches()); if (!m.matches()){
		 * System.out.println("ip不合法"); return;
		 * 
		 * }
		 * 
		 * String[] arr = ip.split("\\."); int temp = -1; boolean flag = true;
		 * if(arr.length < 4) { System.out.println("ip不合法:长度太短"); return; }
		 * 
		 * 
		 * for(int i=0; i<arr.length; i++){ if(arr[i].length()>1 &&
		 * arr[i].charAt(0)=='0'){ flag = false; break; } temp =
		 * Integer.parseInt(arr[i]); if (!(temp >= 0 && temp <= 255)){ flag =
		 * false; break; } }
		 * 
		 * if (flag == true) System.out.println("ip地址合法"); else
		 * System.out.println("ip地址不合法");
		 */
	}
}
