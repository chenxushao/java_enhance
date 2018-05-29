package com.chenxushao.java.thread.jstack;

/**
 * @author chenxushao@kingsoft.com
 */
public class SleepTest1 {

    public static void main(String[] args) throws InterruptedException {

        System.out.println("pre sleep");
        Thread.sleep(1000*200);

        System.out.println("sleep finish");
    }
}
