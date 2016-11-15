package com.chenxushao.java.juc.executor;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolExecutorTest {

	public static void main(String[] args) {

		int corePoolSize = 3;
		int maximumPoolSize = 5;
		int keepAliveTime = 1;
		TimeUnit unit = TimeUnit.SECONDS;
		ArrayBlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<Runnable>(40);
		ThreadFactory threadFactory = Executors.defaultThreadFactory();
		RejectedExecutionHandler handler = new RejectedExecutionHandler() {
			@Override
			public void rejectedExecution(Runnable r,
					ThreadPoolExecutor executor) {
				if(r instanceof Task){
					Task task = (Task) r;
					System.out.println("进入拒绝策略：" + task.getTaskId());
				}else{
					System.out.println("???");
				}
			}
		};

		ThreadPoolExecutor executor = new ThreadPoolExecutor(corePoolSize,
				maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory,
				handler);
		for (int i = 1; i <= 40; i++) {
			executor.execute(new Task(i));
		}

	}

	private static class Task implements Runnable {
		
        private int taskId;
        
        public Task(int taskId){
        	this.taskId = taskId;
        }

        public int getTaskId() {
			return taskId;
		}

		@Override
		public void run() {
			System.out.println("任务运行：" + taskId);
		}
	}
}
