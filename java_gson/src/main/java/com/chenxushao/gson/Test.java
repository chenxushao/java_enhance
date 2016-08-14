package com.chenxushao.gson;

import com.google.gson.Gson;

public class Test {

	/** 
	 * @Title: main 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param @param args      
	 * @return void
	 * @throws 
	 */

	public static void main(String[] args) {
	   Integer[] arr = new Integer[]{1,2,3};
	   Gson gson = new Gson();
	   System.out.println(gson.toJsonTree(arr));
	}

}
