package com.chenxushao.proxy.cglibproxy;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class CGLibProxy implements  MethodInterceptor{


	private Object target;
	
	private AspectBean aspectBean;
	
	private String userName;
	
	
	
	
	public void setUserName(String userName) {
		this.userName = userName;
	}



	public void setTarget(Object target) {
		this.target = target;
	}



	public void setAspectBean(AspectBean aspectBean) {
		this.aspectBean = aspectBean;
	}



	@Override
	public Object intercept(Object obj, Method method, Object[] args,
			MethodProxy proxy) throws Throwable {
		Object result  = null;
		 if(aspectBean.checkUser(userName)){
		    result = proxy.invokeSuper(obj, args);
		 }else{
			 System.out.println("你没有权限执行"+method.getName());
		 }
		return result;
	}
}