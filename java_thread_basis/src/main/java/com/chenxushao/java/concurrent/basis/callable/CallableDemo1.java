package com.chenxushao.java.concurrent.basis.callable;

import com.chenxushao.java.concurrent.util.ThreadUtil;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class CallableDemo1 {

    public static void main(String[] args) throws InterruptedException, ExecutionException, TimeoutException {
        ExecutorService executor = Executors.newSingleThreadExecutor();

        long begin = System.currentTimeMillis();

        Future<String> future = executor.submit(new CallableTask());
        String result = future.get();
        System.out.println("result: " + result);

        System.out.println((System.currentTimeMillis() - begin) / 1000);
    }


    /**
     * 实现java.util.jstack.Callable接口，有返回值的任务
     */
    private static class CallableTask implements Callable<String> {

        @Override
        public String call() throws Exception {
            ThreadUtil.sleep(10, TimeUnit.SECONDS);
            return "hello";
        }
    }
}
