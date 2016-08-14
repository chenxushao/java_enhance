package com.chenxushao.designpattern.proxy.staticproxy;

public class Client {

	/** 
	 * @Title: main 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param @param args      
	 * @return void
	 * @throws 
	 */

	public static void main(String[] args) {
		 /* IUserService us = new UserService();
		  us.insert();
		  us.delete();
		  us.update();
		  us.query();*/
		 UserServiceProxy userServiceProxy = new UserServiceProxy();
		 userServiceProxy.setAspectBean(new AspectBean("cxs"));
		 userServiceProxy.setUserService(new UserService());
		 userServiceProxy.setUserName("cxs");
		  IUserService us =userServiceProxy;
		  us.insert();
		  us.delete();
		  us.update();
		  us.query();
	}

}
