package com.chenxushao.java.basis.enums;

import java.lang.reflect.Field;

public enum Coin {
	penny(1), nickel(5), dime(10), quarter(25);

	private int value;

	public int getValue() {
		return this.value;
	}

	Coin(int value) {
		this.value = value;
	}

	public static void main(String[] args) {
		Coin coin = Coin.nickel;
		System.out.println(coin.getValue());
		System.out.println("父类：" + Coin.class.getSuperclass());

		System.out.println("----所有字段-----");
		// System.out.println(Coin.class.getFields());
		for (Field field : Coin.class.getFields()) {
			System.out.println(field);
		}
	}
}
