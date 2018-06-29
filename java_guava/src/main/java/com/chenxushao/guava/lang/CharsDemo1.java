package com.chenxushao.guava.lang;

import java.util.List;

import com.google.common.primitives.Chars;

public class CharsDemo1 {
	public static void main(String args[]) {
		CharsDemo1 tester = new CharsDemo1();
		tester.testChars();
	}

	private void testChars() {
		char[] charArray = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h' };

		// convert array of primitives to array of objects
		List<Character> objectArray = Chars.asList(charArray);
		System.out.println(objectArray.toString());

		// convert array of objects to array of primitives
		charArray = Chars.toArray(objectArray);
		System.out.print("[ ");
		for (int i = 0; i < charArray.length; i++) {
			System.out.print(charArray[i] + " ");
		}
		System.out.println("]");
		// check if element is present in the cow of primitives or not
		System.out.println("c is in cow? " + Chars.contains(charArray, 'c'));

		// return the index of element
		System.out.println("c position in cow "
				+ Chars.indexOf(charArray, 'c'));

		// Returns the minimum
		System.out.println("Min: " + Chars.min(charArray));

		// Returns the maximum
		System.out.println("Max: " + Chars.max(charArray));
	}
}