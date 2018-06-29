package com.chenxushao.java.thread.basis.threadlifecycle.runnable;

/**
 * 运行状态（RUNNABLE)
 *
 * @author chenxushao
 */
public class RunnableState1 {


    public static void main(String[] args) {
        Thread t = new Thread(new Task(), "t");
        t.start();
        System.out.println("---" + t.getName()+"的状态为："+t.getState());
    }


    private static class Task implements Runnable {

        @Override
        public void run() {
            Thread currentThread = Thread.currentThread();
            for(int i=0; i<50; i++){
                System.out.println(currentThread.getName() + "的状态为：" + currentThread.getState());
            }
        }
    }
}
