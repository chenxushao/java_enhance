package com.chenxushao.java.concurrent.threadsafe.counter;

/**
 * 线程安全，锁的对象是lock，可以灵活控制，只锁真正需要同步的代码，最佳实践。注意lock不能为null,也不能改变
 * @author chenxushao
 */
public class SafeCounter2 {

    private int value = 0;

    private final Object lock = new Object();

    public int getNext() {
        synchronized (lock) {
            return value++;
        }
    }
}