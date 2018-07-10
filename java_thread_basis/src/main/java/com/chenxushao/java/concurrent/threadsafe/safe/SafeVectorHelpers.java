package com.chenxushao.java.concurrent.threadsafe.safe;

import java.util.Vector;

/**
 * 线程安全
 */
public class SafeVectorHelpers {
    public static Object getLast(Vector list) {
        //额外的同步操作
        synchronized (list) {
            int lastIndex = list.size() - 1;
            return list.get(lastIndex);
        }
    }
}
