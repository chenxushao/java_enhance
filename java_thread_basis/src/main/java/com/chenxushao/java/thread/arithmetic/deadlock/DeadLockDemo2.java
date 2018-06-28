package com.chenxushao.java.thread.arithmetic.deadlock;

import com.chenxushao.java.thread.util.ThreadUtil;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author chenxushao
 */
public class DeadLockDemo2 {

    public static void main(String[] args) {

        Task task = new Task();

        Thread t1 = new Thread(task, "t1");
        t1.start();

        Thread t2 = new Thread(task, "t2");
        t2.start();

    }


    private static class Task implements Runnable {

        Lock lock1 = new ReentrantLock();

        Lock lock2 = new ReentrantLock();

        @Override
        public void run() {

            lock1.lock();
            ThreadUtil.sleep(5, TimeUnit.SECONDS);
            lock2.lock();
            ThreadUtil.sleep(3, TimeUnit.SECONDS);
//            lock2.unlock();
            lock1.unlock();

            lock2.lock();
            ThreadUtil.sleep(2, TimeUnit.SECONDS);
            lock1.lock();
            ThreadUtil.sleep(3, TimeUnit.SECONDS);
//            lock1.unlock();
            lock2.unlock();


        }
    }
}
