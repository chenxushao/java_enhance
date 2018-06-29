package com.chenxushao.java.thread.basis.threadlifecycle.waiting;

import com.chenxushao.java.thread.util.ThreadUtil;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 等待获得ReentrantLock的线程处于WAITING状态
 *
 * 通过jstack查看,一般显示为：java.lang.Thread.State: WAITING (parking)
 *
 * @author chenxushao
 */
public class WaitingState4 {

    public static void main(String[] args) {
        Container container = new Container();
        Thread t1 = new Thread(new Task(container), "t1");
        Thread t2 = new Thread(new Task(container), "t2");
        t1.start();
        t2.start();
    }

    private static class Task implements Runnable {

        private Container container;

        public Task(Container container) {
            this.container = container;
        }

        @Override
        public void run() {
            container.doSomething();
        }
    }


    private static class Container {

        private Lock lock = new ReentrantLock();

        public void doSomething() {
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + " running...");
                ThreadUtil.sleep(120, TimeUnit.SECONDS);
            }finally {
                lock.unlock();
            }
        }
    }
}
