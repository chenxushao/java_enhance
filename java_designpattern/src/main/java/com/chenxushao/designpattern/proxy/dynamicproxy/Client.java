package com.chenxushao.designpattern.proxy.dynamicproxy;

public class Client {

	/** 
	 * @Title: main 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param @param args      
	 * @return void
	 * @throws 
	 */

	public static void main(String[] args) {
//		  IPersonService us = new PersonService();
//		  JdkProxyFactory jpf = new JdkProxyFactory(us);
//		  jpf.setAspectBean(new AspectBean("cxs"));
//		  jpf.setUserName("cxs");
//		  IPersonService usProxy = (IPersonService) jpf.bind();
//		  usProxy.add();
//		  usProxy.adjust();
		  
		  
		  IUserService us = new UserService();
		  JdkProxyFactory jpf = new JdkProxyFactory(us);
		  jpf.setAspectBean(new AspectBean("cxs"));
		  jpf.setUserName("cxser");
		  IUserService usProxy = (IUserService) jpf.bind();
		  
		  usProxy.insert();
		  usProxy.delete();
		  usProxy.update();
		  usProxy.query();
		  
	}

}
