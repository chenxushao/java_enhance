package com.chenxushao.java.thread.basis.threadlifecycle.timedwaiting;

import com.chenxushao.java.thread.util.ThreadUtil;

import java.util.concurrent.TimeUnit;

/**
 * @author chenxushao
 * A线程调用了B线程的join(long millis)方法后，A线程会进入TIMED_WAITING状态
 *
 * 可通过jstack工具进行查看，一般显示为：java.lang.Thread.State: TIMED_WAITING (on object monitor)
 */
public class TimedWaitingState2 {

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new Task(), "t");
        t1.start();

        t1.join(30*1000);
    }

    private static class Task implements Runnable {

        @Override
        public void run() {
            ThreadUtil.sleep(60, TimeUnit.SECONDS);
        }
    }


}
