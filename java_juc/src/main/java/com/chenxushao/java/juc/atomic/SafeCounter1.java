package com.chenxushao.java.juc.atomic;

/**
 * Created by cuser on 16/7/24.
 */
public class SafeCounter1 {

    private  volatile int count = 0;



    //若要线程安全的执行count++,需要synchronized加锁
    public synchronized void increment(){
       count++;
    }


    public int getCount(){
        return count;
    }

}
