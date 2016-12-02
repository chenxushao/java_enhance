package com.chenxushao.proxy.cglibproxy;

public class UserService implements IUserService {

	@Override
	public void insert() {
		System.out.println("insert");
	}

	@Override
	public void delete() {
		System.out.println("delete");
	}

	@Override
	public void update() {
		System.out.println("update");
	}

	@Override
	public Object query() {
		System.out.println("query");
		return new Object();
	}

}
