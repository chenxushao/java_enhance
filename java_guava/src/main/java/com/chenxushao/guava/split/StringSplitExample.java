package com.chenxushao.guava.split;

import java.util.Arrays;

public class StringSplitExample {

    public static void main(String[] args) {
        String commaSeparatedString  = "Foo,,Bar,,Baz,,,";
        String[] words = commaSeparatedString.split(",");
        System.out.println(Arrays.toString(words));
    }
}
