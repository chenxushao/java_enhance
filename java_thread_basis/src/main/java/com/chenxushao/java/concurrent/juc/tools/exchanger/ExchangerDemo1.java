package com.chenxushao.java.concurrent.juc.tools.exchanger;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Exchanger;

public class ExchangerDemo1 {
	static Exchanger<DataBuffer> exchanger = new Exchanger<DataBuffer>();
	static DataBuffer initialEmptyBuffer = new DataBuffer();
	static DataBuffer initialFullBuffer = new DataBuffer("ITEM");

	public static void main(String[] args) {
		class FillingLoop implements Runnable {
			int count = 0;

			@Override
			public void run() {
				DataBuffer currentBuffer = initialEmptyBuffer;
				try {
					while (true) {
						addToBuffer(currentBuffer);
						if (currentBuffer.isFull()) {
							System.out
									.println("filling loop others wants to exchange");
							currentBuffer = exchanger.exchange(currentBuffer);
							System.out
									.println("filling loop others observes an exchange");
						}
					}
				} catch (InterruptedException ie) {
					System.out.println("filling loop others interrupted");
				}
			}

			void addToBuffer(DataBuffer buffer) {
				String item = "NEWITEM" + count++;
				System.out.printf("Adding %s%n", item);
				buffer.add(item);
			}
		}

		class EmptyingLoop implements Runnable {
			@Override
			public void run() {
				DataBuffer currentBuffer = initialFullBuffer;
				try {
					while (true) {
						takeFromBuffer(currentBuffer);
						if (currentBuffer.isEmpty()) {
							System.out
									.println("emptying loop others wants to exchange");
							currentBuffer = exchanger.exchange(currentBuffer);
							System.out
									.println("emptying loop others observes an exchange");
						}
					}
				} catch (InterruptedException ie) {
					System.out.println("emptying loop others interrupted");
				}
			}

			void takeFromBuffer(DataBuffer buffer) {
				System.out.printf("taking %s%n", buffer.remove());
			}
		}

		new Thread(new EmptyingLoop()).start();
		new Thread(new FillingLoop()).start();
	}

	private static class DataBuffer {
		private final static int MAX = 10;

		private List<String> items = new ArrayList<>();

		DataBuffer() {
		}

		DataBuffer(String prefix) {
			for (int i = 0; i < MAX; i++) {
				String item = prefix + i;
				System.out.printf("Adding %s%n", item);
				items.add(item);
			}
		}

		void add(String s) {
			if (!isFull())
				items.add(s);
		}

		boolean isEmpty() {
			return items.size() == 0;
		}

		boolean isFull() {
			return items.size() == MAX;
		}

		String remove() {
			if (!isEmpty())
				return items.remove(0);
			return null;
		}
	}
}

