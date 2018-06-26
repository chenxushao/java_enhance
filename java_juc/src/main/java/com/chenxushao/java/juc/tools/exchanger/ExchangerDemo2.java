package com.chenxushao.java.juc.tools.exchanger;

import java.util.concurrent.Exchanger;

public class ExchangerDemo2 {

	public static void main(String[] args) {
		Exchanger exchanger = new Exchanger();

		ExchangerRunnable exchangerRunnable1 = new ExchangerRunnable(exchanger,
				"A");

		ExchangerRunnable exchangerRunnable2 = new ExchangerRunnable(exchanger,
				"B");

		new Thread(exchangerRunnable1).start();
		new Thread(exchangerRunnable2).start();
	}
}