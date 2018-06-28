package com.chenxushao.java.thread.basis.method.holdslock;

/**
 * @author chenxushao
 */
public class HoldsLockDemo1 {

    public static void main(String[] args) {
        HoldsLockDemo1 holdsLockDemo1 = new HoldsLockDemo1();
        System.out.println(Thread.holdsLock(holdsLockDemo1));
    }
}
