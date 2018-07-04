package com.chenxushao.java.concurrent.basis.exceptions;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 线程池中，使用submit方式提交的任务发生异常，外层也捕获不到异常。
 * 但可通过调用Future.get，拿到发生的异常。这是线程池中让外层知道任务发生了异常的一种方法。
 *
 * @author chenxushao
 */
public class ThreadOccurException6 {

    public static void main(String[] args){
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2, 2,
            0L, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<Runnable>());

        threadPoolExecutor.prestartAllCoreThreads();

        List<Future> futures = Lists.newArrayList();

        try {
            for (int i = 1; i <= 6; i++) {
               Future future =  threadPoolExecutor.submit(new OccurExceptionTask(i));
               futures.add(future);
            }
        } catch (Exception e) {
            System.out.println("发生异常");
            e.printStackTrace();
        }

        System.out.println();

        for(Future<?> future:futures){
            try {
                future.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                System.out.println("发生了异常");
//                e.printStackTrace();
            }
        }

        threadPoolExecutor.shutdown();
    }


    private static class OccurExceptionTask implements Runnable {

        private int value;

        private OccurExceptionTask(int value) {
            this.value = value;
        }

        @Override
        public void run() {
            System.out.println("occur: " + value +" "+ Thread.currentThread().getName() + "  running...");
            System.out.println(value / 0);
        }
    }


}
