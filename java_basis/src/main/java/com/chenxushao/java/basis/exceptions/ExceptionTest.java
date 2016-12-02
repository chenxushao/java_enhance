package com.chenxushao.java.basis.exceptions;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class ExceptionTest {

	/**
	 * @param args
	 * @throws IOException
	 * @throws SecurityException
	 */
	public static void main(String[] args) throws SecurityException,
			IOException {

		Logger logger = Logger.getLogger(ExceptionTest.class.getName());
		Handler handler = new FileHandler("log.txt", true);
		handler.setFormatter(new SimpleFormatter());
		logger.addHandler(handler);
		// logger.setLevel(Level.WARNING);
		// logger.setUseParentHandlers(false);
		// OutputStream ops = new FileOutputStream("log.txt");

		// FileHandler fileHandler = new FileHandler();
		// fileHandler.setFormatter(new SimpleFormatter());
		try {
			int x = 10 / 0;
		} catch (ArithmeticException e) {
			// log.warning(msge.printStackTrace(System.out);
			logger.info(e.toString());// 输出信息
		}
	}
}
