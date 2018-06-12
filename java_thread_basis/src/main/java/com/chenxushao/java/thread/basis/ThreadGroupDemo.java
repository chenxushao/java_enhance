package com.chenxushao.java.thread.basis;

import com.chenxushao.java.thread.util.ThreadUtil;

/**
 * @author chenxushao@kingsoft.com
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
    }

    private static class Task implements Runnable{

        @Override
        public void run(){
            while (true) {
                System.out.println("Daemon Thred");
                ThreadUtil.sleep(1 * 1000);
            }
        }

    }

}
