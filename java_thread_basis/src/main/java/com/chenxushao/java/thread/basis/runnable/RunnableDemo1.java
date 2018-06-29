package com.chenxushao.java.thread.basis.runnable;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author chenxushao
 */
public class RunnableDemo1 {


    public static void main(String[] args) {
        ExecutorService executor = Executors.newSingleThreadExecutor();

        long begin = System.currentTimeMillis();
        executor.submit(new RunnableTask());
        System.out.println((System.currentTimeMillis() - begin));

        executor.shutdown();
    }

    //实现java.lang.Runnable接口，没有返回值的任务
    private static class RunnableTask implements Runnable {

        public void run() {
            System.out.println("run");
        }

    }

}
