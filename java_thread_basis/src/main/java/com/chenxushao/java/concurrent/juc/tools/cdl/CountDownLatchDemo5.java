package com.chenxushao.java.concurrent.juc.tools.cdl;

import com.chenxushao.java.concurrent.util.ThreadUtil;
import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 在主线程中，开5个子线程来执行20个任务，只有等任务全部完成后，主线程才能再去做其它事.
 */
public class CountDownLatchDemo5 {

    private static ArrayBlockingQueue<String> blockingQueue;


    //用blockQueue中的元素模拟任务
    private static String getWork() {
        return blockingQueue.poll();
    }

    private static class WorkRunnable implements Runnable {
        private CountDownLatch latch;

        public WorkRunnable(CountDownLatch latch) {
            this.latch = latch;
        }

        public void run() {
            try {
                String work = getWork();
                performWork(work);
            } finally {
                latch.countDown();//完成一个任务就调用一次,确保latch一定会被调用
            }
        }
    }

    private static void performWork(String work) {
        System.out.println(Thread.currentThread().getName() + " 处理任务：" + work);
        ThreadUtil.sleep(60);
    }

    public static void main(String[] args) throws Exception {
        int threadNum = 5;//执行任务的子线程数量

        int workNum = 20;//任务数量

        ExecutorService service = Executors.newFixedThreadPool(threadNum, new ThreadFactoryBuilder().setNameFormat("WorkThread-%d").build());

        CountDownLatch latch = new CountDownLatch(workNum);//计数器的值为任务的数量

        //初始化任务
        blockingQueue = new ArrayBlockingQueue<>(workNum);
        for (int i = 0; i < workNum; i++) {
            blockingQueue.add("任务-" + i);
        }

        System.out.println("主线程开始运行");
        for (int i = 0; i < workNum; i++) {
            service.execute(new WorkRunnable(latch));
        }

        latch.await();//等待子线程的所有任务完成
        System.out.println("主线程去做其它事");
    }

}

