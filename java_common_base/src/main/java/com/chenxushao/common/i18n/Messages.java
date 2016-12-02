package com.chenxushao.common.i18n;

import java.util.ResourceBundle;

public class Messages extends MessagesCore {

	private static final String BUNDLE_NAME = "messages"; //$NON-NLS-1$

	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle
			.getBundle(BUNDLE_NAME);

	private Messages() {
	}

	public static String getString(String key) {
		return getString(key, RESOURCE_BUNDLE);
	}

	public static String getString(String key, Object... args) {
		return getString(key, RESOURCE_BUNDLE, args);
	}
}
