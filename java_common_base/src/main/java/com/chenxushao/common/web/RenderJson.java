package com.chenxushao.common.web;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.chenxushao.common.json.GsonUtils;

public class RenderJson {

	private static final Logger logger = LoggerFactory
			.getLogger(RenderJson.class);

	/**
	 * 将数据以 JSON 格式写入响应中
	 */
	public static void writeJSON(HttpServletResponse response, Object data) {
		try {
			// 设置响应头
			response.setContentType("application/json"); // 指定内容类型为 JSON 格式
			response.setCharacterEncoding(FrameworkConstant.UTF_8); // 防止中文乱码
			// 向响应中写入数据
			PrintWriter writer = response.getWriter();
			writer.write(GsonUtils.object2Json(data)); // 转为 JSON 字符串
			writer.flush();
			writer.close();
		} catch (Exception e) {
			logger.error("在响应中写数据出错！", e);
			throw new RuntimeException(e);
		}
	}
}
