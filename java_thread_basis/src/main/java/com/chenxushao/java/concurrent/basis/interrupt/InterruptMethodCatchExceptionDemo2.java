package com.chenxushao.java.concurrent.basis.interrupt;

/**
 *
 * 如果线程的代码实现本身没有响应中断，那么调用了中断将没有任何效果。
 * 调用interrupt方法后,isInterrupted返回false,线程结束后，会返回false.
 * 许多声明抛出InterruptedException的方法(如Thread.sleep)在抛出InterruptedException之前，Java虚拟机会先将该线程的中断标识位清除，然后抛出InterruptedException，此时调用isInterrupted方法将会返回false。
 * @author chenxushao
 *
 */
public class InterruptMethodCatchExceptionDemo2 {

    public static void main(String[] args) throws Exception{

        Thread t = new Thread(new Task(),"t１");
        System.out.println("before start: " + t.getName() + " " + t.isInterrupted());

        t.start();

        System.out.println("after start: " + t.getName() + " " + t.isInterrupted());

        t.interrupt();

        System.out.println("after interrupt: " + t.getName() + " " + t.isInterrupted());
        t.join();
        System.out.println("after others terminated: " + t.getName() + " " + t.isInterrupted());


    }


    private static class Task implements Runnable{

        @Override
        public void run(){
            for(int i=1;i<=10; i++) {
                System.out.println("in running: " + Thread.currentThread().getName() + " " + Thread.currentThread().isInterrupted());

                try {
                    Thread.sleep(2*1000);
                } catch (InterruptedException e) {
                    System.out.println("catch execption " + Thread.currentThread().getName() + " " + Thread.currentThread().isInterrupted());

                }
            }
        }
    }
}
