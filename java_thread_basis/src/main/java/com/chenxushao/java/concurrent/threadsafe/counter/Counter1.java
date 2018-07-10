package com.chenxushao.java.concurrent.threadsafe.counter;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public class Counter1 {
    private int count = 0; //shared data

    public void increment() {
        count++;
    }

    public int getCount() {
        return count;
    }

}