package com.chenxushao.java.thread.basis.state.locksupport;

import java.util.concurrent.locks.LockSupport;

/**
 * LockSupport方法根据是否有超时时间分别进入WAITING、TIMED_WAITING状态
 *
 * @author chenxushao
 */
public class LockSupportTest1 {

    public static void main(String[] args) {

        LockSupportTest1 lockSupportTest1 = new LockSupportTest1();
        System.out.println("pre");
        LockSupport.parkNanos(lockSupportTest1, Long.MAX_VALUE);
        System.out.println("finish");
    }



}
