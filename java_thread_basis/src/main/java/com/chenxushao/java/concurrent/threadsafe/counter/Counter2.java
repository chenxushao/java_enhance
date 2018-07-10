package com.chenxushao.java.concurrent.threadsafe.counter;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public class Counter2 {
    private int count = 0; //shared data

    public synchronized void increment() {//write protected by lock
        count++;
    }

    public int getCount() {
        return count;
    }
}