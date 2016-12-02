package com.chenxushao.designpattern.proxy.staticproxy;

public class AspectBean {

	private String userName;

	public AspectBean(String userName) {
		this.userName = userName;
	}

	public boolean checkUser(String userName) {
		if (this.userName == null || this.userName.equals("")) {
			return false;
		}
		return this.userName.equals(userName);
	}
}
