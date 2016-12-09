package com.chenxushao.java.juc.bestpractice;



import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class MonitorThreadPoolExecutor   {

    private static int CORE_POOL_SIZE=24;
    private static int MAX_POOL_SIZE=64;
    private static int KEEP_ALIVE_TIME=60;
    private static int MAX_QUEUE_SIZE=30;

    //实例化线程池
    private static  ThreadPoolExecutor threadPool ;
    private static  ArrayBlockingQueue queue= new ArrayBlockingQueue(MAX_QUEUE_SIZE);

    static{
        threadPool=new ThreadPoolExecutor(
                CORE_POOL_SIZE,
                MAX_POOL_SIZE,
                KEEP_ALIVE_TIME, TimeUnit.SECONDS,
                queue,
                new SearchThreadFactory(),
                new AbortPolicyHandler());

    }


    public static ExecutorService create(){
        return threadPool;
    }

    private MonitorThreadPoolExecutor(){

    }


    public static void monitoryCoreInfo(){
//        MonitorUtil.recordOne("SEARCH_POOL_ACTIVE_COUNT",threadPool.getActiveCount());
//        MonitorUtil.recordOne("SEARCH_POOL_QUEUE_SIZE_COUNT",queue.size());
//        MonitorUtil.recordOne("SEARCH_POOL_SIZE_COUNT",threadPool.getPoolSize());
    }

    public static String queryMonitoryCoreInfo(){
        String msg = String.format(" [search-task-pool]    Pool Size: %d (active: %d, core: %d, max: %d, largest: %d), Task: %d (completed: %d), Queue size：%d Executor status:(isShutdown:%s, isTerminated:%s, isTerminating:%s)",
                new Object[]{
                        Integer.valueOf(threadPool.getPoolSize()),
                        Integer.valueOf(threadPool.getActiveCount()),
                        Integer.valueOf(threadPool.getCorePoolSize()),
                        Integer.valueOf(threadPool.getMaximumPoolSize()),
                        Integer.valueOf(threadPool.getLargestPoolSize()),
                        Long.valueOf(threadPool.getTaskCount()),
                Long.valueOf(threadPool.getCompletedTaskCount()),
                        queue.size(),
                Boolean.valueOf(threadPool.isShutdown()),
                Boolean.valueOf(threadPool.isTerminated()),
                Boolean.valueOf(threadPool.isTerminating())});
        return msg;
    }
}
