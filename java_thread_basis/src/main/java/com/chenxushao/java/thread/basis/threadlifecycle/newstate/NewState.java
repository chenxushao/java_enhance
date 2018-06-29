package com.chenxushao.java.thread.basis.threadlifecycle.newstate;

/**
 * 新建状态(NEW)
 *
 * @author chenxushao
 */
public class NewState {

    public static void main(String[] args) {
        Thread t = new Thread(new Task(), "t");
        System.out.println("线程创建后(newstate), " + t.getName() + "的状态:" + t.getState());
    }


    private static class Task implements Runnable {

        @Override
        public void run() {
            System.out.println("running...");
        }
    }

}
