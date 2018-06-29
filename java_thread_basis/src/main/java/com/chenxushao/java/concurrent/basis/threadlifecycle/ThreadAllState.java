package com.chenxushao.java.concurrent.basis.threadlifecycle;

import com.chenxushao.java.concurrent.util.ThreadUtil;

import java.util.concurrent.TimeUnit;

/**
 * @author chenxushao
 */
public class ThreadAllState {

    public static void main(String[] args) throws Exception {

        Thread t1 = new Thread(new Task1(), "t1");

        System.out.println("线程创建后(newstate)," + t1.getName() + "状态:" + t1.getState());
        t1.start();

        System.out.println("线程调用start方法后，" + t1.getName() + "状态:" + t1.getState());

        Thread.sleep(3 * 1000);
        System.out.println("线程调用sleep后，" + t1.getName() + "状态:" + t1.getState());


        Thread.sleep(20 * 1000);
        System.out.println("线程执行完毕后，" + t1.getName() + "状态:" + t1.getState());
    }


    private static class Task1 implements Runnable {

        @Override
        public void run() {
            ThreadUtil.sleep(15, TimeUnit.SECONDS);
            Thread currentThread = Thread.currentThread();
            System.out.println("线程在运行时，"+ currentThread.getName() + "状态:" + currentThread.getState());

        }
    }

}
