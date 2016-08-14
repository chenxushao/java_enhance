package com.chenxushao.common.reflection;

import java.io.PrintStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author chenxushao@gmail.com
 */
public final class ReflectUtils {

	private ReflectUtils() {
	}

	public static Method getMethod(Class<?> c, String methodName,
			Class<?>... parameterTypes) {
		try {
			try {
				return c.getDeclaredMethod(methodName, parameterTypes);
			} catch (NoSuchMethodException ex) {
				Class<?> superclass = c.getSuperclass();
				if (superclass != null) {
					return getMethod(superclass, methodName, parameterTypes);
				}

				return null;
			}
		} catch (RuntimeException ex) {
			throw ex;
		} catch (Exception ex) {
			// throw new ImplementationError(ex);
			throw new RuntimeException("");
		}
	}

	public static Object invokeMethod(Method method, Object target,
			Object... arguments) throws InvocationTargetException {
		if (!method.isAccessible()) {
			method.setAccessible(true);
		}
		try {
			return method.invoke(target, arguments);
		} catch (IllegalAccessException ex) {
			throw new RuntimeException("", ex);
		}
	}

	public static Field getField(Class<?> c, String fieldName) {
		try {
			try {
				return c.getDeclaredField(fieldName);
			} catch (NoSuchFieldException ex) {
				Class<?> superclass = c.getSuperclass();
				if (superclass != null) {
					return getField(superclass, fieldName);
				}
				return null;
			}
		} catch (RuntimeException ex) {
			throw ex;
		} catch (Exception ex) {
			throw new RuntimeException("", ex);
		}
	}

	public static Object getValue(Field field, Object target) {
		if (!field.isAccessible()) {
			field.setAccessible(true);
		}
		try {
			return field.get(target);
		} catch (IllegalAccessException ex) {
			// throw new ImplementationError(ex);
			throw new RuntimeException("");
		}
	}

	public static void setValue(Field field, Object target, Object value) {
		if (!field.isAccessible()) {
			field.setAccessible(true);
		}
		try {
			field.set(target, value);
		} catch (IllegalAccessException ex) {
			// throw new ImplementationError(ex);
		}
	}

	public static void printStackTrace(PrintStream out,
			StackTraceElement[] stackTrace) {
		for (int i = 2; i < stackTrace.length; i++) {
			StackTraceElement stackTraceElement = stackTrace[i];
			out.println("\tat " + stackTraceElement);
		}
	}

	public static void printStackTrace(StackTraceElement[] stackTrace) {
		printStackTrace(System.err, stackTrace);
	}

	public static String getPackageName(Class<? extends Object> c) {
		if (c == null) {
			return null;
		}
		return getPackageName(c.getName());
	}

	public static String getPackageName(String className) {
		if (className == null) {
			return null;
		}

		int lastDot = className.lastIndexOf('.');
		if (lastDot != -1) {
			className = className.substring(0, lastDot);
		}
		return className;
	}

	public static String getSimpleName(Class<? extends Object> c) {
		if (c == null) {
			return null;
		}
		return c.getSimpleName();
	}

	public static String getSimpleClassName(String name) {
		if (name == null) {
			return null;
		}
		int lastDot = name.lastIndexOf('.');
		if (lastDot != -1) {
			name = name.substring(lastDot + 1);
		}
		return name.replace('$', '.');
	}

	public static String getSimpleClassName(Object object) {
		if (object == null) {
			return null;
		}

		return getSimpleName(object.getClass());
	}

	public static boolean isSetter(Method method) {
		return method.getParameterTypes().length == 1
				&& isSetterName(method.getName());
	}

	public static boolean isSetterName(String name) {
		return name.startsWith("set") && name.length() > 3
				&& Character.isUpperCase(name.charAt(3));
	}

	/**
	 * Returns the declared method in the specified class or any of its super
	 * classes.
	 * 
	 * @param c
	 * @param methodName
	 * @param types
	 * @return
	 * @throws NoSuchMethodException
	 */
	public static Method getDeclaredMethod(Class<?> c, String methodName,
			Class<?>[] types) throws NoSuchMethodException {
		Method m = null;
		while (m == null && c != null) {
			try {
				m = c.getDeclaredMethod(methodName, types);
			} catch (SecurityException e) {
				throw new RuntimeException(e);
			} catch (NoSuchMethodException e) {
				// nop
			}
			c = c.getSuperclass();
		}
		if (m == null) {
			throw new NoSuchMethodException();
		}
		return m;
	}

	/**
	 * Returns the declared field in the specified class or any of its
	 * superclasses.
	 * 
	 * @param c
	 * @param fieldName
	 * @return
	 * @throws NoSuchFieldException
	 */
	public static Field getDeclaredField(Class<?> c, String fieldName)
			throws NoSuchFieldException {
		Field f = null;
		while (f == null && c != null) {
			try {
				f = c.getDeclaredField(fieldName);
			} catch (SecurityException e) {
				throw new RuntimeException(e);
			} catch (NoSuchFieldException e) {
				// nop
			}
			c = c.getSuperclass();
		}
		if (f == null) {
			throw new NoSuchFieldException();
		}
		return f;
	}

	/**
	 * Returns the value of a protected field in the specified object.
	 * 
	 * @param <T>
	 * @param fieldName
	 * @return
	 */
	public static <T extends Object> T getProtectedField(Object o,
			String fieldName) {
		try {
			Field f = getDeclaredField(o.getClass(), fieldName);
			f.setAccessible(true);
			return (T) f.get(o);
		} catch (SecurityException e) {
			throw new RuntimeException(e);
		} catch (NoSuchFieldException e) {
			throw new RuntimeException(e);
		} catch (IllegalArgumentException e) {
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Sets a protected field in the specified object.
	 * 
	 * @param o
	 * @param fieldName
	 * @param value
	 */
	public static void setProtectedField(Object o, String fieldName,
			Object value) {
		try {
			Field f = getDeclaredField(o.getClass(), fieldName);
			f.setAccessible(true);
			f.set(o, value);
		} catch (NoSuchFieldException e) {
			throw new RuntimeException(e);
		} catch (IllegalArgumentException e) {
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Invokes a protected method on the specified object.
	 * 
	 * @param <T>
	 * @param o
	 * @param methodName
	 * @return
	 */
	public static <T extends Object> T invokeProtectedMethod(Object o,
			String methodName) {
		return invokeProtectedMethod(o, null, methodName, null);
	}

	/**
	 * Invokes a protected method on the specified object.
	 * 
	 * @param <T>
	 * @param o
	 * @param args
	 * @param methodName
	 * @param types
	 * @return
	 */
	public static <T extends Object> T invokeProtectedMethod(Object o,
			Object[] args, String methodName, Class<?>[] types) {
		try {
			Method m = getDeclaredMethod(o.getClass(), methodName, types);
			m.setAccessible(true);
			return (T) m.invoke(o, args);
		} catch (SecurityException e) {
			throw new RuntimeException(e);
		} catch (NoSuchMethodException e) {
			throw new RuntimeException(e);
		} catch (IllegalArgumentException e) {
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		} catch (InvocationTargetException e) {
			throw new RuntimeException(e);
		}
	}

	// 获取某个对象的私有属性，设置class参数是为了使用父类
	public static Object getPrivateProperty(Object obj, String propertyName) {
		return getPrivateProperty(obj, propertyName, obj.getClass());
	}

	public static Object getPrivateProperty(Object obj, String propertyName,
			Class clazz) {
		try {
			Field declaredField = clazz.getDeclaredField(propertyName);
			declaredField.setAccessible(true);// 设置私有属性可访问
			return declaredField.get(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
