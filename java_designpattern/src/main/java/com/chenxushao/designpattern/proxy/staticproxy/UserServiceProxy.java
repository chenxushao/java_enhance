package com.chenxushao.designpattern.proxy.staticproxy;
public class UserServiceProxy implements IUserService {

	private String userName;
	private IUserService userService;
	private AspectBean aspectBean;
	
	
	
	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	public void setAspectBean(AspectBean aspectBean) {
		this.aspectBean = aspectBean;
	}

	@Override
	public void insert() {
		if(aspectBean.checkUser(userName)){
			userService.insert();
		}else{
			System.out.println("你没有权限执行insert");
		}
		
	}

	@Override
	public void delete() {
		if(aspectBean.checkUser(userName)){
			userService.delete();
		}else{
			System.out.println("你没有权限执行delete");
		}
	}

	@Override
	public void update() {
		if(aspectBean.checkUser(userName)){
			userService.update();
		}else{
			System.out.println("你没有权限执行update");
		}
	}

	@Override
	public Object query() {
		if(aspectBean.checkUser(userName)){
			return userService.query();
		}else{
			System.out.println("你没有权限执行query");
			return null;
		}
	}
}