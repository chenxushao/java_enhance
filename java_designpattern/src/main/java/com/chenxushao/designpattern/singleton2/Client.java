package com.chenxushao.designpattern.singleton2;

public class Client {

	public static void main(String[] args) {
		SingleIton iton1 = SingleIton.getInstance();
		SingleIton iton2 = SingleIton.getInstance();
		System.out.println(iton1 == iton2);
	}
}
