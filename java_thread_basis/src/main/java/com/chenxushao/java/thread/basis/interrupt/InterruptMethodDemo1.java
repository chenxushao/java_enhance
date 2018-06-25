package com.chenxushao.java.thread.basis.interrupt;

/**
 * 如果线程的代码实现本身没有响应中断，那么调用了中断将没有任何效果。
 * 调用interrupt方法后,如果没有抛出InterruptedException的方法，那么，isInterrupted返回true,线程结束后，会返回false.
 *
 * @author chenxushao
 */
public class InterruptMethodDemo1 {

    public static void main(String[] args) throws Exception {

        Thread t = new Thread(new Task(), "t1");
        System.out.println("before start: " + t.getName() + " " + t.isInterrupted());
        t.start();
        System.out.println("after start: " + t.getName() + " " + t.isInterrupted());
        t.interrupt();

        System.out.println("after interrupt: " + t.getName() + " " + t.isInterrupted());
        t.join();
        System.out.println("after thread terminated: " + t.getName() + " " + t.isInterrupted());
    }

    private static class Task implements Runnable {

        @Override
        public void run() {
            Thread currentThread = Thread.currentThread();
            for (int i = 1; i <= 100; i++) {
                System.out.println("in running: " + currentThread.getName() + " " + currentThread.isInterrupted());
            }

        }
    }
}
