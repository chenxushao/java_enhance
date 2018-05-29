package com.chenxushao.java.thread.jstack;

import java.io.IOException;

/**
 * @author chenxushao@kingsoft.com
 */
public class BlockingQueueTest1 {

    public static void main(String[] args) throws IOException {

        System.out.println("pre read");

        int value = System.in.read();
        System.out.println("value: " + value);

        System.out.println("read finish");
    }
}
