package com.chenxushao.java.juc.callable;

import java.util.ArrayList;
import java.util.List;

/**
 * @author CHENXUSHAO
 *  单线程求和
 */
public class SingleThreadSum {
	
	    public static void main(String[] args) {
	    	List<Integer> numbers =  new ArrayList<Integer>();
	    	for(int i=1;i<=20000;i++){
	    		numbers.add(Integer.valueOf(i));
	    	}
	    	System.out.println(numbers.stream().reduce((sum, number) -> sum+number).get());
		}
}
