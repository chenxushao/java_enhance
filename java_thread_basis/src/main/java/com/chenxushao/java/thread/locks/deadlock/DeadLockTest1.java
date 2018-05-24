package com.chenxushao.java.thread.locks.deadlock;

/**
 * @author chenxushao
 */
public class DeadLockTest1 {


    public static void main(String[] args) {

        Object o1 = new Object();
        Object o2 = new Object();

        Thread t1 = new Thread(new MyThread1(o1,o2),"t1");
        Thread t2 = new Thread(new MyThread2(o1,o2),"t2");
        t1.start();
        t2.start();

    }
}





class MyThread1 implements Runnable {

    private Object o1;

    private Object o2;

    public MyThread1(Object o1, Object o2) {
        this.o1 = o1;
        this.o2 = o2;
    }

    @Override
    public void run() {
        synchronized (o1) {
            System.out.println(Thread.currentThread().getName() + " ,get o1");

            try {
                Thread.sleep(2000);
            } catch (Exception e) {
                e.printStackTrace();
            }

            synchronized (o2) {
                System.out.println(Thread.currentThread().getName() + " get o2");
            }
        }
    }
}

class MyThread2 implements Runnable {

    private Object o1;

    private Object o2;

    public MyThread2(Object o1, Object o2) {
        this.o1 = o1;
        this.o2 = o2;
    }

    @Override
    public void run() {
        synchronized (o2) {
            System.out.println(Thread.currentThread().getName() + " ,get o2");

            try {
                Thread.sleep(2000);
            } catch (Exception e) {
                e.printStackTrace();
            }

            synchronized (o1) {
                System.out.println(Thread.currentThread().getName() + " get o1");
            }
        }
    }
}