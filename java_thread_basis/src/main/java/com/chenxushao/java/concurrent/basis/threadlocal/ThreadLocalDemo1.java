package com.chenxushao.java.concurrent.basis.threadlocal;

/**
 * @author chenxushao
 */
public class ThreadLocalDemo1 {

//    private static ThreadLocal<String> threadLocal = new ThreadLocal<String>();
    private static ThreadLocal<String> threadLocal = new InheritableThreadLocal<String>();

    public static void main(String[] args) {
        threadLocal.set("abc");
        System.out.println(threadLocal.get());

        Thread t = new Thread(new Task(),"t");
        t.start();
    }


    private static class Task implements Runnable{

        @Override
        public void run(){

            System.out.println(Thread.currentThread().getName()+ ","  +threadLocal.get());
        }
    }

}
