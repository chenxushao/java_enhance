package com.chenxushao.java.basis.enums;

import java.util.Arrays;
import java.util.List;

public final class BizConstants {

	public static final String TIELYOU_WEBSERVICE_URLKEY = "tieyou_ws_addr";
	public static final String TIELYOU_SEARCH_URLKEY = "tieyou_search_addr";
	
	public static final String TIEYOU = "TIEYOU";
	
	/**
	 * 调用接口的签名错误
	 */
	public static final String TIELYOU_WEBSERVICE_AUTHENTICATION_ERROR = "authentication_error";

	public final static Integer MAX_SYSTEM_OPERATOR_NAME_SIZE = 30;

	/**
	 * 铁友回调接口处理失败
	 */
	public static final String TIELYOU_WEBSERVICE_PROCESS_FAIL = "process_fail";
	/**
	 * 铁友回调接口处理成功
	 */
	public static final String TIELYOU_WEBSERVICE_PROCESS_SUCCESS = "process_sucess";

	// 国美供应商常量名
	public static final String PARTNER_NAME = "gome";

	/**
	 * 车票类型1成人
	 */
	public static final Integer ADULT_TICKET = 1;

	/**
	 * 车票类型2儿童
	 */
	public static final Integer CHILDREN_TICKET = 2;

	/**
	 * 铁友返回的退票状态成功
	 */
	public static final Integer TIEYOU_RETURN_TICKET_SUCCESS = 1;
	/**
	 * 铁友返回的退票状态失败
	 */
	public static final Integer TIEYOU_RETURN_TICKET_FAIL = 2;

	// 铁友车票状态集合
	public static final List<Integer> TIEYOU_RETURN_TICKET_RESULT_COLL = Arrays
			.asList(TIEYOU_RETURN_TICKET_SUCCESS, TIEYOU_RETURN_TICKET_FAIL);

	/**
	 * 铁友回调接口处理成功
	 */
	public static final String STATUS_SUCCESS = "SUCCESS";

	/**
	 * 铁友回调接口处理失败
	 */
	public static final String STATUS_FAIL = "FAIL";

	// BILLTYPE_ORDERBILL :　订单
	public static final Integer BILLTYPE_ORDERBILL = 1;

	// BILLTYPE_REFUNDBILL：退款单
	public static final Integer BILLTYPE_REFUNDBILL = 2;

	// 重新推送的记录，处理状态ORDER_PUSH_AGENT表的status
	public static final int ORDER_PUSH_AGENT_INIT = 0;
	public static final int ORDER_PUSH_AGENT_SUCCES = 1;
	public static final int ORDER_PUSH_AGENT_DEAL = 2;
	public static final int ORDER_PUSH_AGENT_FAIL = 3;
	public static final int ORDER_PUSH_AGENT_TIMEOUT_WARN = 4;
	public static final int ORDER_PUSH_AGENT_NO_RESPONSE_WARN = 5;

	// 日志是否显示标记(前台、后台）
	public static final int LOG_SHOW = 1;// 显示
	public static final int LOG_NO_SHOW = 0;// 不显示

	// Sms模板
	public static final String SMS_TEMPLATE_TICKET_SUCCESS = "【国美在线】火车票订单#orderNo#，#takeTicketUserName#您已购#ticketDate##ticketNo#次#seatNumberList##fromStation##startTime#开。#passengerNameList#尽快换取纸质车票。";
	public static final String SMS_TEMPLATE_REFUND_TICKET_SUCCESS = "【国美在线】火车票退票订单#orderNo#，#passengerName#乘坐的#ticketDate##ticketNo#次#seat_number##fromStation##startTime#开，退票成功。";
	public static final String SMS_TEMPLATE_TICKET_FAILURE = "【国美在线】火车票订单#orderNo#，#takeTicketUserName#已购的#ticketDate#由#fromStation#至#toStation#的#ticketNo#次出票失败。#failMessage#";
	public static final String SMS_TEMPLATE_SERVICE_WARNING = "【火车票业务告警】#userName#于#orderTime#提交的火车票订单#orderNo#，支付时间#payTime#，已有35分钟未接收到供应商系统推送通知。";

	// 退票要在列车开车前的分钟阈值
	public final static Integer REFUND_TICKET_MINUTES_LIMIT = 35;

	// 订单超时取消分钟阈值
	public final static Integer ORDER_TIMEOUT_CANCEL_MINUTES_LIMIT = 30;

	// 推sap最大单据数量
	public final static Integer PUSH_SAP_BILL_SIZE_LIMIT = 200;

	// 订单已完成小时阈值
	public final static Integer ORDER_FINISH_HOURS_LIMIT = 24;



	// 已出票的退款单类型集合
	public final static List<Integer> ticketSuccessRefundTypeList = Arrays
			.asList(RefundType.cetk.type, RefundType.tb.type,
					RefundType.hxtk.type);

	// 未出票的退款单类型集合
	public final static List<Integer> ticketWaitRefundTypeList = Arrays.asList(
			RefundType.wptk.type, RefundType.qxdd.type, RefundType.cfzf.type);

	// 可推sap状态列表
	public static final List<Integer> canPushStatusList = Arrays.asList(
			PushMsgStatus.NO_PUSH.STATUS, PushMsgStatus.DEAL_FAILURE.STATUS);

	// 可推供应商退款通知状态列表
	public static final List<Integer> canPushRefundNotifyStatusList = Arrays
			.asList(PushMsgStatus.NO_PUSH.STATUS,
					PushMsgStatus.DEAL_FAILURE.STATUS);


}
