package com.chenxushao.java.basis.syntax;

import java.math.BigDecimal;

public class FloatTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		double f1 = 0.3;
		double f2 = 0.4;
		System.out.println(Double.toString(f1 * f2));// float在java中计算是不精确的
		BigDecimal b1 = new BigDecimal(0.3);
		BigDecimal b2 = new BigDecimal(0.4);
		System.out.println(b1.multiply(b2));

	}

}
