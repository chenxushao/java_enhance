package com.chenxushao.guava.lang;

import static org.assertj.core.api.Assertions.assertThat;

import java.nio.charset.Charset;

import org.junit.Test;

import com.google.common.base.Charsets;

/**
 * Classs to show how to use Charsets class
 */
public class CharsetsDemo2 {

	@Test
	public void shouldCreateSupportedInJavaCharset() throws Exception {

		// given
		Charset charset = Charset.forName("UTF-8");

		// when
		Charset charsetFromGuava = Charsets.UTF_8;

		// then
		assertThat(charset.name()).isEqualTo(charsetFromGuava.name());
	}
}
