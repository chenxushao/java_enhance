package com.chenxushao.java.concurrent.basis.exceptions;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 通过为线程设置UncaughtExceptionHandler也能够捕获到异常，但一般也仅仅能够记录一下日志。外层仍然拿不到异常.
 *
 * @author chenxushao
 */
public class ThreadOccurException2 {

    public static void main(String[] args) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 1,
            0L, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<Runnable>());
        try {
            Thread t = new Thread(new Task(), "t");
            t.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
                @Override
                public void uncaughtException(Thread t, Throwable e) {
                    System.out.println(t.getName() + "在UncaughtExceptionHandler中捕获到发生异常啦.");
                }
            });

            t.start();
        } catch (Exception e) {
            System.out.println("发生异常");
        }
    }

    private static class Task implements Runnable {

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + "  running...");
            System.out.println(1 / 0);
        }
    }

}
