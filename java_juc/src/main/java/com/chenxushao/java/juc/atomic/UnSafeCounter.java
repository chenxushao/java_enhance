package com.chenxushao.java.juc.atomic;
/**
 * Created by cuser on 16/7/24.
 */
public class UnSafeCounter {

    private  volatile int count = 0;

    public  void increment(){
       count++;
    }


    public int getCount(){
        return count;
    }

}
