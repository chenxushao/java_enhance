package com.chenxushao.java.thread.basis.threadlifecycle.waiting;

import com.chenxushao.java.thread.util.ThreadUtil;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * @author chenxushao
 *
 * 线程调用了LockSupport.park()方法后，线程会进入WAITING状态
 *
 * 可通过jstack工具进行查看，一般显示为： java.lang.Thread.State: WAITING (parking)
 */
public class WaitingState2 {

    public static void main(String[] args) {
        Thread t1 = new Thread(new Task(), "t");
        t1.start();

        ThreadUtil.sleep(5, TimeUnit.SECONDS);
        System.out.println("线程" + t1.getName() + "调用LockSupport.park()方法后，状态为: " + t1.getState());

        LockSupport.unpark(t1);
        ThreadUtil.sleep(5, TimeUnit.SECONDS);
        System.out.println("线程" + t1.getName() + "调用LockSupport.unpark()方法后，状态为: " + t1.getState());
    }

    private static class Task implements Runnable {

        @Override
        public void run() {
            System.out.println("running...");
            LockSupport.park();
        }
    }


}
