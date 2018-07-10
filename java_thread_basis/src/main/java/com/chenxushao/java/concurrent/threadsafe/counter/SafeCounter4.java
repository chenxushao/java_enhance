package com.chenxushao.java.concurrent.threadsafe.counter;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 使用ReentrantLock达到线程安全
 * @author chenxushao
 */
public class SafeCounter4 {

    private int count = 0;

    private final Lock lock = new ReentrantLock();

    public int getNext() {
        lock.lock();
        try {
           return count++;
        } finally {
            lock.unlock();
        }
    }
}
