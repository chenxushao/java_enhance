package com.chenxushao.java.gc;

/*
 * Minor GC 
 */
public class MinorGCDemo {

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		MemoryObject mmo = new MemoryObject(1024 * 1024);
		happenMinorGC(11);

	}

	private static void happenMinorGC(int happenMinorGCIndex) throws Exception {
		for (int i = 0; i < happenMinorGCIndex; i++) {
			if (i == happenMinorGCIndex - 1) {
				Thread.sleep(200);
				System.out.println("minor gc should happen");
			}
			new MemoryObject(1024 * 1024);

		}
	}

}

class MemoryObject {
	private byte[] bytes;

	public MemoryObject(int objectSize) {
		this.bytes = new byte[objectSize];
	}
}