package com.chenxushao.java.thread.basis.state;

import java.io.IOException;

/**
 * @author chenxushao
 */
public class ThreadNewStateTest1 {

    public static void main(String[] args) throws IOException {

        Thread thread = new Thread(new MyTask1(),"newStateThread");
        System.out.println("thread的状态为：" + thread.getState());
        System.out.println(Thread.currentThread().getName() + " run...");

        int value = System.in.read();
        System.out.println("value: " + value);

    }
}


class MyTask1 implements Runnable{

    @Override
    public void run(){
        System.out.println(Thread.currentThread().getName() + " run...");
    }

}
