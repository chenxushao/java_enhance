package com.chenxushao.java.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpServer {

	/**
	 * @throws IOException
	 * @Title: main
	 * @Description:
	 * @param
	 * @return void
	 * @throws
	 */

	public static void main(String[] args) throws IOException {

		ServerSocket server = new ServerSocket(80);
		System.out.println("sss");
		while (true) {
			Socket socket = server.accept();

			BufferedReader br = new BufferedReader(new InputStreamReader(
					socket.getInputStream()));

			String line = "";
			while ((line = br.readLine()) != null)

				System.out.println(line);
		}

	}

}
