package com.chenxushao.java.thread.basis.state.sleep;

import com.chenxushao.java.thread.util.ThreadUtil;

import java.util.concurrent.TimeUnit;

/**
 * @author chenxushao
 * 调用sleep方法后，线程会进入TIMED_WAITING状态
 * 可通过jstack工具进行查看，显示为：  java.lang.Thread.State: TIMED_WAITING (sleeping)
 */
public class SleepMethodInvokeTest1 {

    public static void main(String[] args) {
        System.out.println("pre sleep");
        ThreadUtil.sleep(200, TimeUnit.SECONDS);
        System.out.println("sleep finish");
    }
}
