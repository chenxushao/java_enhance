package com.chenxushao.designpattern.adapter;

public class Adapter implements Target {

	Adaptee adaptee;

	@Override
	public void operate2() {
		// TODO Auto-generated method stub

	}

	@Override
	public void operate1() {
		adaptee.operate1();
	}

}
