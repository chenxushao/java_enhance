package com.chenxushao.java.concurrent.basis.visibility;

public class NoVisibility {
    private static boolean ready;
    private static int number;

    private static class ReaderThread extends Thread {
        public void run() {
            //内部静态类可以直接使用外部类的静态域
            while (!ready){
                // 线程让步,使当前线程从执行状态（运行状态）变为可执行态（就绪状态）。
                // 就是说当一个线程使用了这个方法之后，它就会把自己CPU执行的时间让掉，
                // 让自己或者其它的线程运行。
                Thread.yield();
            }
            System.out.println(number);
        }
    }

    public static void main(String[] args) {
        new ReaderThread().start();
        //JVM可能对一些语句进行重排序
        number = 42;
        ready = true;
    }
}
