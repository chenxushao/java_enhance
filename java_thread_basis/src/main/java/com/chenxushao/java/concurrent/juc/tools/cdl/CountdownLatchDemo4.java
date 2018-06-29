package com.chenxushao.java.concurrent.juc.tools.cdl;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 可以调用多次，调用一次减1	，不一定需要所有线程都调用。
 */
public class CountdownLatchDemo4 {

    public static void main(String[] args) throws Exception {
        ExecutorService service = Executors.newCachedThreadPool();
        final CountDownLatch cdAnswer = new CountDownLatch(3);
        Runnable runnable = new Runnable() {
            public void run() {
                try {
                    cdAnswer.countDown();
                    cdAnswer.countDown();//可以调用多次，调用一次减1	，不一定需要所有线程都调用。
                    System.out.println("x");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        service.execute(runnable);
        cdAnswer.await();
        System.out.println("ok");
        service.shutdown();
    }
}
