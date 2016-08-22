package com.chenxushao.guava.collections;

import java.util.List;

import com.google.common.collect.Lists;
//List分组示例
public class ListPartitionDemo {

	
	public static void main(String[] args) {
		
		
		//list分组，比如将一个100个元素的list，分成每20个一组。
		List<String> list = Lists.newArrayList();
		
		for(int i=0;i<15; i++){
			list.add(String.valueOf(i));
		}
		List<List<String>> subLists = Lists.partition(list, 2);
		
		for(List<String> subList:subLists){
			System.out.println(subList);
		}
	}
}
