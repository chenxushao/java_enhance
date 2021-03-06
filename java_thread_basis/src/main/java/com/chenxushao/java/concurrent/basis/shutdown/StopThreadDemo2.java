package com.chenxushao.java.concurrent.basis.shutdown;

import java.util.concurrent.TimeUnit;

public class StopThreadDemo2 {
    public static void main(String[] args) throws Exception {
        Task task = new Task();
        Thread t = new Thread(task, "t");
        t.start();

        TimeUnit.SECONDS.sleep(1);
        task.cancel();
    }

    private static class Task implements Runnable {

        //要使用volatile
        private volatile boolean on = true;

        @Override
        public void run() {
            long i = 1;
            while (on && !Thread.currentThread().isInterrupted()) {
                i++;
            }
            System.out.println("Count i = " + i);
        }

        public void cancel() {
            on = false;
        }
    }
}
