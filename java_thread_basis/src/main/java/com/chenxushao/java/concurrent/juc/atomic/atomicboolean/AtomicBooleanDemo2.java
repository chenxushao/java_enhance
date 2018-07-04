package com.chenxushao.java.concurrent.juc.atomic.atomicboolean;

import com.chenxushao.java.concurrent.util.ThreadUtil;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * compareAndSet(boolean, boolean)  第一个参数为原始值，第二个参数为要修改的新值，若修改成功则返回true，否则返回false
 * getAndSet(boolean)   尝试设置新的boolean值，直到成功为止，返回设置前的数据
 * 运行结果说明：仅有一个输出：我成功了，说明只有一个线程尝试将boolean值修改成功，其他的都未成功，达到锁的效果
 */
public class AtomicBooleanDemo2 {

    public final static AtomicBoolean TEST_BOOLEAN = new AtomicBoolean();


    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(ThreadUtil.NCPUs * 2);
        for (int i = 1; i <= 100; i++) {
            executorService.execute(new Task());
        }

        executorService.shutdown();
    }

    private static class Task implements Runnable {

        @Override
        public void run() {
//            ThreadUtil.sleep(1, TimeUnit.SECONDS);
            if (TEST_BOOLEAN.compareAndSet(false, true)) {
                System.out.println("线程" + Thread.currentThread().getName() + " 变更AtomicBoolean成功了！");
            }
        }
    }
}