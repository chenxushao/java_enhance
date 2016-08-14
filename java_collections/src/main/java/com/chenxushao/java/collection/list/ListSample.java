package com.chenxushao.java.collection.list;

import java.util.ArrayList;
import java.util.List;

public class ListSample {

	public static void main(String[] args) {
		  //不用泛型的时候，什么类型都可以添加到List中，使用了泛型则不可以，在编译阶段就会报错
		 //无疑提高了安全性
		  List alist = new ArrayList();
		  alist.add("String");
		  alist.add(new Integer(1));
		  
		  //使用泛型的情况
		  List<String> strList = new ArrayList<String>();
		  strList.add("abc");
		  strList.add("def");
		  
		  for (Object str : strList.toArray()){
			 
			  System.out.println(str);
		  }

	}

}
