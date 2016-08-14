package com.chenxushao.guava.stirng;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import com.google.common.base.CaseFormat;

/**
 * CaseFormat features
 */
public class CaseFormatDemo1 {

    @Test
    public void shouldConvertToUpperUnderscore() throws Exception {

        // then
        assertThat(CaseFormat.UPPER_CAMEL.to(CaseFormat.UPPER_UNDERSCORE, "HelloWorld"))
                .isEqualTo("HELLO_WORLD");
    }

    @Test
    public void shouldConvertToLowerCamel() throws Exception {

        // then
        assertThat(CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, "HELLO_WORLD"))
                .isEqualTo("helloWorld");
    }

    @Test
    public void shouldConvertToLowerHyphen() throws Exception {

        // then
        assertThat(CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_HYPHEN, "helloWorld"))
                .isEqualTo("hello-world");
    }
}
