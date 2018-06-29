package com.chenxushao.xstream;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.chenxushao.clazz.DefaultClassScanner;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.converters.SingleValueConverter;
import com.thoughtworks.xstream.converters.basic.DateConverter;
import com.thoughtworks.xstream.converters.reflection.SunUnsafeReflectionProvider;
import com.thoughtworks.xstream.io.xml.CompactWriter;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class DefaultXStream {
	// The XStream instance is others-safe
	private static XStream xstream = new XStream();

	static {
		xstream.autodetectAnnotations(true);
		DefaultClassScanner scanner = new DefaultClassScanner();
		// 自定义的注解类和路径
		// List<Class<?>> clazzList =
		// scanner.getClassListByAnnotation("cn.com.gome",XStreamMapper.class);
		List<Class<?>> clazzList = null;
		// xstream.processAnnotations((Class[]) clazzList.toArray());
		System.out.println(clazzList);
		for (Class clazz : clazzList) {
			xstream.processAnnotations(clazz);
		}
	}

	public static void writeXML(Object object, File file) throws IOException {
		FileOutputStream out = new FileOutputStream(file);
		try {
			xstream.toXML(object, out);
			out.flush();
		} finally {
			out.close();
		}
	}

	public static String writeXML(Object object) {
		return xstream.toXML(object);
	}

	public static <T> T readXML(File file) throws IOException {
		FileInputStream in = new FileInputStream(file);
		try {
			return (T) xstream.fromXML(in);
		} finally {
			in.close();
		}
	}

	public static String toXML(Object obj) {
		return xstream.toXML(obj);
	}

	public static String toXmlOptimized(Object toSerialize) {
		StringWriter sw = new StringWriter();
		xstream.marshal(toSerialize, new CompactWriter(sw));
		return sw.toString();
	}

	public static String toXML(Object obj,
			List<SingleValueConverter> converterList) {
		for (SingleValueConverter converter : converterList) {
			xstream.registerConverter(converter);
		}
		return xstream.toXML(obj);
	}

	public static String toXMLDateSerializer(Object obj, String dateFormat) {
		xstream.registerConverter(new DateConverter(dateFormat, new String[] {}));
		return xstream.toXML(obj);
	}

	public static <T> T fromXMLDateDerializer(String xml, String dateFormat) {
		xstream.registerConverter(new DateConverter(dateFormat, new String[] {}));
		return (T) xstream.fromXML(xml);
	}

	public static <T> T fromXML(String xml) {
		return (T) xstream.fromXML(xml);
	}

	public static <T> T fromXML(InputStream in) {
		return (T) xstream.fromXML(in);
	}

	public static String toString(Object o) {
		StringWriter writer = new StringWriter();
		XStream xs = new XStream(new SunUnsafeReflectionProvider());
		xs.processAnnotations(o.getClass());
		xs.toXML(o, writer);
		return writer.toString();
	}

	/**
	 * @param map
	 * @return eg :
	 *         <map><entry><string>b</string><string>2</string></entry></map>
	 */
	public static String serializeMap(Map<String, String> map) {
		if (map == null) {
			map = new HashMap<String, String>();
		}
		XStream xstream = new XStream(new DomDriver());
		return xstream.toXML(map);
	}

	/**
	 * @param map
	 *            eg :
	 *            <map><entry><string>b</string><string>2</string></entry></map>
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, String> deserializeMap(String map) {
		if (map == null || map.equals("")) {
			return new HashMap<String, String>();
		} else {
			XStream xstream = new XStream(new DomDriver());
			return (Map<String, String>) xstream.fromXML(map);
		}
	}

	/**
	 * @return eg:<cow><string>a</string><string>b</string></cow>
	 */
	public static String serializeList(List<?> list) {
		if (list == null) {
			list = new ArrayList<String>();
		}
		XStream xstream = new XStream(new DomDriver());
		return xstream.toXML(list);
	}

	/**
	 * @param list
	 *            eg:<cow><string>a</string><string>b</string></cow>
	 */
	@SuppressWarnings("unchecked")
	public static List<String> deserializeList(String list) {
		if (list == null || list.equals("")) {
			return new ArrayList<String>();
		}
		XStream xstream = new XStream(new DomDriver());
		return (List<String>) xstream.fromXML(list);
	}

	/**
	 * @param set
	 *            of Strings
	 * @return serialized Set eg:<set><string>a</string><string>b</string></set>
	 */
	public static String serializeSet(Set<?> set) {
		if (set == null) {
			set = new HashSet<String>();
		}
		XStream xstream = new XStream(new DomDriver());
		return xstream.toXML(set);
	}

	/**
	 * @param set
	 *            serialized as a String
	 *            eg:<set><string>a</string><string>b</string></set>
	 * @return deserialized Set
	 */
	public static Set<String> deserializeSet(String set) {
		if (set == null || set.equals("")) {
			return new HashSet<String>();
		}
		XStream xstream = new XStream(new DomDriver());
		return (Set<String>) xstream.fromXML(set);
	}
}