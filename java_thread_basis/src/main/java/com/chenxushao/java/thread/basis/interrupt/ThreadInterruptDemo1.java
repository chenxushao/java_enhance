package com.chenxushao.java.thread.basis.interrupt;

import com.chenxushao.java.thread.util.ThreadUtil;

/**
 *
 * 如果线程的代码实现本身没有响应中断，那么调用了中断将没有任何效果。
 * @author chenxushao
 *
 */
public class ThreadInterruptDemo1 {

    public static void main(String[] args) {

        Thread t = new Thread(new Task(),"t2");
        t.start();

        System.out.println("in main: " + t.getName() + " " + Thread.currentThread().isInterrupted());

//        Thread.interrupted();
        ThreadUtil.sleep(3*1000);
        System.out.println("hello");
        t.interrupt();
        System.out.println("world");

        System.out.println("finish: " + t.getName() + " " + Thread.currentThread().isInterrupted());


    }


    private static class Task implements Runnable{

        @Override
        public void run(){
            while(true) {
                System.out.println(Thread.currentThread().getName() + " " + Thread.currentThread().isInterrupted());

                try {
                    Thread.sleep(3*1000);
                } catch (InterruptedException e) {
                    System.out.println("execption " + Thread.currentThread().getName() + " " + Thread.currentThread().isInterrupted());

                }
            }
        }
    }
}
