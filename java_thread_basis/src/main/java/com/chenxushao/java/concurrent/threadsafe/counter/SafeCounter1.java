package com.chenxushao.java.concurrent.threadsafe.counter;

/**
 * 线程安全，锁的对象是当前SafeCounter实例，不会影响到另一个SafeCounter实例的getNext方法
 *
 * @author chenxushao
 */
public class SafeCounter1 {

    private int value = 0;

    public synchronized int getNext() {
        return value++;
    }
}