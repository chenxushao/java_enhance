package com.chenxushao.guava.collections;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;
import com.google.common.collect.Table;

public class MultisetDemo1 {

	public static void main(String[] args) {
		Multiset<String> ms =  HashMultiset.create();		
	    ms.add("a");
	    ms.add("a");
	    ms.add("b");
	    ms.add("c");
	    ms.add("d");
	    ms.add("d");
	    ms.add("e");
	    
	    System.out.println(ms);
	    
		
	    System.out.println(ms.count("a"));
	    
	    //elementSet方法，返回的是Set类型
	    for(String str :ms.elementSet()){
	    	System.out.println(str+","+ms.count(str));
	    }
	}
}
