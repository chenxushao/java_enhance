package com.chenxushao.java.juc.collection.queue.blocking;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BlockingQueueTest1{ 
        public static void main(String[] args) throws InterruptedException { 
                BlockingQueue<String> bqueue = new ArrayBlockingQueue<String>(20); 
                for (int i = 0; i < 30; i++) { 
                        //将指定元素添加到此队列中 
                        bqueue.put("加入元素" + i); 
                        System.out.println("向阻塞队列中添加了元素:" + i); 
                } 
                System.out.println("程序到此运行结束，即将退出----"); 
        } 
}