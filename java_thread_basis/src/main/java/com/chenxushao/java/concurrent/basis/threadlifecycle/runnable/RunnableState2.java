package com.chenxushao.java.concurrent.basis.threadlifecycle.runnable;

import com.chenxushao.java.concurrent.util.ThreadUtil;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * IO Block等待输入时，线程状态依然是RUNABLE
 */
public class RunnableState2 {

    public static void main(String[] args) {
        Thread t1 = new Thread(new Task(), "t1");
        t1.start();

        ThreadUtil.sleep(5, TimeUnit.SECONDS);
        System.out.println("处于阻塞IO状态的线程" + t1.getName() + "的状态为：" + t1.getState());
    }

    private static class Task implements Runnable {

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " State is : "
                + Thread.currentThread().getState());
            System.out.println("请输入字符：");
            try {
                System.in.read();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

}

