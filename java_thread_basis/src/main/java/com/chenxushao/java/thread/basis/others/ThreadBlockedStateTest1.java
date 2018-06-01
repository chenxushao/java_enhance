package com.chenxushao.java.thread.basis.others;

/**
 * @author chenxushao
 */
public class ThreadBlockedStateTest1 {

    public synchronized void test1(){
        System.out.println("test1");
    }

    public void test2() throws InterruptedException {
        synchronized (this){
            System.out.println("test2");
            Thread.sleep(1000*60);
        }
    }


    public static void main(String[] args) throws InterruptedException {

        ThreadBlockedStateTest1 threadBlockedStateTest1 = new ThreadBlockedStateTest1();
        threadBlockedStateTest1.test2();
    }
}
