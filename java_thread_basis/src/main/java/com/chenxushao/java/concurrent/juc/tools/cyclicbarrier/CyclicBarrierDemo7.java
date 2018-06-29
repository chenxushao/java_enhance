package com.chenxushao.java.concurrent.juc.tools.cyclicbarrier;

import com.chenxushao.java.concurrent.util.ThreadUtil;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

/**
 * CyclicBarrier可以循环使用，全部线程到达屏障后，会重新开始。
 *
 * @author chenxushao
 */
public class CyclicBarrierDemo7 {

    public static void main(String[] args) throws Exception {

        CyclicBarrier cyclicBarrier = new CyclicBarrier(7);
//        CyclicBarrier cyclicBarrier = newstate CyclicBarrier(10);
        System.out.println("init: " + cyclicBarrier.getNumberWaiting());


        Thread t1 = new Thread(new Task(cyclicBarrier),"t1");
        t1.start();

        System.out.println("-------");
        ThreadUtil.sleep(3, TimeUnit.SECONDS);
        System.out.println( cyclicBarrier.getNumberWaiting());

        Thread t2 = new Thread(new Task(cyclicBarrier), "t2");
        t2.start();

        System.out.println("-------");
        ThreadUtil.sleep(3, TimeUnit.SECONDS);
        System.out.println(cyclicBarrier.getNumberWaiting());


        Thread t3 = new Thread(new Task(cyclicBarrier), "t3");
        t3.start();

        System.out.println("-------");
        ThreadUtil.sleep(3, TimeUnit.SECONDS);
        System.out.println( cyclicBarrier.getNumberWaiting());


        Thread t4 = new Thread(new Task(cyclicBarrier), "t4");
        t4.start();

        System.out.println("-------");
        ThreadUtil.sleep(3, TimeUnit.SECONDS);
        System.out.println( cyclicBarrier.getNumberWaiting());

        Thread t5 = new Thread(new Task(cyclicBarrier), "t5");
        t5.start();

        System.out.println("-------");
        ThreadUtil.sleep(3, TimeUnit.SECONDS);
        System.out.println( cyclicBarrier.getNumberWaiting());

        Thread t6 = new Thread(new Task(cyclicBarrier), "t6");
        t6.start();

        System.out.println("-------");
        ThreadUtil.sleep(3, TimeUnit.SECONDS);
        System.out.println( cyclicBarrier.getNumberWaiting());

        Thread t7 = new Thread(new Task(cyclicBarrier), "t7");
        t7.start();

        System.out.println("-------");
        ThreadUtil.sleep(3, TimeUnit.SECONDS);
        System.out.println( cyclicBarrier.getNumberWaiting());

    }


    private static class Task implements Runnable{

        private CyclicBarrier cyclicBarrier;

        public Task(CyclicBarrier cyclicBarrier){
            this.cyclicBarrier = cyclicBarrier;
        }
        @Override
        public void run(){
            try {
                System.out.println(Thread.currentThread().getName() + "已到达第一站");
                cyclicBarrier.await();
                System.out.println(Thread.currentThread().getName() + "第一站处理完毕");

                ThreadUtil.sleep(3,TimeUnit.SECONDS);
                System.out.println(Thread.currentThread().getName() + "已到达第二站");
                cyclicBarrier.await();
                System.out.println(Thread.currentThread().getName() + "第二站处理完毕");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }
}
