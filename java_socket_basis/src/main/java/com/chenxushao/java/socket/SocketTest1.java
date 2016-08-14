package com.chenxushao.java.socket;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class SocketTest1 {

	public static void main(String[] args) throws UnknownHostException, IOException {
		 Socket socket = new Socket(InetAddress.getLocalHost(),80);
		 System.out.println(socket.getLocalPort());
		 System.out.println(socket.getPort());
		 System.out.println(socket.getLocalAddress());
		 

	}

}
