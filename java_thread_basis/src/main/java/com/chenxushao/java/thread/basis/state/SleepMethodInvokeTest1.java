package com.chenxushao.java.thread.basis.state;

/**
 * @author chenxushao
 * 调用sleep方法后，线程会进入TIMED_WAITING状态
 */
public class SleepMethodInvokeTest1 {

    public static void main(String[] args) throws InterruptedException {

        System.out.println("pre sleep");
        Thread.sleep(1000*200);
        System.out.println("sleep finish");
    }
}
