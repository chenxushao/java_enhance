package com.chenxushao.java.basis.lang;

import java.util.ArrayList;
import java.util.List;

public class InstanceOfSamples {

	public static void main(String[] args) {
		List<String> list = new ArrayList<String>();

		if (list instanceof ArrayList) {
			System.out.println("Type is :" + "ArrayList");
		}
	}
}
