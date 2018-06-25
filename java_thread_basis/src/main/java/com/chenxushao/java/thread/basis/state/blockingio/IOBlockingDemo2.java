package com.chenxushao.java.thread.basis.state.blockingio;

import com.chenxushao.java.thread.util.ThreadUtil;

import java.io.IOException;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * IO Block等待输入时，线程状态依然是Runnable
 */
public class IOBlockingDemo2 {

    public static void main(String[] args) {
        Thread t1 = new Thread(new Task(), "t1");
        System.out.println(new Date() + "线程t1的状态" + t1.getState());
        t1.start();

        for (int i = 0; i < 10; i++) {
            System.out.println(new Date() + "线程t的状态" + t1.getState() + "----"
                + i);
            ThreadUtil.sleep(5, TimeUnit.SECONDS);
        }

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

            System.out.println(Thread.currentThread().getName() + " State is : "
                + Thread.currentThread().getState());

        }

    }

}

