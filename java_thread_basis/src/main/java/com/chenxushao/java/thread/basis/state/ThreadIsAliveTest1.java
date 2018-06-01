package com.chenxushao.java.thread.basis.state;

/**
 * 线程是否活着.
 *
 * @author CHENXUSHAO
 */
public class ThreadIsAliveTest1 {

    public static void main(String[] args) throws Exception {
        Thread t1 = new Thread(new Task1(), "t1");
        System.out.println("t1:" + t1.isAlive());
        t1.start();


        System.out.println("t1:" + t1.isAlive());


        t1.join();

        System.out.println("t1:" + t1.isAlive());
    }

    private static class Task1 implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 2; i++) {
                System.out.println(i);
                try {
                    Thread.sleep(1000 * 2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
