package com.chenxushao.java.concurrent.basis.method.holdslock;

/**
 * @author chenxushao
 */
public class HoldsLockDemo2 {

    public static void main(String[] args) {
        Ids ids  = new Ids();
        ids.getId();
    }

    private static class Ids{

        private int i = 0;

        public synchronized int getId(){
            System.out.println(Thread.currentThread().getName() + ", getId... " + Thread.holdsLock(this));
            return i++;
        }

    }




}
