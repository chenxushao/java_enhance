package com.chenxushao.java.thread.basis.threadinfo;

/**
 * @author chenxushao
 */
public class ThreadInfoDemo1 {

    public static void main(String[] args) {

        Thread t1 = new Thread(new Task1(), "t1");

        System.out.println("MAX_PRIORITY: " + Thread.MAX_PRIORITY);
        System.out.println("MIN_PRIORITY: "  + Thread.MIN_PRIORITY);
        System.out.println("NORM_PRIORITY: " + Thread.NORM_PRIORITY);

        System.out.println("class: " + t1.getClass());
        System.out.println("id:" + t1.getId());
        System.out.println("name:" + t1.getName());
        System.out.println("threadlifecycle:" + t1.getState());
        System.out.println("priority:" + t1.getPriority());
        System.out.println("isAlive:" + t1.isAlive());
        System.out.println("isDaemon:" + t1.isDaemon());
        System.out.println("isInterrupted:" + t1.isInterrupted());
        System.out.println("stackTrace:" + t1.getStackTrace());
        System.out.println("contextClassLoader:" + t1.getContextClassLoader());
        System.out.println("threadGroup:" + t1.getThreadGroup());
        System.out.println("uncaughtExceptionHandler:" + t1.getUncaughtExceptionHandler());
    }

    private static class Task1 implements Runnable {

        @Override
        public void run() {
            System.out.println();
        }
    }
}
