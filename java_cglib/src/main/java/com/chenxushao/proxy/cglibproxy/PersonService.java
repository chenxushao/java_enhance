package com.chenxushao.proxy.cglibproxy;

public class PersonService implements IPersonService {

	@Override
	public void add() {
		 System.out.println("add");
	}

	@Override
	public void adjust() {
		 System.out.println("adjust");
	}
}
