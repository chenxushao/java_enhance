package com.chenxushao.java.basis.annonation;

import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

public class SuppressWarningsTest {
 
	 //对编译程序说明某个方法中若有警告信息，则加以抵制。
    //压制警告，加上该Annotation，所有的警告显示都消失了
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		 //jdk5.0的正确使用方法，不会出现警告等信息
		 /* Map<String,Date> map = new TreeMap<String,Date>();
		  map.put("hello", new Date());
		  System.out.println(map.get("hello"));*/
		
		
		  //jdk1.4及之前的定义方式
		  Map map = new TreeMap();
		  map.put("hello", new Date());
		  System.out.println(map.get("hello"));
	}

}
