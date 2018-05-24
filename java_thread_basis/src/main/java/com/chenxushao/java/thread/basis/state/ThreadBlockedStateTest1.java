package com.chenxushao.java.thread.basis.state;

/**
 * @author chenxushao
 */
public class ThreadBlockedStateTest1 {


    public synchronized void test1(){
        System.out.println("test1");
    }

    public void test2(){
        synchronized (this){
            System.out.println("test2");
        }
    }
}
