package com.chenxushao.proxy.cglibproxy;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Callback;
import net.sf.cglib.proxy.CallbackFilter;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.NoOp;

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
		  
		  
//		  IUserService us = new UserService();
//		  JdkProxyFactory jpf = new JdkProxyFactory(us);
//		  jpf.setAspectBean(new AspectBean("cxs"));
//		  jpf.setUserName("cxser");
//		  IUserService usProxy = (IUserService) jpf.bind();
		  
		 
		  CGLibProxy cgLibProxy = new CGLibProxy();
		  cgLibProxy.setAspectBean(new AspectBean("cxs"));
		  cgLibProxy.setUserName("cxser");
		  Enhancer enhancer = new Enhancer();
		  enhancer.setSuperclass(UserService.class);
		  enhancer.setCallbacks(new Callback[]{cgLibProxy,NoOp.INSTANCE});
		  enhancer.setCallbackFilter(new CallbackFilter() {
			@Override
			public int accept(Method method) {
				if(!"query".equals(method.getName())){
				  return 0;
				}else{
					return 1;
				}
			}
		});
		  
		  UserService proxy =  (UserService) enhancer.create();
		  proxy.insert();
		  proxy.delete();
		  proxy.update();
		  proxy.query();
	}

}
