package com.chenxushao.java.concurrent.basis.threadgroup;

import com.chenxushao.java.concurrent.util.ThreadUtil;

/**
 * @author chenxushao
 */
public class ThreadGroupDemo {

    public static void main(String[] args) {

        ThreadGroup tg = new ThreadGroup("cxstgs");
        Thread t1 = new Thread(tg,new Task(),"t1");
        Thread t2 = new Thread(tg,new Task(),"t2");

        t1.start();
        t2.start();
        tg.list();

        System.out.println(tg.activeCount());

        System.out.println(tg.getParent());
        System.out.println(tg.getParent().getParent());
        System.out.println(tg.getParent().getParent().getParent());
    }

    private static class Task implements Runnable{

        @Override
        public void run(){
            while (true) {
                System.out.println("test...");
                ThreadUtil.sleep(1 * 1000);
            }
        }

    }

}
