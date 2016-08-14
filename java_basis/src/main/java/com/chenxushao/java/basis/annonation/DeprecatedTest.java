package com.chenxushao.java.basis.annonation;

import java.text.DateFormat;
import java.util.Locale;

public class DeprecatedTest {
   
	 //java.lang.Deprecated也是个Maker Annotation。
	 //告诉编译程序某个方法已经不建议使用，即该方法是过时的
	 //该方法已经不建议再使用
	 @Deprecated
	 public void doSomething(){
		  System.out.println("do something.");
	 }
	 
	 public static void main(String[] args){
		 DeprecatedTest dt = new DeprecatedTest();
		 dt.doSomething();
		 
		 java.util.Date date = new java.util.Date();
		 date.toLocaleString();
		 DateFormat df = DateFormat.getDateInstance(DateFormat.LONG, Locale.CHINA);
		 System.out.println(df.format(date));
	 }
}
