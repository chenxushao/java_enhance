package com.jfans;

import java.lang.Integer;;

public class ClassLoaderTest3 {
	
	public static void main(String[] args) throws ClassNotFoundException {
		 //伪造的Integer能通过编译，但永远无法运行，这个得益于JDK类加载器的双亲委托模型
		//这种机制保证了JDK核心类库不会被破坏
		 Integer integer = new Integer("1234");
	     System.out.println(integer + "--->" + integer.getClass().getClassLoader());
		 
	}

}
