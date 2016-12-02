package com.chenxushao.designpattern.singleton;

public class Client {

	public static void main(String[] args) {
		SingleIton2 iton1 = SingleIton2.getInstance();
		SingleIton2 iton2 = SingleIton2.getInstance();
		System.out.println(iton1 == iton2);
	}
}
