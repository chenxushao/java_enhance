package com.chenxushao.java.juc.tools.semaphore;

import com.chenxushao.java.juc.util.NamedThreadFactory;
import com.chenxushao.java.juc.util.RandomUtil;
import com.chenxushao.java.juc.util.ThreadUtil;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @author chenxushao
 */
public class SemaphoreDemo3 {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(5, new NamedThreadFactory("ttt"));
        Semaphore semaphore = new Semaphore(5);

        for (int i = 1; i <= 60; i++) {
            executorService.execute(new Task(semaphore));
        }

        executorService.shutdown();
    }


    private static class Task implements Runnable {

        private Semaphore semaphore;

        public Task(Semaphore semaphore) {
            this.semaphore = semaphore;
        }

        @Override
        public void run() {
            try {
                semaphore.acquire();
                System.out.println(Thread.currentThread().getName() + " running. availablePermits: " + semaphore.availablePermits());
                ThreadUtil.sleep(RandomUtil.nextInt(1, 4), TimeUnit.SECONDS);
                semaphore.release();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
