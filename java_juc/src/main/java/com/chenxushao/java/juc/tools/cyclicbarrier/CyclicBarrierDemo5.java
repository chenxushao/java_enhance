package com.chenxushao.java.juc.tools.cyclicbarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 可以调用多次，但是要到达关卡，必须多个线程都调用。
 */
public class CyclicBarrierDemo5 {
    public static void main(String[] args) {
        final CyclicBarrier barrier = new CyclicBarrier(2, new Runnable() {
            @Override
            public void run() {
                System.out.println("终于轮到我了");
            }
        });

        Runnable action1 = new Runnable() {
            @Override
            public void run() {
                String name = Thread.currentThread().getName();
                System.out.printf("Thread %s " + "executing barrier action.%n",
                    name);
                try {
                    barrier.await();
                    barrier.await();//可以调用多次，但是要到达关卡，必须多个线程都调用。
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }
        };

        ExecutorService executor = Executors.newCachedThreadPool();
        executor.execute(action1);
        System.out.println("hello");
    }
}