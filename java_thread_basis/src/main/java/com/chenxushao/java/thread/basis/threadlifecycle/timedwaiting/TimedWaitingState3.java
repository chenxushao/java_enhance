package com.chenxushao.java.thread.basis.threadlifecycle.timedwaiting;

import com.chenxushao.java.thread.util.ThreadUtil;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * @author chenxushao
 *
 * 线程调用了parkNanos(long nanos)方法后，线程会进入WAITING状态
 *
 * 可通过jstack工具进行查看，一般显示为：java.lang.Thread.State: TIMED_WAITING (parking)
 */
public class TimedWaitingState3 {

    public static void main(String[] args) {
        Thread t = new Thread(new Task(), "ttt");
        t.start();


        ThreadUtil.sleep(2, TimeUnit.SECONDS);
        System.out.println("线程" + t.getName() + "调用LockSupport.parkNanos(long nanos)方法后，状态为: " + t.getState());

        ThreadUtil.sleep(30, TimeUnit.SECONDS);
        LockSupport.unpark(t);
        ThreadUtil.sleep(1, TimeUnit.SECONDS);
        System.out.println("线程" + t.getName() + "调用LockSupport.unpark方法后，状态为: " + t.getState());
    }

    private static class Task implements Runnable {

        @Override
        public void run() {
            System.out.println("running...");
            LockSupport.parkNanos(80000 * 10000000000L);
        }
    }


}
