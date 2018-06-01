package com.chenxushao.java.thread.deadlock;

/**
 * @author chenxushao
 */
public class DeadLockTest1 {

    public static void main(String[] args) {
        Object o1 = new Object();
        Object o2 = new Object();
        Task1 task1 = new Task1(o1, o2);
        Thread t1 = new Thread(task1, "t1");
        t1.start();
        Thread t2 = new Thread(task1, "t2");
        t2.start();
    }


    private static class Task1 implements Runnable {

        private Object o1;

        private Object o2;

        public Task1(Object o1, Object o2) {
            this.o1 = o1;
            this.o2 = o2;
        }

        @Override
        public void run() {

            synchronized (o1) {
                try {
                    Thread.sleep(1000 * 2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " get o1");
                synchronized (o2) {
                    System.out.println(Thread.currentThread().getName() + " get o2");
                }
            }

            synchronized (o2) {
                try {
                    Thread.sleep(1000 * 2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " get o2");

                synchronized (o1) {
                    System.out.println(Thread.currentThread().getName() + " get o1");

                }
            }
        }
    }
}
