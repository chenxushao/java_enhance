package com.chenxushao.java.concurrent.basis.exceptions;

/**
 * 线程内部逻辑抛出异常，外层并不会捕获到异常。
 * @author chenxushao
 */
public class ThreadOccurException1 {

    public static void main(String[] args) {

        try {
            Thread t = new Thread(new Task(), "t");
            t.start();
        }catch (Exception e){
            System.out.println("发生异常");
        }
    }



    private static class Task implements Runnable{

        @Override
        public void run(){
            System.out.println(Thread.currentThread().getName() + "  running...");
            System.out.println(1/0);
        }
    }

}
