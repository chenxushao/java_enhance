package com.chenxushao.guava.lang;

import java.util.List;

import com.google.common.primitives.Booleans;

public class BooleansDemo1 {
	public static void main(String args[]) {
		BooleansDemo1 tester = new BooleansDemo1();
		tester.testBooleans();
	}

	private void testBooleans() {
		boolean[] booleanArray = { true, true, false, true, true, false, false };

		// convert array of primitives to array of objects
		List<Boolean> objectArray = Booleans.asList(booleanArray);
		System.out.println(objectArray.toString());

		// convert array of objects to array of primitives
		booleanArray = Booleans.toArray(objectArray);
		System.out.print("[ ");
		for (int i = 0; i < booleanArray.length; i++) {
			System.out.print(booleanArray[i] + " ");
		}
		System.out.println("]");
		// check if element is present in the cow of primitives or not
		System.out.println("true is in cow? "
				+ Booleans.contains(booleanArray, true));

		// return the first index of element
		System.out.println("true position in cow "
				+ Booleans.indexOf(booleanArray, true));

		// Returns the count of true values
		System.out.println("true occured: " + Booleans.countTrue());

		// Returns the comparisons
		System.out.println("false Vs true: " + Booleans.compare(false, true));
		System.out.println("false Vs false: " + Booleans.compare(false, false));
		System.out.println("true Vs false: " + Booleans.compare(true, false));
		System.out.println("true Vs true: " + Booleans.compare(true, true));
	}
}