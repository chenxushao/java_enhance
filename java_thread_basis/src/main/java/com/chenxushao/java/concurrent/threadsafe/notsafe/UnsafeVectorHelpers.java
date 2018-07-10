package com.chenxushao.java.concurrent.threadsafe.notsafe;

import java.util.Vector;


public class UnsafeVectorHelpers {

    //复合操作，先检查再运行，并不是线程安全的，
    public static Object getLast(Vector list) {
        int lastIndex = list.size() - 1;
        return list.get(lastIndex);
    }
}
