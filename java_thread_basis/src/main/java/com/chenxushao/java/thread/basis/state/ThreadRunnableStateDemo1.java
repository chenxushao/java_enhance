package com.chenxushao.java.thread.basis.state;

/**
 * @author chenxushao
 */
public class ThreadRunnableStateDemo1 {

    public static void main(String[] args) {
        Thread t1 = new Thread(new MyTask(), "tt");
        t1.start();
    }
}



class MyTask extends Thread{

    @Override
    public void run(){
        while(true){
            System.out.println(Thread.currentThread().getName() + " running");
            try {
//                Thread.sleep(1);
            }catch (Exception e){

            }
        }
    }
}