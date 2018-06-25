package com.chenxushao.java.thread.arithmetic.deadlock;

import com.chenxushao.java.thread.util.ThreadUtil;

import java.util.concurrent.TimeUnit;

/**
 * 死锁范例.
 *
 * @author chenxushao
 */
public class DeadLockTest1 {

    public static void main(String[] args) {
        Object o1 = new Object();
        Object o2 = new Object();
        Task task = new Task(o1, o2);

        Thread t1 = new Thread(task, "t1");
        t1.start();

        Thread t2 = new Thread(task, "t2");
        t2.start();
    }


    private static class Task implements Runnable {

        private Object o1;

        private Object o2;

        public Task(Object o1, Object o2) {
            this.o1 = o1;
            this.o2 = o2;
        }

        @Override
        public void run() {

            synchronized (o1) {
                ThreadUtil.sleep(2, TimeUnit.SECONDS);
                System.out.println(Thread.currentThread().getName() + " get o1");
                synchronized (o2) {
                    System.out.println(Thread.currentThread().getName() + " get o2");
                }
            }

            synchronized (o2) {
                ThreadUtil.sleep(2, TimeUnit.SECONDS);
                System.out.println(Thread.currentThread().getName() + " get o2");
                synchronized (o1) {
                    System.out.println(Thread.currentThread().getName() + " get o1");

                }
            }
        }
    }
}
