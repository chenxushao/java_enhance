package com.chenxushao.java.basis.annonation;

/******************************************************************************
 * 
 * <p>Description: 登录检验响应类型 </p> 
 *
 *****************************************************************************/
public enum LoginAuthResponeType {
	RedirectUrl(1,"重定向URL"),
	RenderJson(2,"渲染JSON");
	
	public final int type;
	public final String desc;

	private LoginAuthResponeType(int type,String desc) {
		this.type = type;
		this.desc = desc;
	}
}