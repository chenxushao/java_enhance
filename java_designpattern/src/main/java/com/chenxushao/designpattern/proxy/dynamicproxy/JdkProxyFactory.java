package com.chenxushao.designpattern.proxy.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class JdkProxyFactory implements InvocationHandler {

	private Object target;

	private AspectBean aspectBean;

	private String userName;

	public void setTarget(Object target) {
		this.target = target;
	}

	public void setAspectBean(AspectBean aspectBean) {
		this.aspectBean = aspectBean;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public JdkProxyFactory(Object target) {
		super();
		this.target = target;
	}

	public Object bind() {
		return Proxy.newProxyInstance(target.getClass().getClassLoader(),
				target.getClass().getInterfaces(), this);
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		Object result = null;
		if ("query".equals(method.getName())) {
			result = method.invoke(target, args);
		} else {
			if (aspectBean.checkUser(userName)) {
				result = method.invoke(target, args);
			} else {
				System.out.println("你没有权限执行" + method.getName());
			}
		}
		return result;
	}
}