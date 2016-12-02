package com.chenxushao.java.basis.enums;

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import com.google.common.collect.LinkedHashMultimap;

//import cn.com.gome.ztghb.exception.ServiceException;

/******************************************************************************
 * 
 * <p>
 * Description:退款单状态机服务
 * </p>
 * Project: biz Package: cn.com.gome.train.statemachine File:
 * RefundBillStateMachineService.java
 * 
 * @author chenxushao@hotmail.com
 * @date 2015年9月22日 上午11:46:17
 * 
 *****************************************************************************/
public class RefundBillStateMachineService {

	private final static Logger logger = LoggerFactory
			.getLogger(RefundBillStateMachineService.class);

	// 状态机配置
	private static LinkedHashMultimap<Integer, RefundBillState> multimap = LinkedHashMultimap
			.create();

	static {
		// NEW
		multimap.put(RefundBillState.NEW.VALUE,
				RefundBillState.FINANCIAL_AUDITED);
		multimap.put(RefundBillState.NEW.VALUE,
				RefundBillState.FINANCIAL_AUDIT_NOT_THROUGH);
		// FINANCIAL_AUDITED
		multimap.put(RefundBillState.FINANCIAL_AUDITED.VALUE,
				RefundBillState.RB_PUSHED);
		// FINANCIAL_AUDIT_NOT_THROUGH
		multimap.put(RefundBillState.FINANCIAL_AUDIT_NOT_THROUGH.VALUE,
				RefundBillState.FINANCIAL_AUDITED);

		// RB_PUSHED
		multimap.put(RefundBillState.RB_PUSHED.VALUE,
				RefundBillState.RB_AUDITED);
		multimap.put(RefundBillState.RB_PUSHED.VALUE,
				RefundBillState.RB_AUDIT_NOT_THROUGH);

		// RB_AUDIT_NOT_THROUGH
		multimap.put(RefundBillState.RB_AUDIT_NOT_THROUGH.VALUE,
				RefundBillState.RB_AUDITED);
	}

	public static void checkStateChange(RefundBillState currentState,
			RefundBillState targetState) throws RuntimeException {

		if (currentState == null || targetState == null) {
			// throw new ServiceException("当前状态或目标状态值不合法");
			throw new RuntimeException("当前状态或目标状态值不合法");
		}

		Set<RefundBillState> nextStates = multimap.get(currentState.VALUE);
		if (CollectionUtils.isEmpty(nextStates)
				|| !nextStates.contains(targetState)) {
			// MonitorUtil.recordOne("refund-bill-checkStateChange-error");
			logger.error(
					"refund bill checkStateChange error 不允许进行状态转换：当前状态={}, 目标状态={}",
					currentState, targetState);
			throw new RuntimeException("不允许进行状态流转，当前状态=" + currentState.NAME
					+ ", 目标状态=" + targetState.NAME);
		}
	}
}
