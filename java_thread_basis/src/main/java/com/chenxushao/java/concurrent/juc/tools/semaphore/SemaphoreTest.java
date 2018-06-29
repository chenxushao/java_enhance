package com.chenxushao.java.concurrent.juc.tools.semaphore;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class SemaphoreTest {

    private static final int THREAD_COUNT = 100;

    private static ExecutorService threadPool = Executors
        .newFixedThreadPool(THREAD_COUNT);

    private static Semaphore s = new Semaphore(2);

    public static void main(String[] args) {
        for (int i = 0; i < THREAD_COUNT; i++) {
            threadPool.execute(new Runnable() {

                @Override
                public void run() {
                    try {
                        s.acquire();
                        System.out.println(Thread.currentThread().getName() + " save data");
                        Thread.sleep(1000*10);
                        s.release();
                    } catch (InterruptedException e) {
                       e.printStackTrace();
                    }
                }
            });
        }

        threadPool.shutdown();
    }
}