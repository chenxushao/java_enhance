package com.chenxushao.java.basis.enums;


/******************************************************************************
 * 
 * <p>
 * Description: 系统操作人枚举
 * </p>
 * Project: biz Package: cn.com.gome.train.constants File: SystemOperator.java
 * 
 * @author chenxushao@hotmail.com
 * @date 2015年9月17日 上午11:30:49
 * 
 *****************************************************************************/
public enum SystemOperator {

	TRAIN_USER("火车票用户"), 
	TRAIN_SYS_BG("火车票系统后台"), 
	TRAIN_MGR("火车票系统管理员"), 
	PUSH_RB_REFUND_JOB("推送RB退款JOB"),
	PUSH_REFUND_NOTIFY_JOB("推送供应商退款通知JOB"),
	ORDER_FINISH_JOB("订单完成JOB"),
	ORDER_TIMEOUTCANCEL_JOB("订单超时取消JOB"),
	ORDER_PUSHPAY_JOB("推送铁友支付消息JOB"),
	ORDER_WARN_JOB("订单超时未响应告警JOB"),
	RB_CALLBACK("RB回调"),
	PAY_CALLBACK("支付回调"),
	POSITIVE_SAP_INCOME_JOB("正向SAP收入JOB"),
	NEGATIVE_SAP_INCOME_JOB("逆向SAP收入JOB"),
	POSITIVE_SAP_RECEIPT_JOB("正向SAP收款JOB"),
	NEGATIVE_SAP_RECEIPT_JOB("逆向SAP收款JOB"),
	SAP_INCOME_CALLBACK("SAP收入回调"),
	REFUND_AUDITOR("退款审核员"),
	AGENT_REFUND_NOTICE("供应商退款通知"),
	AGENT_REFUND_TICKET_NOTICE("供应商退票通知"),
	AGENT_CANCEL_ORDER_NOTICE("供应商取消订单通知"),
	AGENT_LOCK_ORDER_NOTICE("供应商锁单通知"),
	AGENT_NO_TICKET_NOTICE("供应商无票通知"),
	AGENT_TICKET_NOTICE("供应商有票通知");
	
	 
	public final String desc;

	private SystemOperator(String desc) {
		this.desc = desc;
	}
}
