package com.chenxushao.java.thread.jstack;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @author chenxushao
 */
public class IOBlockingTest1 {

    public static void main(String[] args) throws InterruptedException {

        System.out.println("pre");

        BlockingQueue<String> queue = new ArrayBlockingQueue<String>(5);
        String value = queue.take();
        System.out.println("value: " + value);

        System.out.println("finish");
    }
}
