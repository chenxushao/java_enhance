package com.chenxushao.guava.split;

import com.google.common.base.Splitter;

public class SplitterDemo3 {
	public static void main(String args[]) {
		SplitterDemo3 tester = new SplitterDemo3();
		tester.testSplitter();
	}

	private void testSplitter() {
		System.out
				.println(Splitter
						.on(',')
						.trimResults()
						.omitEmptyStrings()
						.split("the ,quick, , brown         , fox,              jumps, over, the, lazy, little dog."));
	}
}