package com.chenxushao.java.thread.basis.state;

import java.io.IOException;
import java.util.Date;

/**
 * IO Block等待输入时，线程状态依然是Runnable
 */
public class IOBlockedTest {

    public static void main(String[] args) {
        Thread t = new Thread(new Task(), "t");
        System.out.println(new Date() + "线程t的状态" + t.getState());
        t.start();

        for (int i = 0; i < 10; i++) {
            System.out.println(new Date() + "线程t的状态" + t.getState() + "----"
                + i);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) { //
			     e.printStackTrace();
            }

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

