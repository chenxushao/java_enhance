package com.chenxushao.java.thread.basis.interrupt;

import com.chenxushao.java.thread.util.ThreadUtil;

/**
 * @author chenxushao
 */
public class ThreadSleepInterruptDemo1 {

    public static void main(String[] args) {

        Thread t = new Thread(new Task(),"t1");
        t.start();

        System.out.println(t.getState());

        ThreadUtil.sleep(5*1000);
        t.interrupt();

//        System.out.println(t.getState());


    }


    private static class Task implements Runnable{

        @Override
        public void run(){
            while(true) {
                System.out.println(Thread.currentThread().getName() + " running...");
                try {
                    Thread.sleep(1000*1000);
                } catch (InterruptedException e) {
                   Thread.currentThread().interrupt();
                }
            }
        }
    }
}
