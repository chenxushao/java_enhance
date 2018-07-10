package com.chenxushao.java.concurrent.threadsafe.counter;

import javax.annotation.concurrent.ThreadSafe;

/**
 * uses volatile to guarantee the visibility of the current result where locks(synchronzied)only allow one thread to access a value at once,
 * volatile reads allow more than one, you get a higher degree of sharing
 */
@ThreadSafe
public class Counter4 {

    private volatile int count = 0; //shared data

    public synchronized void increment() {
        count++;
    }

    public int getCount() {
        return count;
    }

}