package com.chenxushao.guava.stirng;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import com.google.common.base.Strings;

/**
 * Class to show features of Strings class
 */
public class StringsDemo3 {

    @Test
    public void shouldReturnTrueOnNullString() throws Exception {
      assertThat(Strings.isNullOrEmpty(null)).isTrue();
    }

    @Test
    public void shouldConvertNullToEmpty() throws Exception {
        assertThat(Strings.nullToEmpty(null)).isEqualTo("");
    }

    @Test
    public void shouldConvertEmptyToNull() throws Exception {
        assertThat(Strings.emptyToNull("")).isNull();
    }

    @Test
    public void shouldPadEnd() throws Exception {

        assertThat(Strings.padEnd("Nothing special", 20, '*')).isEqualTo("Nothing special*****");
    }

    @Test
    public void shouldPadStart() throws Exception {
        assertThat(Strings.padStart("Nothing special", 20, ' ')).isEqualTo("     Nothing special");
    }

    @Test
    public void shouldRepeatGivenString() throws Exception {
        assertThat(Strings.repeat("Hello ", 3)).isEqualTo("Hello Hello Hello ");
    }
}
