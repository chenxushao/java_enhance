package com.chenxushao.java.concurrent.basis;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

/**
 * @author chenxushao
 */
public class MultiThread {

    public static void main(String[] args) {

        //获取Java线程管理MxBean
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();

        //不需要获取同步的monitor和synchronizer信息，仅获取线程和线程堆栈信息
        ThreadInfo[] threadInfos = threadMXBean.dumpAllThreads(false,false);
        for(ThreadInfo threadInfo:threadInfos){
            System.out.println("[" + threadInfo.getThreadId()+"] " + threadInfo.getThreadName() +" " +  threadInfo.getThreadState());
        }
    }
}
