package com.chenxushao.java.thread.basis.method;

/**
 * @author chenxushao@kingsoft.com
 */
public class Test3 {

    public static void main(String[] args) {


        Thread t1 = new Thread(new Task1(),"t1");
        Thread t2 = new Thread(new Task2(),"t2");
        Thread t3 = new Thread(new Task3(),"t3");
    }


}


class Task1 implements Runnable{

    @Override
    public void run(){

        System.out.println("1");
    }
}

class Task2 implements Runnable{

    private Thread t1;

    @Override
    public void run(){
        try {
            t1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("2");
    }
}

class Task3 implements Runnable{

    private Thread t1;

    private Thread t2;

    @Override
    public void run(){
        try{
        t1.join();
        t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("3");
    }
}


