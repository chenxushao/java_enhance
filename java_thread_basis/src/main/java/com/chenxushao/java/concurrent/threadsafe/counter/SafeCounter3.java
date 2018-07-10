package com.chenxushao.java.concurrent.threadsafe.counter;

/**
 * 线程安全，锁的对象是SafeCounter类，任何实例都会受锁的影响，一般不这样用
 * @author chenxushao
 */
public class SafeCounter3 {

    private int value = 0;

    public int getNext() {
        synchronized (SafeCounter3.class) {
            return value++;
        }
    }
}