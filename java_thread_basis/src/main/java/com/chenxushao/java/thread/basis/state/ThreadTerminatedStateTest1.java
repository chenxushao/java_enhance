package com.chenxushao.java.thread.basis.state;

/**
 * @author chenxushao
 */
public class ThreadTerminatedStateTest1 {

    public static void main(String[] args) throws InterruptedException {

        Thread t = new Thread(new MyTask2(),"ttt");
        System.out.println(t.getName() + " state is " + t.getState());
        t.start();

        t.join();
        System.out.println(t.getName() + " state is " + t.getState());
    }
}

class MyTask2 implements Runnable {

    @Override
    public void run() {
        Thread t = Thread.currentThread();
        System.out.println(t.getName() + " state is  " + t.getState());
        try {
            Thread.sleep(1000 * 60);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}