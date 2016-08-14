package com.chenxushao.java.jvm.oom;

/*
 * 方法栈和本地方法栈内存溢出 异常测试
 * 在Sun Jdk中，并不区分方法栈和本地方法栈。
 * 栈容量由-Xss指定。
 * VM参数:-Xss128K
 * 
 * 这里实际上是一个单线程程序。实验结果表明：在单个线程下，无论是由于栈桢太大，还是虚拟机栈容量太小，当内存无法分配
 * 的时候，虚拟机抛出的都是StackOverflowError异常。
 * 在多线程环境下，有时可产生内存溢出(OutOfMemoryError)异常[查看示例JavaVMStackOutOfMemoryError.java]。但是，这样产生的内存溢出异常与栈空间是否足够大并不存在存在任何联系，或者准确
 * 地说，在这种情况下，给每个线程分配的栈内存越大，反而越容易产生内存溢出异常。
 * 原因，其实并不难理解，操作系统分配给每个进程的内存是有限制的，譬如32位的Windows限制为2GB。虚拟机提供了参数来控制
 * Java堆(Heap)和方法的这两部分内存的最大值。剩余的为2GB(操作系统限制)减去Xmx(最大堆容量)，再减去MaxPermSize(最大方法区容量)，
 * 程序计数器消耗内存很小，可以忽略掉。如果虚拟机进程本身耗费的内存不计算在内，剩下的内存就由虚拟机栈和本地方法栈"瓜分"了。每个
 * 线程分配到的栈容量越大，可以建立的线程数量自然就越少，建立线程时就越容易将剩下的内存耗尽，这时候自然就抛出OutOfMemoryError异常了。
 * 
 * 使用虚拟机的默认参数，栈深度在大多数情况下达到1000-2000完全没有问题，对于正常的方法调用(包括递归)，这个深度应该完全够用了。但是如果是
 * 因为建立过多线程而导致的内存溢出，在不能减少线程数或者更换64位虚拟机的情况下，就只能通过减少最大堆(-Xmx)和减少栈容量(-Xss)来换取更多
 * 的线程。如果没有这方面的经验，这种通过"减少内存"的手段来解决内存溢出的方式会比较难以想到。
 * 
 */
public class VMStackOverflowError {
	private int length = 0;

	/**
	 * @param args
	 * @throws Throwable 
	 */
	public static void main(String[] args) throws Throwable {
         VMStackOverflowError vse = new VMStackOverflowError();
         try{
         vse.loop();
         }catch(Throwable e){
        	 System.out.println("stack length: " + vse.length);
        	 throw e;
         }
		
	}

	
	private  void loop(){
		 length ++;
		 loop();
	}
	
	
}
