package com.chenxushao.java.thread.basis.interrupt;

import com.chenxushao.java.thread.util.ThreadUtil;

/**
 * 许多声明抛出InterruptedException的方法(如Thread.sleep)在抛出InterruptedException之前，Java虚拟机会先将该线程的中断标识位清除，然后抛出InterruptedException，此时调用isInterrupted方法将会返回false。
 *
 * @author chenxushao
 */
public class ThreadSleepInterruptDemo1 {

    public static void main(String[] args) {

        Thread t = new Thread(new Task(), "t1");
        t.start();

        System.out.println("in mian " + t.getName() + "  isInterrupted " + t.isInterrupted());
        ThreadUtil.sleep(5 * 1000);
        t.interrupt();

        System.out.println("after interrupt " + t.getName() + "  isInterrupted " + t.isInterrupted());

    }


    private static class Task implements Runnable {

        @Override
        public void run() {
            while (true) {
                Thread currentThread = Thread.currentThread();
                System.out.println("begin " + currentThread.getName() + " running... isInterrupted " + currentThread.isInterrupted());

                try {
                    Thread.sleep(1000 * 1000);
                } catch (InterruptedException e) {
                    System.out.println("in catch: " + currentThread.getName() + " isInterrupted " + currentThread.isInterrupted());
                }

                System.out.println("end " + currentThread.getName() + " running... isInterrupted " + currentThread.isInterrupted());

            }
        }
    }
}
