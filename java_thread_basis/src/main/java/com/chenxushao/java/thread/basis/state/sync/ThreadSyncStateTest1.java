package com.chenxushao.java.thread.basis.state.sync;

import com.chenxushao.java.thread.util.ThreadUtil;

import java.util.concurrent.TimeUnit;

/**
 * 在等待进入同步方法或同步代码块的（获得锁）的线程处于BLOCKED状态，通过jstack查看： java.lang.Thread.State: BLOCKED (on object monitor)
 *
 *
 * @author chenxushao
 */
public class ThreadSyncStateTest1 {

    public static void main(String[] args) {
        Container container = new Container();
        Thread t1 = new Thread(new MyTask(container), "t1");
        Thread t2 = new Thread(new MyTask(container), "t2");
        t1.start();
        t2.start();
    }

    private static class MyTask implements Runnable {

        private Container container;

        public MyTask(Container container) {
            this.container = container;
        }

        @Override
        public void run() {
            container.doSomething();
        }
    }


    private static class Container {

        public synchronized void doSomething() {
            System.out.println(Thread.currentThread().getName() + " running...");
            ThreadUtil.sleep(120, TimeUnit.SECONDS);
        }
    }
}
