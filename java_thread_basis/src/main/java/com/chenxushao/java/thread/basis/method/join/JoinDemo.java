package com.chenxushao.java.thread.basis.method.join;

/**
 * @author CHENXUSHAO join方法的功能就是使异步执行的线程变成同步执行
 */
public class JoinDemo {

    public static void main(String[] args) {
        Thread t1 = new Thread(new Task(), "t1");
        t1.start();
        try {
            t1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < 10; i++) {
            System.out.println("I am main Thread");
        }
    }


    private static class Task implements Runnable {

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                System.out.println("I am " + Thread.currentThread().getName());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }

}