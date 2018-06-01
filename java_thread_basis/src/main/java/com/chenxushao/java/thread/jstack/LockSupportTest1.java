package com.chenxushao.java.thread.jstack;

import java.util.concurrent.locks.LockSupport;

/**
 * @author chenxushao
 */
public class LockSupportTest1 {

    public static void main(String[] args) {

        LockSupportTest1 lockSupportTest1 = new LockSupportTest1();

        System.out.println("pre");

        LockSupport.parkNanos(lockSupportTest1, Long.MAX_VALUE);

        System.out.println("finish");
    }



}
