package com.chenxushao.java.thread.basis.state;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author chenxushao
 *
 * TODO
 */
public class LockTest1 {

    Lock lock = new ReentrantLock();

    public  void test1(){
        lock.lock();

        try {
            System.out.println("test1");
        }finally {
            lock.unlock();
        }

    }

    public static void main(String[] args) {
        LockTest1 lockTest1 = new LockTest1();
        lockTest1.test1();
    }

}
