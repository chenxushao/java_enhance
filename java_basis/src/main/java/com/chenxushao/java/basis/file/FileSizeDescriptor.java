package com.chenxushao.java.basis.file;

import java.io.File;
import java.text.NumberFormat;

public class FileSizeDescriptor {

	private double fileSize = 0.0;
	public static final String[] FILE_SIZE_UNIT = { "Byte", "KB", "MB", "GB",
			"TB", "PB" };

	private String formatSize(double size) {
		// 数字格式化类
		NumberFormat numberFormat = NumberFormat.getInstance();
		numberFormat.setMaximumFractionDigits(2);// 设置数的小数部分的最大位数

		String[] unit = FILE_SIZE_UNIT;
		int i = 0;
		for (; i < unit.length; i++) {
			if ((long) (size / 1024) > 0) {
				size = size / 1024;
			} else {
				break;
			}
		}
		return numberFormat.format(size) + unit[i];

	}

	private String getFileSize(File file) {

		if (file.isFile()) {

			fileSize += file.length();

		}
		if (file.isDirectory()) {
			for (File tempFile : file.listFiles()) {
				getFileSize(tempFile);
			}
		}
		return formatSize(fileSize);
	}

	public String getFileSizeTemp(File file) {
		String tempSize = getFileSize(file);
		setFileSize(0.0);
		return tempSize;
	}

	private void setFileSize(double fileSize) {
		this.fileSize = fileSize;
	}
}
