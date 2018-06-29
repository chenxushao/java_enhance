package com.chenxushao.java.concurrent.basis.daemon;

import com.chenxushao.java.concurrent.util.ThreadUtil;

/**
 * 当一个Java应用内，只有守护线程时，Java虚拟机就会自然退出。
 *
 * @author chenxushao
 */
public class DaemonThreadDemo {
    public static void main(String[] args) {
        Thread daemonThread = new Thread(new Task(),"daemonThread");
        daemonThread.setDaemon(true);
        daemonThread.start();

        ThreadUtil.sleep(5*1000);

    }

    private static class Task implements Runnable{

        @Override
        public void run(){

            while (true){
                System.out.println("Daemon Thred");
                ThreadUtil.sleep(1*1000);
            }
        }

    }

}
