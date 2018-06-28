package com.chenxushao.java.juc.tools.cdl;

import com.chenxushao.java.juc.util.ThreadUtil;
import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 *public boolean await(long timeout, TimeUnit unit)
 * 这里有了时间限制，调用该方法的线程等到指定的timeout时间后，CountDownLatch的值是否countDown到0，都会继续往下执行
 */
public class CountDownLatchDemo6 {

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
        ThreadUtil.sleep(new Random().nextInt(10), TimeUnit.SECONDS);
    }

    public static void main(String[] args) throws Exception {
        ExecutorService service = Executors.newFixedThreadPool(5, new ThreadFactoryBuilder().setNameFormat("WorkThread-%d").build());

        CountDownLatch latch = new CountDownLatch(20);//计数器的值为任务的数量

        //初始化任务
        blockingQueue = new ArrayBlockingQueue<>(20);
        for (int i = 0; i < 20; i++) {
            blockingQueue.add("任务-" + i);
        }

        System.out.println("主线程开始运行");
        for (int i = 0; i < 20; i++) {
            service.execute(new WorkRunnable(latch));
        }

        //支持超时
        latch.await(2,TimeUnit.SECONDS);

        System.out.println("主线程去做其它事");
    }

}

