package com.chenxushao.java.thread.concurrent;

/**
 * @author chenxushao@kingsoft.com
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
