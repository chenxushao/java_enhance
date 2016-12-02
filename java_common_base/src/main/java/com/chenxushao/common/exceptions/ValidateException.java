package com.chenxushao.common.exceptions;

/**
 * 验证异常
 */
public class ValidateException extends RuntimeException {
	private static final long serialVersionUID = 6057602589533840889L;

	public ValidateException() {
	}

	public ValidateException(String msg) {
		super(msg);
	}

}
