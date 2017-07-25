package com.chenxushao.java.juc.tools.cdl;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 火箭发射.
 */
public class CountDownLatchDemo1 {

    public static void main(String[] args) throws Exception {
        final CountDownLatch latch = new CountDownLatch(10);

        System.out.println("开始准备火箭发射");

        ExecutorService executorService = Executors.newFixedThreadPool(10, new ThreadFactoryBuilder().setDaemon(true).setNameFormat("checke-%d").build());

        for (int i = 0; i < 10; i++) {
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName() + " check complete.");
                    try {
                        Thread.sleep(new Random().nextInt(10) * 1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    latch.countDown();
                }
            });
        }

        latch.await();
        System.out.println("Fire");
        executorService.shutdown();;
    }

}
