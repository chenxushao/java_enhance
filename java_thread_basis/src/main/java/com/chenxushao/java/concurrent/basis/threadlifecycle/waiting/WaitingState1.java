package com.chenxushao.java.concurrent.basis.threadlifecycle.waiting;

import com.chenxushao.java.concurrent.util.ThreadUtil;

import java.util.concurrent.TimeUnit;

/**
 * @author chenxushao
 * A线程调用了B线程的join方法后，A线程会进入WAITING状态
 *
 * 可通过jstack工具进行查看，一般显示为： java.lang.Thread.State: WAITING (on object monitor)
 */
public class WaitingState1 {

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new Task(), "t");
        t1.start();

        t1.join();
    }

    private static class Task implements Runnable {

        @Override
        public void run() {
            ThreadUtil.sleep(60, TimeUnit.SECONDS);
        }
    }


}
