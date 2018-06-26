package com.chenxushao.java.juc.tools.cdl;

import com.chenxushao.java.juc.util.ThreadUtil;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountdownLatchDemo3 {

    public static void main(String[] args) throws Exception {
        ExecutorService service = Executors.newCachedThreadPool();

        final CountDownLatch cdOrder = new CountDownLatch(1);
        final CountDownLatch cdAnswer = new CountDownLatch(3);

        for (int i = 0; i < 3; i++) {

            Runnable runnable = new Runnable() {
                public void run() {
                    try {
                        System.out.println("线程" + Thread.currentThread().getName() +
                            "正准备接受命令");
                        cdOrder.await();

                        System.out.println("线程" + Thread.currentThread().getName() + "已接受命令");
                        ThreadUtil.sleep(new Random().nextInt(10) * 1000);
                        System.out.println("线程" + Thread.currentThread().getName() + "回应命令处理结果");
                        cdAnswer.countDown();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            };

            service.execute(runnable);
        }

        ThreadUtil.sleep(new Random().nextInt(10) * 1000);

        System.out.println("线程" + Thread.currentThread().getName() + "即将发布命令");
        cdOrder.countDown();
        System.out.println("线程" + Thread.currentThread().getName() + "已发送命令，正在等待结果");

        cdAnswer.await();

        System.out.println("线程" + Thread.currentThread().getName() + "已收到所有响应结果");

        service.shutdown();

    }
}
