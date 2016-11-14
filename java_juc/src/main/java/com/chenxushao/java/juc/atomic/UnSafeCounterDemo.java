package com.chenxushao.java.juc.atomic;

/**
 * Created by cuser on 16/7/24.
 */
public class UnSafeCounterDemo {


    private static UnSafeCounter unSafeCounter = new UnSafeCounter();

    public static void main(String[] args) throws InterruptedException {

        Thread t1 = new Thread(new Runnable(){
            public void run() {
                for(int i=0; i<100000*100; i++){
                    unSafeCounter.increment();;
                }
            }
        });

        Thread t2 = new Thread(new Runnable(){
            public void run() {
                for(int i=0; i<100000*100; i++){
                    unSafeCounter.increment();;
                }
            }
        });

        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println("end");
        System.out.println(unSafeCounter.getCount());

    }
}
