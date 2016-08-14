package com.chenxushao.java.basis.string;
//右对齐
public class LeftPadAString {
	  public static void main(String[] argv) {
	    System.out.println(">" + padLeft("asdf", 10) + "<");
	  }

	  public static String padLeft(String s, int n) {
	    return String.format("%1$#" + n + "s", s);
	  }
	}
	//>      asdf<
