package com.chenxushao.java.concurrent.basis.exceptions;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 线程池中，使用execute方式提交的任务发生异常，外层捕获不到异常。
 * 同时发生异常的线程会被销毁，重新创建线程，这一点从线程输出的名字上就可以看出来。这会有一定的开销，要注意这一点。
 *
 * @author chenxushao
 */
public class ThreadOccurException5 {

    public static void main(String[] args) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2, 2,
            0L, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<Runnable>());

        threadPoolExecutor.prestartAllCoreThreads();

        for (int i = 1; i <= 6; i++) {
            threadPoolExecutor.execute(new CommonTask());
        }

        try {
            for (int i = 1; i <= 6; i++) {
                threadPoolExecutor.execute(new OccurExceptionTask(i));
            }
        } catch (Exception e) {
            System.out.println("发生异常");
        }

        System.out.println();

        threadPoolExecutor.shutdown();
    }


    private static class OccurExceptionTask implements Runnable {

        private int value;

        private OccurExceptionTask(int value) {
            this.value = value;
        }

        @Override
        public void run() {
            System.out.println("occur: " + value + " " + Thread.currentThread().getName() + "  running...");
            System.out.println(value / 0);
        }
    }


    private static class CommonTask implements Runnable {

        @Override
        public void run() {
            System.out.println("common: " + Thread.currentThread().getName() + "  running...");
        }
    }

}
