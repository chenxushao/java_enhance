package com.chenxushao.java.basis.string;
//左对齐　
public class RightPadAString {
	  public static void main(String[] argv) {
	    System.out.println(">" + padRight("asdf", 10) + "<");
	  }

	  public static String padRight(String s, int n) {
	    return String.format("%1$-" + n + "s", s);
	  }
	}