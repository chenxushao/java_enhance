package com.chenxushao.xstream;

import java.io.Writer;
import java.util.regex.Pattern;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.Xpp3Driver;

public class XStreamUtilbak {
	protected static String PREFIX_CDATA = "<![CDATA[";
	protected static String SUFFIX_CDATA = "]]>";

	/**
	 * 初始化XStream 可支持某一字段可以加入CDATA标签 如果需要某一字段使用原文
	 * 就需要在String类型的text的头加上"<![CDATA["和结尾处加上"]]>"标签， 以供XStream输出时进行识别
	 * 
	 * @param isAddCDATA
	 *            是否支持CDATA标签
	 * @return
	 */
	public static XStream initXStream(boolean isAddCDATA) {
		XStream xstream = null;
		if (isAddCDATA) {
			xstream = new XStream(new Xpp3Driver() {
				public HierarchicalStreamWriter createWriter(Writer out) {
					return new PrettyPrintWriter(out) {
						// 对所有xml节点的转换都增加CDATA标记
						boolean cdata = true;

						@SuppressWarnings("unchecked")
						public void startNode(String name, Class clazz) {
							super.startNode(name, clazz);
						}

						protected void writeText(QuickWriter writer, String text) {
							if (cdata) {
								writer.write(PREFIX_CDATA);
								writer.write(text);
								writer.write(SUFFIX_CDATA);
							} else {
								writer.write(text);
							}
						}
					};
				}
			});
		} else {
			xstream = new XStream();
		}
		return xstream;
	}

	/**
	 * 扩展xstream，使其支持CDATA块.专用于微信开发。使其支持CDATA，数字和浮点数不加CDATA，xml属性首字母大写
	 * 
	 * @return
	 */
	public static XStream initXStream4WeiXin() {
		XStream xstream = new XStream(new Xpp3Driver() {
			public HierarchicalStreamWriter createWriter(Writer out) {
				return new PrettyPrintWriter(out) {
					// 对那些xml节点的转换增加CDATA标记 true增加 false反之
					boolean cdata = false;

					@SuppressWarnings("unchecked")
					public void startNode(String name, Class clazz) {
						if (!name.equals("xml")) {
							char[] arr = name.toCharArray();
							if (arr[0] >= 'a' && arr[0] <= 'z') {
								// arr[0] -= 'a' - 'A';
								// ASCII码，大写字母和小写字符之间数值上差32
								arr[0] = (char) ((int) arr[0] - 32);
							}
							name = new String(arr);
						}
						super.startNode(name, clazz);
					}

					@Override
					public void setValue(String text) {
						if (text != null && !"".equals(text)) {
							// 浮点型判断
							Pattern patternInt = Pattern
									.compile("[0-9]*(\\.?)[0-9]*");
							// 整型判断
							Pattern patternFloat = Pattern.compile("[0-9]+");
							// 如果是整数或浮点数 就不要加[CDATA[]了
							if (patternInt.matcher(text).matches()
									|| patternFloat.matcher(text).matches()) {
								cdata = false;
							} else {
								cdata = true;
							}
						}
						super.setValue(text);
					}

					protected void writeText(QuickWriter writer, String text) {

						/*
						 * if (cdata) {
						 * 
						 * text = "<![CDATA["+text+"]]>"; }
						 * 
						 * 
						 * super.writeText(writer, text);
						 */
						if (cdata) {
							writer.write(PREFIX_CDATA);
							writer.write(text);
							writer.write(SUFFIX_CDATA);
						} else {
							writer.write(text);
						}
					}
				};
			}
		});
		return xstream;
	}
}
