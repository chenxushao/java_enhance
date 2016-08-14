package com.chenxushao.guava.joiner;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.google.common.base.Joiner;

/**
 * Test class to show some features of Joiners
 */
public class JoinerDemo2 {


    public static List<String> languages = Arrays.asList("Java", "Haskell", "Scala", "Brainfuck");

    @Test
    public void shouldJoinWithCommas() throws Exception {
        assertThat(Joiner.on(",").join(languages)).isEqualTo("Java,Haskell,Scala,Brainfuck");
    }

    public void shouldThrowNullPointerException() throws Exception {
        assertThat(Joiner.on(",").join(countriesWithNullValue)).isEqualTo("Poland,Brasil,Ukraine,null,England,Croatia");
    }

    public static List<String> countriesWithNullValue = Arrays
            .asList("Poland", "Brasil", "Ukraine", null, "England", "Croatia");

    @Test
    public void shouldJoinWithCommasAndOmitNulls() throws Exception {
        assertThat(Joiner.on(",").skipNulls().join(countriesWithNullValue))
                .isEqualTo("Poland,Brasil,Ukraine,England,Croatia");
    }

    @Test
    public void shouldJoinWithCommasAndReplaceNullsWithWordNothing() throws Exception {
        assertThat(Joiner.on(",").useForNull("NONE").join(countriesWithNullValue))
                .isEqualTo("Poland,Brasil,Ukraine,NONE,England,Croatia");
    }

    public static Map<Integer, String> numbersWords = new HashMap<Integer, String>();

    static {
        numbersWords.put(1, "one");
        numbersWords.put(2, "two");
        numbersWords.put(3, null);
        numbersWords.put(4, "four");
    }

    @Test
    public void shouldJoinMap() throws Exception {
        assertThat(Joiner.on(" | ").withKeyValueSeparator(" -> ")
                .useForNull("Unknown").join(numbersWords))
                .isEqualTo("1 -> one | 2 -> two | 3 -> Unknown | 4 -> four");
    }
}