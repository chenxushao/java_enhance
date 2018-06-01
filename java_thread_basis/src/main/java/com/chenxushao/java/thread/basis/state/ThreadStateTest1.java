package com.chenxushao.java.thread.basis.state;

/**
 * @author chenxushao
 */
public class ThreadStateTest1 {

    public static void main(String[] args) throws Exception {

        Thread t1 = new Thread(new Task1(), "t1");

        System.out.println("线程" + t1.getName() + "状态:" + t1.getState());
        t1.start();

        System.out.println("线程" + t1.getName() + "状态:" + t1.getState());

        Thread.sleep(3 * 1000);
        System.out.println("线程" + t1.getName() + "状态:" + t1.getState());


        Thread.sleep(20 * 1000);
        System.out.println("线程" + t1.getName() + "状态:" + t1.getState());
    }


    private static class Task1 implements Runnable {

        @Override
        public void run() {
            try {
                Thread.sleep(1000 * 20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Thread currentThread = Thread.currentThread();
            System.out.println(currentThread.getName() + " running...");

        }
    }

}
