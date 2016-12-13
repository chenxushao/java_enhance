package com.chenxushao.java.juc.bestpractice;


import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ThreadPoolExecutor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AbortPolicyHandler extends ThreadPoolExecutor.AbortPolicy {
    private static final Logger logger = LoggerFactory.getLogger(AbortPolicyHandler.class);

    public AbortPolicyHandler(){
    }

    public void rejectedExecution(Runnable r, ThreadPoolExecutor e) {
//        MonitorUtil.recordOne("SEARCH_REJECTED_TASK");
        String msg = String.format(" [SEARCH_REJECTED_TASK]  Thread pool is EXHAUSTED!  Pool Size: %d (active: %d, core: %d, max: %d, largest: %d), Task: %d (completed: %d), Executor status:(isShutdown:%s, isTerminated:%s, isTerminating:%s)", new Object[]{ Integer.valueOf(e.getPoolSize()), Integer.valueOf(e.getActiveCount()), Integer.valueOf(e.getCorePoolSize()), Integer.valueOf(e.getMaximumPoolSize()), Integer.valueOf(e.getLargestPoolSize()), Long.valueOf(e.getTaskCount()), Long.valueOf(e.getCompletedTaskCount()), Boolean.valueOf(e.isShutdown()), Boolean.valueOf(e.isTerminated()), Boolean.valueOf(e.isTerminating())});
        logger.warn(msg);
        throw new RejectedExecutionException(msg);
    }
}

