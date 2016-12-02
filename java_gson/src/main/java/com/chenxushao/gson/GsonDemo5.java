package com.chenxushao.gson;

import com.google.gson.Gson;

public class GsonDemo5 {

	public static void main(String[] args) {
		Integer[] arr = new Integer[] { 1, 2, 3 };
		Gson gson = new Gson();
		System.out.println(gson.toJsonTree(arr));
	}
}
