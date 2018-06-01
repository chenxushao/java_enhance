package com.chenxushao.java.thread.jstack;

/**
 * @author chenxushao
 */
public class SynchronizedTest1 {


    public synchronized void test1(){
        System.out.println("test1");
    }

    public void test2(){
        synchronized (this){
            System.out.println("test2");
        }
    }
}
