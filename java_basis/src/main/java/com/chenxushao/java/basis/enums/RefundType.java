package com.chenxushao.java.basis.enums;

/******************************************************************************
 * 
 * <p>Description: 退款类型</p> 
 * Project: biz
 * Package: cn.com.gome.train.statemachine
 *    File: RefundType.java
 * 
 * @author chenxushao@hotmail.com
 * @date 2015年9月7日 下午5:53:08
 * 
 *****************************************************************************/
public enum RefundType {
	 //要区分是否已出票。已出票：0，2、3  没有出票：1，4   特殊情况：5
	cetk(0, "差额退款"),
	wptk(1, "无票退款"),
	tb(2,"保险退费") ,
	hxtk(3, "退票退款"),
	qxdd(4,"取消订单"),//代表超时支付情况，订单已取消，收银台返回已支付
	cfzf(5,"重复支付");

	public final Integer type;
	public final String desc;

	private RefundType(Integer type, String desc) {
		this.type = type;
		this.desc = desc;
	}

	public static RefundType convert(Integer type) {
		if(type==null){
			return null;
		}
		RefundType[] refundTypes = RefundType.values();
		for (RefundType refundType : refundTypes) {
			if (type.equals(refundType.type)) {
				return refundType;
			}
		}
		//Default
		return null;
	}
}
