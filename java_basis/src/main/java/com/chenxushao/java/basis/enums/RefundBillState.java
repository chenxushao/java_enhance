package com.chenxushao.java.basis.enums;

/******************************************************************************
 * 
 * <p>
 * Description: 退款单状态
 * </p>
 * Project: biz Package: cn.com.gome.train.statemachine File: RefundType.java
 * 
 * @author chenxushao@hotmail.com
 * @date 2015年9月7日 下午5:53:08
 * 
 *****************************************************************************/
public enum RefundBillState {

	NEW(1, "新建"), FINANCIAL_AUDITED(2, "财务审核通过"), FINANCIAL_AUDIT_NOT_THROUGH(
			3, "财务审核不通过"), RB_PUSHED(4, "已推RB"), RB_AUDITED(5, "RB审核通过"), RB_AUDIT_NOT_THROUGH(
			6, "RB审核不通过");

	public final Integer VALUE;
	public final String NAME;

	private RefundBillState(Integer value, String name) {
		this.VALUE = value;
		this.NAME = name;
	}

	public static RefundBillState convert(Integer value) {
		if (value == null) {
			return null;
		}
		RefundBillState[] refundTypes = RefundBillState.values();
		for (RefundBillState refundType : refundTypes) {
			if (value.equals(refundType.VALUE)) {
				return refundType;
			}
		}
		// Default
		return null;
	}

	public static String prettyState(Integer value) {
		RefundBillState state = convert(value);
		if (state == null) {
			return "Unknown";
		}

		if (value == RB_AUDITED.VALUE) {
			return "已退款";
		}

		return "退款中";
	}
}
