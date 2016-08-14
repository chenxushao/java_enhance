package com.chenxushao.java.basis.string;

public class StringMethodTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		 String s = "Hello,World!";
		 System.out.println(s.length());
		 System.out.println(s.substring(4));//从4开始(包括4)后面那一段截取下来
         System.out.println(s.substring(4,8));//包头不包尾
	}

}
