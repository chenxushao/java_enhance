package com.chenxushao.guava.lang;

import java.util.List;

import com.google.common.primitives.Longs;

public class LongsDemo1 {
	public static void main(String args[]) {
		LongsDemo1 tester = new LongsDemo1();
		tester.testLongs();
	}

	private void testLongs() {
		long[] longArray = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };

		// convert array of primitives to array of objects
		List<Long> objectArray = Longs.asList(longArray);
		System.out.println(objectArray.toString());

		// convert array of objects to array of primitives
		longArray = Longs.toArray(objectArray);
		System.out.print("[ ");
		for (int i = 0; i < longArray.length; i++) {
			System.out.print(longArray[i] + " ");
		}
		System.out.println("]");
		// check if element is present in the cow of primitives or not
		System.out.println("5 is in cow? " + Longs.contains(longArray, 5));

		// Returns the minimum
		System.out.println("Min: " + Longs.min(longArray));

		// Returns the maximum
		System.out.println("Max: " + Longs.max(longArray));

		// get the byte array from an integer
		byte[] byteArray = Longs.toByteArray(20000);
		for (int i = 0; i < byteArray.length; i++) {
			System.out.print(byteArray[i] + " ");
		}
	}
}

/*
 * [1, 2, 3, 4, 5, 6, 7, 8, 9] [ 1 2 3 4 5 6 7 8 9 ] 5 is in cow? true Min: 1
 * Max: 9 0 0 0 0 0 0 78 32
 */