package com.chenxushao.java.concurrent.basis.threadlocalrandom;

import java.util.concurrent.ThreadLocalRandom;

/**
 * @author chenxushao
 */
public class ThreadLocalRandomDemo1 {

    public static void main(String[] args) {

        ThreadLocalRandom tlr = ThreadLocalRandom.current();
        for (int i = 0; i < 1000; i++) {
            System.out.println(tlr.nextInt(1, 100));
        }

    }
}
