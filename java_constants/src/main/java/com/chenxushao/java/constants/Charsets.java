package com.chenxushao.java.constants;

import java.nio.charset.Charset;

/**
 * JDK7可直接使用java.nio.charset.StandardCharsets.
 */
public final class Charsets {

	private Charsets() {

	}

	public static final Charset UTF8 = Charset.forName("UTF-8");

}
