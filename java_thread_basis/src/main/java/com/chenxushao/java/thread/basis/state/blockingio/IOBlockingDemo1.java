package com.chenxushao.java.thread.basis.state.blockingio;

import java.io.IOException;

/**
 * IO Block等待输入时，线程状态依然是Runnable,可以通过jstack进行查看
 *
 * @author chenxushao
 */
public class IOBlockingDemo1 {

    public static void main(String[] args) throws IOException {

        System.out.println("pre read");

        int value = System.in.read();
        System.out.println("value: " + value);

        System.out.println("read finish");
    }
}
