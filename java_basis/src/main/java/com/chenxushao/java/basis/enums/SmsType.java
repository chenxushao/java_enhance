package com.chenxushao.java.basis.enums;

import java.util.Map;
import java.util.Set;

/******************************************************************************
 * 
 * <p>
 * Description: 短信类型枚举
 * </p>
 * Project: biz Package: cn.com.gome.train.statemachine File: SmsType.java
 * 
 * @author chenxushao@hotmail.com
 * @date 2015年9月22日 下午3:22:42
 * 
 *****************************************************************************/
public enum SmsType {
	TICKET_SUCCESS(1, "7004", BizConstants.SMS_TEMPLATE_TICKET_SUCCESS, "出票成功") {
		@Override
		public boolean sendSms(Map<String, String> paramMap) {
			String mobileId = paramMap.get(SMS_MOBILE_ID);
//			int status = SMSUtil.sendSms(diffId, mobileId, paramMap, desc);
//			return (status == SMSUtil.SMS_SUCESS) ? true : false;
			int status = 1;
			return (status ==  1) ? true : false;
		}

		@Override
		public String buildSmsContent(Map<String, String> paramMap) {
			return buildSms(this, paramMap);
		}
	},

	REFUND_TICKET_SUCCESS(2, "7005",
			BizConstants.SMS_TEMPLATE_REFUND_TICKET_SUCCESS, "退票成功") {
		@Override
		public boolean sendSms(Map<String, String> paramMap) {
			String mobileId = paramMap.get(SMS_MOBILE_ID);
//			int status = SMSUtil.sendSms(diffId, mobileId, paramMap, desc);
			
			int status = 1;
			return (status ==  1) ? true : false;
		}

		@Override
		public String buildSmsContent(Map<String, String> paramMap) {
			return buildSms(this, paramMap);
		}

	},

	TICKET_FAILURE(3, "7006", BizConstants.SMS_TEMPLATE_TICKET_FAILURE, "出票失败") {
		@Override
		public boolean sendSms(Map<String, String> paramMap) {
			String mobileId = paramMap.get(SMS_MOBILE_ID);
//			int status = SMSUtil.sendSms(diffId, mobileId, paramMap, desc);
//			return (status ==  SMSUtil.SMS_SUCESS) ? true : false;
			int status = 1;
			return (status ==  1) ? true : false;
		}

		@Override
		public String buildSmsContent(Map<String, String> paramMap) {
			return buildSms(this, paramMap);
		}
	},

	SERVICE_WARNING(4, "7007", BizConstants.SMS_TEMPLATE_SERVICE_WARNING,
			"火车票业务告警") {
		@Override
		public boolean sendSms(Map<String, String> paramMap) {
			String mobileId = paramMap.get(SMS_MOBILE_ID);
//			int status = SMSUtil.sendSms(diffId, mobileId, paramMap, desc);
//			return (status ==  SMSUtil.SMS_SUCESS) ? true : false;
			int status = 1;
			return (status ==  1) ? true : false;
		}

		@Override
		public String buildSmsContent(Map<String, String> paramMap) {
			return buildSms(this, paramMap);
		}
	};

	public final Integer type;
	public final String diffId;
	public final String template;
	public final String desc;

	// 发送短信手机号key
	public static final String SMS_MOBILE_ID = "SmsMobileId";

	private SmsType(Integer type, String diffId, String template, String desc) {
		this.type = type;
		this.diffId = diffId;
		this.template = template;
		this.desc = desc;
	}

	public abstract boolean sendSms(Map<String, String> paramMap);

	public abstract String buildSmsContent(Map<String, String> paramMap);

	public static SmsType convert(Integer type) {
		if (type == null) {
			return null;
		}
		SmsType[] smsTypes = SmsType.values();
		for (SmsType smsType : smsTypes) {
			if (type.equals(smsType.type)) {
				return smsType;
			}
		}
		// Default
		return null;
	}

	private static String buildSms(SmsType smsType, Map<String, String> paramMap) {
		if(paramMap==null || paramMap.size()==0){
			return "";
		}
		Set<Map.Entry<String, String>> entrySet = paramMap.entrySet();
		String smsContent = smsType.template;
		for (Map.Entry<String, String> entry : entrySet) {
			String key = "#" + entry.getKey() + "#";
			smsContent = smsContent.replace(key, entry.getValue());
		}
		return smsContent;
	}
}
