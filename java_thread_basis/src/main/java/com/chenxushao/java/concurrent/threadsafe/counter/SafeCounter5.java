package com.chenxushao.java.concurrent.threadsafe.counter;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 使用CAS(AtomicIntegerP后,不需要加锁，也可以达到线程安全.
 * @author chenxushao
 */
public class SafeCounter5 {

    private AtomicInteger value = new AtomicInteger(0);

    public void getNext() {
        value.getAndIncrement();
    }
}
