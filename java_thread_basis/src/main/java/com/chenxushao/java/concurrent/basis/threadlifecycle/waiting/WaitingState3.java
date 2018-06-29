package com.chenxushao.java.concurrent.basis.threadlifecycle.waiting;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 *
 * 阻塞队列等待时，线程状态为WAITING.
 * 通过jstack，查看： java.lang.Thread.State: WAITING (parking)
 * @author chenxushao
 */
public class WaitingState3 {

    public static void main(String[] args) throws InterruptedException {

        BlockingQueue<String> queue = new ArrayBlockingQueue<String>(5);
        String value = queue.take();

        System.out.println("value: " + value);

    }
}
