package com.chenxushao.java.concurrent.basis.interrupts.isinterrupt;

import com.chenxushao.java.concurrent.util.ThreadUtil;

import java.util.concurrent.TimeUnit;

/**
 *  isInterrupted()返回线程的中断状态
 * @author chenxushao
 */
public class IsInterruptMethodDemo1 {

    public static void main(String[] args) throws Exception {

        Thread t = new Thread(new Task(), "t");
        System.out.println(t.getState()+ "," + t.getName() + ", " + t.isInterrupted());
        t.start();
        System.out.println(t.getState() + "," + t.getName() + ", " + t.isInterrupted());
        t.interrupt();

        System.out.println("after interrupts: " + t.getName() + ", " + t.isInterrupted());
    }

    private static class Task implements Runnable {

        @Override
        public void run() {
            Thread currentThread = Thread.currentThread();
            for (int i = 1; i <= 5; i++) {
                System.out.println("running...");
                ThreadUtil.sleep(1, TimeUnit.SECONDS);

            }

        }
    }
}
