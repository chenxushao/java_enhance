package com.chenxushao.java.concurrent.threadsafe.counter;

/**
 * value++不是原子操作,使用volatile也不能达到线程安全.
 *
 * @author chenxushao
 */
public class UnSafeCounter2 {

    private volatile int value = 0;

    public void getNext() {
        value++;
    }
}
