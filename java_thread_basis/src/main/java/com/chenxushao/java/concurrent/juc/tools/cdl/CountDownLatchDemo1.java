package com.chenxushao.java.concurrent.juc.tools.cdl;

import com.chenxushao.java.concurrent.util.ThreadUtil;
import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 使用CountDownLatch来模拟火箭发射倒计时.
 */
public class CountDownLatchDemo1 {

    public static void main(String[] args) throws Exception {
        final CountDownLatch latch = new CountDownLatch(10);

        System.out.println("开始准备火箭发射");

        ExecutorService executorService = Executors.newFixedThreadPool(10, new ThreadFactoryBuilder().setNameFormat("checke-%d").build());

        for (int i = 0; i < 10; i++) {
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    int sleepTime = new Random().nextInt(10) * 1000;
                    System.out.println(Thread.currentThread().getName() + " check complete," + " sleepTime:" + sleepTime);
                    ThreadUtil.sleep(sleepTime);
                    latch.countDown();
                }
            });
        }

        latch.await();
        System.out.println("各项工作准备就绪，开始发射！");
        executorService.shutdown();
    }

}
