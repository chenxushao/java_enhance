package com.chenxushao.java.thread.basis.state.sleep;

import com.chenxushao.java.thread.util.ThreadUtil;

import java.util.concurrent.TimeUnit;

/**
 * @author chenxushao
 * 调用sleep方法后，线程会进入TIMED_WAITING状态
 */
public class SleepMethodInvokeTest2 {

    public static void main(String[] args) throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + " start");

        Thread t1 = new Thread(new Task1(), "t1");
        t1.start();

        Thread.sleep(1000*12);
        System.out.println(Thread.currentThread().getName() + " finish");
    }

    private static class Task1 implements Runnable {

        @Override
        public void run() {
            System.out.println("pre sleep");
            ThreadUtil.sleep(30, TimeUnit.SECONDS);
            System.out.println("sleep finish");

        }
    }


}
