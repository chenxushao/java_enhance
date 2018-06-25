package com.chenxushao.java.thread.arithmetic.printabc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * 三个线程，分别打印a,b,c
 * 要求三个线程顺序打印打印十次a,b,c
 *
 * @author chenxushao
 *
 */
public class PrintABCDemo1 {

    public static void main(String[] args) {
        PrintABC printABC = new PrintABC();

        Thread t1 = new Thread(new Task1(printABC), "t1");
        Thread t2 = new Thread(new Task2(printABC), "t2");
        Thread t3 = new Thread(new Task3(printABC), "t3");
        t1.start();
        t2.start();
        t3.start();
    }

    private static class Task1 implements Runnable {

        private PrintABC printABC;

        public Task1(PrintABC printABC) {
            this.printABC = printABC;
        }

        @Override
        public void run() {
            try {
                printABC.printA();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static class Task2 implements Runnable {

        private PrintABC printABC;

        public Task2(PrintABC printABC) {
            this.printABC = printABC;
        }

        @Override
        public void run() {
            try {
                printABC.printB();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static class Task3 implements Runnable {


        private PrintABC printABC;

        public Task3(PrintABC printABC) {
            this.printABC = printABC;
        }

        @Override
        public void run() {
            try {
                printABC.printC();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    private static class PrintABC {

        private Lock lock = new ReentrantLock();

        Condition condition1 = lock.newCondition();

        Condition condition2 = lock.newCondition();

        Condition condition3 = lock.newCondition();

        private Integer num = 1;

        public void printA() throws InterruptedException {
            lock.lock();
            try {

                for (int i = 1; i <= 10; i++) {
                    if (num != 1) {
                        condition1.await();
                    }
                    System.out.println("a");
                    num = 2;
                    condition2.signal();
                }
            } finally {
                lock.unlock();
            }
        }

        public void printB() throws InterruptedException {
            lock.lock();
            try {
                for (int i = 1; i <= 10; i++) {
                    if (num != 2) {
                        condition2.await();
                    }
                    System.out.println("b");
                    num = 3;
                    condition3.signal();
                }
            } finally {
                lock.unlock();
            }
        }

        public void printC() throws InterruptedException {
            lock.lock();

            try {
                for (int i = 1; i <= 10; i++) {
                    if (num != 3) {
                        condition3.await();
                    }
                    System.out.println("c");
                    num = 1;
                    condition1.signal();
                }
            } finally {
                lock.unlock();
            }
        }
    }
}
