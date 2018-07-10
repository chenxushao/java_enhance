package com.chenxushao.java.concurrent.threadsafe.counter;


/**
 * 非线程安全，value++不是原子操作，value状态不稳定，需要同步
 * @author chenxushao
 */
public class UnSafeCounter1 {

    private int value = 0;

    public int getNext() {
        return value++;
    }
}