package com.chenxushao.java.juc.bestpractice;

import java.util.HashMap;
import java.util.Map;

/******************************************************************************
 * 
 * <p>Description:存储于ThreadLocal的上下文信息 </p> 
 * Project: common
 * Package: com.kscloud.boss.common.util
 *    File: ThreadLocalContext.java
 * 
 * @author chenxushao@hotmail.com
 * @Created on 2016年8月13日 下午4:36:16
 * @since 1.0
 *****************************************************************************/
public class ThreadLocalContext {

	private static ThreadLocal<Map<String, Object>> context = new ThreadLocal<Map<String, Object>>() {
		@Override
		protected Map<String, Object> initialValue() {
			return new HashMap<String, Object>();
		}
	};

	/**
	 * 放入ThreadLocal的Context.
	 */
	public static void put(String key, Object value) {
		context.get().put(key, value);
	}

	/**
	 * 取出ThreadLocal的Context.
	 */
	@SuppressWarnings("unchecked")
	public static <T> T get(String key) {
		return (T) (context.get().get(key));
	}

	/**
	 * 清理ThreadLocal的Context内容.
	 */
	public static void reset() {
		context.get().clear();
	}
}
