package com.chenxushao.java.thread.basis.state.sleep;

import com.chenxushao.java.thread.util.ThreadUtil;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class SleepMethodInvokeTest4 {

    public static void main(String[] args) {
        Thread t = new Thread(new Task(), "t");
        t.start();

        for (int i = 0; i < 30; i++) {
            System.out.println(new Date() + "线程t的状态" + t.getState() + "----"
                + i);
            ThreadUtil.sleep(2, TimeUnit.SECONDS);
        }
    }


    private static class Task implements Runnable {

        @Override
        public void run() {
            while (true) {
                System.out.println("run");
                ThreadUtil.sleep(10, TimeUnit.SECONDS);
            }
        }

    }
}

