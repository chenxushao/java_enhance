package com.chenxushao.java.thread.basis;

import com.chenxushao.java.thread.util.ThreadUtil;

/**
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
