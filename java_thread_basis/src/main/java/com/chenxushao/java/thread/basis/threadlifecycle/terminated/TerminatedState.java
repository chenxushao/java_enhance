package com.chenxushao.java.thread.basis.threadlifecycle.terminated;

import com.chenxushao.java.thread.util.ThreadUtil;

import java.util.concurrent.TimeUnit;

/**
 * 终止状态(TERMINATED)
 * @author chenxushao
 */
public class TerminatedState {

    public static void main(String[] args) throws Exception {
        Thread t1 = new Thread(new Task1(), "t1");
        t1.start();
        //主线束休眠10s
        ThreadUtil.sleep(10,TimeUnit.SECONDS);
        System.out.println("线程执行完毕后，" + t1.getName() + "状态:" + t1.getState());
    }


    private static class Task1 implements Runnable {

        @Override
        public void run() {
            ThreadUtil.sleep(2, TimeUnit.SECONDS);
            Thread currentThread = Thread.currentThread();
            System.out.println("线程在运行时，"+ currentThread.getName() + "状态:" + currentThread.getState());

        }
    }

}
