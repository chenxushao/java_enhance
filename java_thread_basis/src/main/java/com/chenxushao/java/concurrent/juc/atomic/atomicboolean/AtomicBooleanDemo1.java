package com.chenxushao.java.concurrent.juc.atomic.atomicboolean;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author chenxushao
 */
public class AtomicBooleanDemo1 {


    public static void main(String[] args) {
        AtomicBoolean atomicBoolean = new AtomicBoolean();
        System.out.println(atomicBoolean.get());

        atomicBoolean = new AtomicBoolean(true);
        System.out.println(atomicBoolean.get());

        atomicBoolean = new AtomicBoolean(false);
        System.out.println(atomicBoolean.get());

        atomicBoolean.set(true);
        System.out.println(atomicBoolean.get());

    }
}
