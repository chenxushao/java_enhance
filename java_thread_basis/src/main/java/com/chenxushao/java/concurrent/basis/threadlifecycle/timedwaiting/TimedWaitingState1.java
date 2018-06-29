package com.chenxushao.java.concurrent.basis.threadlifecycle.timedwaiting;

import com.chenxushao.java.concurrent.util.ThreadUtil;

import java.util.concurrent.TimeUnit;

/**
 * @author chenxushao
 * <p>
 * 调用sleep方法后，线程会进入TIMED_WAITING状态
 * 可通过jstack工具进行查看，显示为：  java.lang.Thread.State: TIMED_WAITING (sleeping)
 */
public class TimedWaitingState1 {

    public static void main(String[] args) {
        Thread t1 = new Thread(new Task(), "t");
        t1.start();

        ThreadUtil.sleep(5,TimeUnit.SECONDS);
        System.out.println("线程" + t1.getName() + "调用sleep方法后，状态为: " + t1.getState());

    }

    private static class Task implements Runnable {

        @Override
        public void run() {
            System.out.println("running...");
            ThreadUtil.sleep(30, TimeUnit.SECONDS);
        }
    }


}
