package com.chenxushao.java.thread.basis.method.isalive;

import com.chenxushao.java.thread.util.ThreadUtil;

import java.util.concurrent.TimeUnit;

/**
 * 线程是否活着.
 *
 * @author CHENXUSHAO
 */
public class ThreadIsAliveTest1 {

    public static void main(String[] args) throws Exception {
        Thread t1 = new Thread(new Task1(), "t1");
        System.out.println("t1:" + t1.isAlive());
        t1.start();


        System.out.println("t1:" + t1.isAlive());


        t1.join();
        System.out.println("t1:" + t1.isAlive());

        ThreadUtil.sleep(15,TimeUnit.SECONDS);
        System.out.println("t1:" + t1.isAlive());
    }

    private static class Task1 implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 2; i++) {
                ThreadUtil.sleep(2, TimeUnit.SECONDS);
            }
        }
    }
}
