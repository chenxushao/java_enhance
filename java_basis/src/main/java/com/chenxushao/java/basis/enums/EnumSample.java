package com.chenxushao.java.basis.enums;

public class EnumSample {

	public static void main(String[] args) {
		// 枚举的比较
		Coin coin = Coin.valueOf(args[0]);
		for (Coin c : Coin.values()) {
			System.out.println(coin.compareTo(c));
		}
	}
}
