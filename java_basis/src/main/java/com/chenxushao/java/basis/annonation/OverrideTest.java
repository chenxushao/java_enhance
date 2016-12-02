package com.chenxushao.java.basis.annonation;

public class OverrideTest {

	// java.lang.Override是个Maker Annotation(标识Annotation)。
	// Java内置的Annotation(强制保证覆盖)
	@Override
	public String toString() {
		return "OverrideTest";
	}

	public static void main(String[] args) {
		OverrideTest ot = new OverrideTest();
		System.out.println(ot);
	}

}
