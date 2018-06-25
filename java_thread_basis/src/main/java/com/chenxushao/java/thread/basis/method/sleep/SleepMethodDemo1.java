package com.chenxushao.java.thread.basis.method.sleep;

import com.chenxushao.java.thread.util.ThreadUtil;

import java.util.concurrent.TimeUnit;

/**
 * @author chenxushao
 */
public class SleepMethodDemo1 {

    public static void main(String[] args) {
        Thread t1 = new Thread(new Task(), "t1");
        t1.start();
        System.out.println(t1.getId());
        System.out.println(t1.getPriority());
    }

    private static class Task implements Runnable {

        @Override
        public void run() {
            while (true) {
                System.out.println(Thread.currentThread().getName() + " running...");
                ThreadUtil.sleep(2, TimeUnit.SECONDS);
            }
        }
    }
}