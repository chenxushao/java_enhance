package com.chenxushao.java.concurrent.threadsafe.counter;

import javax.annotation.concurrent.ThreadSafe;

/**
 * get need to be synchronized for the class to be thread-safe,
 * to ensure that no updates are lost,and that all threads see the most recent value of the counter. (Visibility)
 */
@ThreadSafe
public class Counter3 {
    private int count = 0; //shared data

    public synchronized void increment() {
        count++;
    }

    public synchronized int getCount() {
        return count;
    }

}