package com.chenxushao.java.concurrent.ratelimiter;

import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import com.google.common.util.concurrent.RateLimiter;

import java.util.concurrent.Executors;

public class RateLimiterTest {
    public static void main(String[] args) {
        testRateLimiter();
    }

    /**
     * RateLimiter类似于JDK的信号量Semphore，他用来限制对资源并发访问的线程数
     */
    public static void testRateLimiter() {
        
        ListeningExecutorService executorService = MoreExecutors
                .listeningDecorator(Executors.newFixedThreadPool(5));

        RateLimiter limiter = RateLimiter.create(2); // 每秒不超过4个任务被提交

        
        for (int i = 0; i < 50; i++) {
            limiter.acquire(); // 请求RateLimiter, 超过permits会被阻塞
            
            executorService.submit(new Task("is "+ i));
        }
    }


    private static class Task implements Runnable {
        String str;

        public Task(String str) {
            this.str = str;
        }

        @Override
        public void run() {
            System.out.println("Task call execute.." + str);
        }

    }
}


