package com.chenxushao.java.thread.basis.method;

/**
 * @author CHENXUSHAO
 *  当线程在运行中执行了Thread类的yield()静态方法，如果此时具有相同优先级的其他线程处于就就绪状态，
 *  那么yield()方法将把当前运行的线程放到可运行池中并使另一个线程运行,如果没有相同优先级的可运行线程，则yield()方法什么都不做。
 *  让出cpu，不释放锁。
 */
public class YieldDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TestYieldThread t1 = new TestYieldThread("t1");
		TestYieldThread t2 = new TestYieldThread("t2");
		 t1.start();
		 t2.start();
		 try {
			t1.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}


class TestYieldThread extends Thread{
    public TestYieldThread(String name){
    	super(name);
    }
	public void run() {
		for (int i=0; i<=100; i++){
			System.out.println("I am "+this.getName()+": "+i);
			if(i%10 == 0){
				Thread.yield();
			}
		}
	}
}