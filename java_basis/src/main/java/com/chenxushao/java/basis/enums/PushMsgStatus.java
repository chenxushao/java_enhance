package com.chenxushao.java.basis.enums;

/******************************************************************************
 * 
 * <p>
 * Description: 消息推送相关状态
 * </p>
 * Project: job Package: cn.com.gome.train.constant File: PushMsgStatus.java
 * 
 * @author chenxushao@hotmail.com
 * @date 2015年11月4日 下午5:33:22
 * 
 *****************************************************************************/
public enum PushMsgStatus {

	NO_PUSH(1, "未推"), PUSHED(2, "已推"), DEAL_SUCCESS(3, "成功"), DEAL_FAILURE(4,
			"失败"), DELETED(5, "已删除");

	public final Integer STATUS;
	public final String DESC;

	private PushMsgStatus(Integer status, String desc) {
		STATUS = status;
		DESC = desc;
	}

	public static PushMsgStatus convert(Integer status) {
		if (status == null) {
			return null;
		}
		PushMsgStatus[] pushMsgStatuses = PushMsgStatus.values();
		for (PushMsgStatus pushMsgStatus : pushMsgStatuses) {
			if (status.equals(pushMsgStatus.STATUS)) {
				return pushMsgStatus;
			}
		}
		// Default
		return null;
	}
	
	@Override
	public String toString(){
		return DESC + "("+STATUS+")";
	}
}