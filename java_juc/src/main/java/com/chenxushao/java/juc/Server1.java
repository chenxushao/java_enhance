package com.chenxushao.java.juc;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

class Server1 {
	public static void main(String[] args) throws IOException {
		ServerSocket socket = new ServerSocket(9000);
		while (true) {
			final Socket s = socket.accept();
			Runnable r = new Runnable() {
				@Override
				public void run() {
					doWork(s);
				}
			};
			new Thread(r).start();
		}
	}

	static void doWork(Socket s) {
	}
}