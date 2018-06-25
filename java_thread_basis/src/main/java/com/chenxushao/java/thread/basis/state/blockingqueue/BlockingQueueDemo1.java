package com.chenxushao.java.thread.basis.state.blockingqueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 *
 * 阻塞队列等待时，线程状态为WAITING.
 * 通过jstack，查看： java.lang.Thread.State: WAITING (parking)
 * @author chenxushao
 */
public class BlockingQueueDemo1 {

    public static void main(String[] args) throws InterruptedException {

        System.out.println("pre");

        BlockingQueue<String> queue = new ArrayBlockingQueue<String>(5);
        String value = queue.take();
        System.out.println("value: " + value);

        System.out.println("finish");
    }
}
