package com.chenxushao.java.concurrent.basis.interrupts.interrupt;

import com.chenxushao.java.concurrent.util.ThreadUtil;

import java.util.concurrent.TimeUnit;

/**
 * 如果线程的代码实现本身没有响应中断，那么调用了中断将没有任何效果。
 * 调用interrupt方法后,如果没有抛出InterruptedException的方法，那么，isInterrupted返回true,线程结束后，会返回false.
 *
 * @author chenxushao
 */
public class InterruptMethodDemo1 {

    public static void main(String[] args) throws Exception {

        Thread t = new Thread(new Task(), "t1");
        System.out.println(t.getState()+ "," + t.getName() + ", " + t.isInterrupted());
        t.start();
        System.out.println(t.getState() + "," + t.getName() + ", " + t.isInterrupted());
        t.interrupt();

        System.out.println("after interrupts: " + t.getName() + ", " + t.isInterrupted());
        t.join();
        System.out.println(t.getState() + "," + t.getName() + ", " + t.isInterrupted());
    }

    private static class Task implements Runnable {

        @Override
        public void run() {
            Thread currentThread = Thread.currentThread();
            for (int i = 1; i <= 5; i++) {
                System.out.println(i+": in running: " + currentThread.getName() + " " + currentThread.isInterrupted());
                ThreadUtil.sleep(1, TimeUnit.SECONDS);

            }

        }
    }
}
