package com.chenxushao.common.string;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * 功能：反射工具类
 * 
 * @author chenxushao@gmail.com
 */
public final class ReflectUtils {
	private static final String NAMESPACE_SEPARATOR = ".";

	public static final Class<Object> ROOT_CLASS = Object.class;// 所有java类的父亲

	public static final Class<?>[] NO_PARAMETERS = null;

	public static final Object[] NO_ARGUMENTS = null;// 无参常量

	private static final Method HASH_CODE_METHOD = lookupHashCodeMethod();

	private static final Map<Object, Long> ids = new WeakHashMap<Object, Long>();

	public static boolean DUMP_STATICS = false;

	private static long lastID;

	private ReflectUtils() {
	}

	// 获取某对象的某字段值
	public static Object getField(Object target, String fieldName) {
		Class<?> clazz = target.getClass();
		while (clazz != null) {
			try {
				Field field = clazz.getDeclaredField(fieldName);
				field.setAccessible(true);

				return field.get(target);

			} catch (NoSuchFieldException ex) {
				// AIRPlugin.logException(ex);
			} catch (IllegalAccessException ex) {
				// AIRPlugin.logException(ex);
			}
			clazz = clazz.getSuperclass();
		}
		return null;
	}

	// 获取某Class的某字段
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
			// throw new ImplementationError(ex);
			throw new RuntimeException("");
		}
	}

	/**
	 * Returns the getter method name from the property name.
	 * 
	 * @param propertyName
	 *            the property name
	 * @param isBool
	 *            <ul>
	 *            <li>true - returns isXxxx</li>
	 *            <li>false - returns getXxxx</li>
	 *            </ul>
	 * @return the getter method name
	 */
	public static String getGetterName(String propertyName, boolean isBool) {
		if (isBool) {
			return "is" + propertyName.substring(0, 1).toUpperCase()
					+ propertyName.substring(1);
		} else {
			return "get" + propertyName.substring(0, 1).toUpperCase()
					+ propertyName.substring(1);
		}
	}

	/**
	 * Returns the setter method name from the property name.
	 * 
	 * @param propertyName
	 *            the propery name
	 * @return the setter method name
	 */
	public static String getSetterName(String propertyName) {
		return "set" + propertyName.substring(0, 1).toUpperCase()
				+ propertyName.substring(1);
	}

	// 调用public无参数方法
	public static Object invokePublicMethod(Object o, String methodName) {
		try {
			Method m = o.getClass().getMethod(methodName, new Class[0]);
			m.setAccessible(true);
			return m.invoke(o, new Object[0]);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// 调用public有参数方法
	public static void invokePublicMethod(Object o, String methodName,
			Class[] classes, Object[] objects) {
		try {
			Method m = o.getClass().getMethod(methodName, classes);
			m.setAccessible(true);
			m.invoke(o, objects);
		} catch (Exception e) {
			e.printStackTrace();
		}

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

	// 调用某方法
	public static Object invokeMethod(Method method, Object target,
			Object... arguments) throws InvocationTargetException {
		if (!method.isAccessible()) {
			method.setAccessible(true);
		}

		try {
			return method.invoke(target, arguments);
		} catch (IllegalAccessException ex) {
			// throw new ImplementationError(ex);
			throw new RuntimeException("");
		}
	}

	// 收集一个Class的所有字段
	public static void collectFields(Class<?> c, List<Field> fields) {
		if (c == ROOT_CLASS) {
			return;
		}

		// Recurse
		collectFields(c.getSuperclass(), fields);

		try {
			Field[] declaredFields = c.getDeclaredFields();
			for (Field field : declaredFields) {
				if (field.isSynthetic()) {
					continue;
				}

				if ((field.getModifiers() & Modifier.STATIC) != 0
						&& !DUMP_STATICS) {
					continue;
				}

				if (field.getAnnotation(ExcludeFromDump.class) != null) {
					continue;
				}

				fields.add(field);
			}
		} catch (RuntimeException ex) {
			throw ex;
		} catch (Exception ex) {
			// throw new ImplementationError(ex);
		}
	}

	// 获得某个对象的某字段值
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

	// 设置某个对象的某字段值
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

	public static Integer getHashCode(Object object) {
		try {
			return (Integer) HASH_CODE_METHOD.invoke(object, NO_ARGUMENTS);
		} catch (Exception ex) {
			// IOUtil.print(ex);
		}

		return 0;
	}

	public static Long getID(Object object) {
		Long id = ids.get(object);
		if (id == null) {
			id = ++lastID;
			ids.put(object, id);
		}

		return id;
	}

	// 得到包名
	public static String getPackageName(Class<? extends Object> c) {
		if (c == null) {
			return null;
		}

		return getPackageName(c.getName());
	}

	// 根据类的完整名字，获取包名
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

	// 获取类名，不包括包名
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

	public static String getLabel(Object object) {
		if (object == null) {
			return null;
		}

		return object.getClass().getSimpleName() + "@" + getID(object); //$NON-NLS-1$
	}

	public static boolean isSetter(Method method) {
		return method.getParameterTypes().length == 1
				&& isSetterName(method.getName());
	}

	public static boolean isSetterName(String name) {
		return name.startsWith("set") && name.length() > 3
				&& Character.isUpperCase(name.charAt(3));
	}

	private static Method lookupHashCodeMethod() {
		Method method;

		try {
			method = ROOT_CLASS.getMethod("hashCode", NO_PARAMETERS); //$NON-NLS-1$
		} catch (Exception ex) {
			// This can really not happen
			throw new AssertionError();
		}

		if (!method.isAccessible()) {
			method.setAccessible(true);
		}
		return method;
	}

	/**
	 * @author Eike Stepper
	 */
	@Retention(RetentionPolicy.RUNTIME)
	@Target(ElementType.FIELD)
	public @interface ExcludeFromDump {
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

	/**
	 * collects recursivly all Fields for a certain class (originalClass) until
	 * a certain superClass is reached (stopClass)
	 * 
	 * @param originalClass
	 *            the Class to inspect
	 * @param stopClass
	 *            the superClass where to stop
	 * @param ignoreStaticAndFinalFields
	 *            determines if static and final fields should be ignored
	 * @return a List of all collected fields
	 * @see java.lang.reflect.Field 收集某类的字段，从子类收集到父类(sropClass)
	 */
	public static List<Field> collectAllFields(Class<?> originalClass,
			Class<?> stopClass, boolean ignoreStaticAndFinalFields,
			String[] ignoredMembers) {
		List<Field> fields = new ArrayList<Field>();
		Class<?> superClass = originalClass;

		// iterate through all superclasses and
		// collect all declared fields (public, private, protected)
		// until the stopClass is reached
		while (superClass != stopClass) {
			Field[] f = superClass.getDeclaredFields();
			if (ignoreStaticAndFinalFields) {
				for (Field element : f) {
					if (ignoreField(element))
						continue;
					else if (ignoreMembers(element, ignoredMembers))
						continue;
					else {
						Field field = element;
						// makes also private Fields accessible
						field.setAccessible(true);
						fields.add(field);
					}
				}
			} else {
				for (Field field : f) {
					if (ignoreMembers(field, ignoredMembers))
						continue;
					// makes also private Fields accessible
					field.setAccessible(true);
					fields.add(field);
				}
			}
			superClass = superClass.getSuperclass();
		}
		return fields;
	}

	/**
	 * Does the same as
	 * {@link #collectAllFields(Class, Class, boolean, String[])} but has
	 * java.lang.Object.class as stopClass and doesn't ignore any fields
	 * 
	 * @param originalClass
	 *            The Class for which all Fields should be collected
	 * @param ignoreStaticAndFinalFields
	 *            determines if static and final fields should be ignored
	 * @return a List of all declaredFields for the originalClass
	 * @see #collectAllFields(Class, Class, boolean, String[])
	 */
	public static List<Field> collectAllFields(Class<?> originalClass,
			boolean ignoreStaticAndFinalFields) {
		return collectAllFields(originalClass, Object.class,
				ignoreStaticAndFinalFields, null);
	}

	/**
	 * ignores all members/fields that are static or final. This method is used
	 * to check that.
	 * 
	 * @param field
	 *            Ignore this field?
	 * @return true if the field should be ignored, false if not.
	 */
	private static boolean ignoreField(Field field) {
		int modifiers = field.getModifiers();

		if ((modifiers & Modifier.STATIC) != 0)
			return true;

		if ((modifiers & Modifier.FINAL) != 0)
			return true;

		return false;
	}

	private static boolean ignoreMembers(Field field, String[] names) {
		if (names == null)
			return false;

		for (String name : names) {
			if (field.getName().equals(name))
				return true;
		}
		return false;
	}

	/**
	 * a generic equals-Method for all Classes based on java.lang.reflect
	 * 
	 * @param original
	 *            The original Instance which should be compared
	 * @param target
	 *            The target Instance which will be compared with the original
	 * @return true if the values for all Fields of both instances are
	 *         equivalent, otherwise returns false
	 * @see java.lang.reflect.Field
	 * @see #collectAllFields(Class originalClass, boolean ignoreFields)
	 */
	public static boolean equals(Object original, Object target) {
		if (original == target)
			return true;

		Class<?> originalClass = original.getClass();
		Class<?> targetClass = target.getClass();

		if (!originalClass.isAssignableFrom(targetClass))
			return false;

		List<Field> originalFields = collectAllFields(originalClass, true);
		List<Field> targetFields = collectAllFields(targetClass, true);

		if (originalFields.size() != targetFields.size())
			return false;

		for (int i = 0; i < originalFields.size(); ++i) {
			Field originalField = originalFields.get(i);
			Field targetField = targetFields.get(i);
			try {
				if (!originalField.get(original)
						.equals(targetField.get(target)))
					return false;
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}

		return true;
	}

	/**
	 * a generic toString()-Method which print outs all Fieldnames and the
	 * corresponding values for an arbitary instance
	 * 
	 * @param o
	 *            the target instance
	 * @param withSuperClasses
	 *            determines if the fields for all superclasses should be
	 *            displayed as well
	 * @return a String which contains the Classname and all Fieldnames with the
	 *         corresponding values
	 */
	public static String toString(Object o, boolean withSuperClasses) {
		Class<?> oClass = o.getClass();
		StringBuffer sb = new StringBuffer();
		// add Class Name
		sb.append(oClass.getName());
		sb.append("\n");
		if (withSuperClasses) {
			List<Field> fields = collectAllFields(oClass, false);
			for (Field field : fields) {
				try {
					sb.append(field.getName());
					sb.append(" = ");
					sb.append(String.valueOf(field.get(o)));
					sb.append("\n");
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		} else {
			Field[] fields = oClass.getDeclaredFields();
			for (Field field : fields) {
				field.setAccessible(true);
				try {
					sb.append(field.getName());
					sb.append(" = ");
					sb.append(String.valueOf(field.get(o)));
					sb.append("\n");
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
		return sb.toString();
	}

	public static interface IObjectFoundHandler {
		public void objectFound(String path, Object object);
	}

	private static class ObjectFoundResult {
		private boolean found = false;
	}

	/**
	 * Returns whether an object of the given class could be found in the graph
	 * of the given object.
	 * 
	 * @param checkObject
	 *            The object to scan
	 * @param clazz
	 *            The class to filter
	 * @param filter
	 *            Filter for the scanning (Whether the found classes should or
	 *            should not be assignable to the given clazz parameter)
	 * @return Whether an object of the given class could be found in the graph
	 *         of the given object.
	 */
	public static boolean findContainedObjectsByClass(Object checkObject,
			Class<?> clazz, boolean filter) {
		final ObjectFoundResult result = new ObjectFoundResult();
		findContainedObjectsByClass(checkObject, clazz, filter, true,
				new IObjectFoundHandler() {
					public void objectFound(String path, Object object) {
						result.found = true;
					}
				});
		return result.found;
	}

	/**
	 * Finds objects of the given class in the object graph of the given object.
	 * 
	 * @param checkObject
	 *            The object to scan
	 * @param clazz
	 *            The class to filter
	 * @param filter
	 *            Filter for the scanning (Whether the found classes should or
	 *            should not be assignable to the given clazz parameter)
	 * @param returnOnFirstInBranch
	 *            Whether to return after the first match in a branch
	 * @param objectFoundHandler
	 *            The handler to invoke when an object matches
	 */
	public static void findContainedObjectsByClass(Object checkObject,
			Class<?> clazz, boolean filter, boolean returnOnFirstInBranch,
			IObjectFoundHandler objectFoundHandler) {
		findContainedObjectsByClass(new HashSet<Object>(), "", checkObject,
				clazz, filter, returnOnFirstInBranch, objectFoundHandler);
	}

	private static void findContainedObjectsByClass(Set<Object> checked,
			String path, Object checkObject, Class<?> clazz, boolean filter,
			boolean returnOnFirstInBranch,
			IObjectFoundHandler objectFoundHandler) {
		if (checkObject == null)
			return;
		if (clazz.isAssignableFrom(checkObject.getClass()) == filter) {
			objectFoundHandler.objectFound(path, checkObject);
			if (returnOnFirstInBranch)
				return;
		}

		Object object = checkObject;
		Class<?> classRun = object.getClass();
		while (!classRun.equals(Object.class)) {

			Field[] fields = classRun.getDeclaredFields();
			for (Field field : fields) {
				field.setAccessible(true);
				try {
					Object o = field.get(object);
					if (o == null)
						continue;
					if (!checked.contains(o)) {
						checked.add(o);
						if (o instanceof Collection) {
							Collection<?> col = (Collection<?>) o;
							for (Object o2 : col) {
								if (o2 == null)
									continue;
								findContainedObjectsByClass(checked, path + "/"
										+ field.getName() + "$value$"
										+ o2.getClass().getName(), o2, clazz,
										filter, returnOnFirstInBranch,
										objectFoundHandler);
							}
						} else if (o instanceof Map) {
							Map<?, ?> map = (Map<?, ?>) o;
							for (Object o2 : map.keySet()) {
								if (o2 == null)
									continue;
								findContainedObjectsByClass(checked, path + "/"
										+ field.getName() + "$key$"
										+ o2.getClass().getName(), o2, clazz,
										filter, returnOnFirstInBranch,
										objectFoundHandler);
							}
							for (Object o2 : map.values()) {
								if (o2 == null)
									continue;
								findContainedObjectsByClass(checked, path + "/"
										+ field.getName() + "$value$"
										+ o2.getClass().getName(), o2, clazz,
										filter, returnOnFirstInBranch,
										objectFoundHandler);
							}
						} else {
							findContainedObjectsByClass(checked, path + "/"
									+ field.getName(), o, clazz, filter,
									returnOnFirstInBranch, objectFoundHandler);
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			classRun = classRun.getSuperclass();
		}
	}

	/**
	 * Lists all (and of course loads all) the classes in the given package. The
	 * given classloader will be used to list the resources in the package, but
	 * to load the class {@link Class#forName(String)} is used without
	 * specifying the classloader.
	 * 
	 * @param packageName
	 *            The package name to search classes for.
	 * @param cld
	 *            The classloader to use.
	 * @param recurse
	 *            Whether this method should recurse in sub-packages.
	 * @return A list of classes that exists within the given package.
	 * @throws ClassNotFoundException
	 *             If something went wrong.
	 */
	public static Collection<Class<?>> listClassesInPackage(String packageName,
			ClassLoader cld, boolean recurse) throws ClassNotFoundException {
		List<Class<?>> result = new LinkedList<Class<?>>();
		_listClassesOfPackage(packageName, cld, result, recurse);
		return result;
	}

	/**
	 * Lists all (and of course loads all) the classes in the given package. The
	 * {@link Thread#getContextClassLoader()} classloader will be used to list
	 * the resources in the package, but to load the class
	 * {@link Class#forName(String)} is used without specifying the classloader.
	 * 
	 * @param packageName
	 *            The package name to search classes for.
	 * @param recurse
	 *            Whether this method should recurse in sub-packages.
	 * @return A list of classes that exists within the given package.
	 * @throws ClassNotFoundException
	 *             If something went wrong.
	 */
	public static Collection<Class<?>> listClassesInPackage(String packageName,
			boolean recurse) throws ClassNotFoundException {
		List<Class<?>> result = new LinkedList<Class<?>>();
		ClassLoader cld = Thread.currentThread().getContextClassLoader();
		if (cld == null) {
			throw new ClassNotFoundException("Can't get class loader.");
		}
		_listClassesOfPackage(packageName, cld, result, recurse);
		return result;
	}

	/**
	 * Lists all (and of course loads all) the classes in the given package. The
	 * {@link Thread#getContextClassLoader()} classloader will be used to list
	 * the resources in the package, but to load the class
	 * {@link Class#forName(String)} is used without specifying the classloader.
	 * <p>
	 * Some of the code here was taken from:
	 * http://forum.java.sun.com/thread.jspa?threadID=341935&start=15
	 * </p>
	 * 
	 * @param packageName
	 *            The package name to search classes for.
	 * @param resultClasses
	 *            The colleciton where the (recursive) runs of this method
	 *            stores the found classes.
	 * @param recurse
	 *            Whether this method should recurse in sub-packages.
	 * @throws ClassNotFoundException
	 *             If something went wrong.
	 */
	protected static void _listClassesOfPackage(String packageName,
			ClassLoader cld, Collection<Class<?>> resultClasses, boolean recurse)
			throws ClassNotFoundException {
		// list all resource-entries for the given package
		List<File> pathEntries = new LinkedList<File>();
		List<JarFile> jarEntries = new LinkedList<JarFile>();
		String path = packageName.replace('.', '/') + '/';
		try {
			// Ask for all resources for the path
			Enumeration<URL> resources = cld.getResources(path);
			while (resources.hasMoreElements()) {
				URL resourceURL = resources.nextElement();
				if (resourceURL.getProtocol() == null
						|| resourceURL.getProtocol().equalsIgnoreCase("file")) {
					pathEntries.add(new File(URLDecoder.decode(
							resourceURL.getPath(), "UTF-8")));
				} else if (resourceURL.getProtocol().equalsIgnoreCase("jar")) {
					String jarPath = resourceURL.getPath();
					jarPath = jarPath.substring(0, jarPath.indexOf("!"));
					if (jarPath.startsWith("file:"))
						jarPath = jarPath.substring(5);
					jarEntries.add(new JarFile(jarPath));
				}
			}
		} catch (NullPointerException x) {
			throw new ClassNotFoundException(
					packageName
							+ " does not appear to be a valid package (Null pointer exception)");
		} catch (UnsupportedEncodingException encex) {
			throw new ClassNotFoundException(
					packageName
							+ " does not appear to be a valid package (Unsupported encoding)");
		} catch (IOException ioex) {
			throw new ClassNotFoundException(
					"IOException was thrown when trying to get all resources for "
							+ packageName, ioex);
		}

		// separate into classes and directories
		List<String> recursePackages = new LinkedList<String>();
		// For every directory identified capture all the .class files
		for (File directory : pathEntries) {
			if (directory.exists()) {
				// Get the list of the files contained in the package
				File[] files = directory.listFiles();
				for (File file : files) {
					if (file.isDirectory()) {
						recursePackages.add(packageName + "." + file.getName());
					} else {
						// we are only interested in .class files
						if (file.getName().endsWith(".class")) {
							// removes the .class extension
							resultClasses.add(Class.forName(packageName
									+ '.'
									+ file.getName().substring(0,
											file.getName().length() - 6)));
						}
					}
				}
			} else {
				throw new ClassNotFoundException(packageName + " ("
						+ directory.getPath()
						+ ") does not appear to be a valid package");
			}
		}
		for (JarFile jarFile : jarEntries) {
			for (Enumeration<JarEntry> e = jarFile.entries(); e
					.hasMoreElements();) {
				JarEntry entry = e.nextElement();
				if (!(entry.getName().length() >= path.length() && entry
						.getName().substring(0, path.length()).equals(path))) {
					continue;
				}
				if (entry.isDirectory()) {
					String suffix = entry.getName().substring(path.length());
					if ("".equals(suffix)) {
						continue;
					}
					if (suffix.endsWith("/")) {
						suffix = suffix.substring(0, suffix.length() - 1);
					}
					if (suffix.indexOf('/') < 0) {
						recursePackages.add(packageName + "." + suffix);
					}
				}
				if (entry.getName().endsWith(".class")
						&& entry.getName().indexOf("/", path.length()) < 0) {
					String cn = entry.getName().substring(0,
							entry.getName().length() - ".class".length());
					cn = cn.replaceAll("/", ".");
					resultClasses.add(Class.forName(cn));
				}
			}
		}
		// recurse into sub-packages
		if (recurse) {
			for (String pack : recursePackages) {
				_listClassesOfPackage(pack, cld, resultClasses, recurse);
			}
		}
	}

	/**
	 * Gets the field specified by its name. In contrast to
	 * {@link Class#getDeclaredField(String)}, this method steps up the class
	 * hierarchy and returns the first {@link Field} it finds. If there is no
	 * field in the complete hierarchy, the first {@link NoSuchFieldException}
	 * (caught from {@link Class#getDeclaredField(String)}) is re-thrown.
	 * 
	 * @param clazz
	 *            the class where to start searching for the field. Must
	 *            <b>not</b> be <code>null</code>.
	 * @param fieldName
	 *            the name of the field. Must <b>not</b> be <code>null</code>.
	 * @param fieldType
	 *            the type of the field. Can be <code>null</code>. In this case,
	 *            the first found will be returned - no matter what type it has.
	 * @return the first {@link Field} in the class hierarchy matching the
	 *         specified field name.
	 * @throws SecurityException
	 *             if {@link Class#getDeclaredField(String)} throws a
	 *             <code>SecurityException</code>.
	 * @throws NoSuchFieldException
	 *             if there is no field with the specified name in the complete
	 *             class hierarchy.
	 */
	public static Field getDeclaredField(Class<?> clazz, String fieldName,
			Class<?> fieldType) throws SecurityException, NoSuchFieldException {
		if (clazz == null)
			throw new IllegalArgumentException("clazz must not be null!");

		if (fieldName == null)
			throw new IllegalArgumentException("fieldName must not be null!");

		NoSuchFieldException firstNoSuchFieldException = null;

		Class<?> c = clazz;
		while (c != null) {
			try {
				Field f = c.getDeclaredField(fieldName);
				if (fieldType != null && f.getType() != fieldType)
					throw new NoSuchFieldException("The field \"" + fieldName
							+ "\" exists in class " + c.getName()
							+ " but the type does not match! Expected type is "
							+ fieldType.getName() + " but found "
							+ f.getType().getName() + "!");

				return f;
			} catch (NoSuchFieldException e) {
				if (firstNoSuchFieldException == null)
					firstNoSuchFieldException = e;
			}
			c = c.getSuperclass();
		}

		throw firstNoSuchFieldException;
	}

	/**
	 * Returns the list of the type-arguments the given class used to configure
	 * a parameterized superclass with.
	 * <p>
	 * This method will walk up the inheritance of the given class until it
	 * finds the first parameterized superclass or reaches Object.
	 * </p>
	 * <p>
	 * This method works only for real (not in-line) subclasses of parameterized
	 * classes and will return the types the subclass used to parameterize its
	 * superclass.
	 * </p>
	 * <p>
	 * If no parameterized superclass of the given class can be found in its
	 * inheritance, <code>null</code> will be returned.
	 * </p>
	 * <p>
	 * Examples:
	 * </p>
	 * <p>
	 * Given
	 * 
	 * <pre>
	 * class TestClass extends HashMap&lt;String, Object&gt; {
	 * }
	 * </pre>
	 * 
	 * The following code
	 * 
	 * <pre>
	 * getSuperClassTypeArguments(TestClass.class);
	 * </pre>
	 * 
	 * will return <code>[class java.lang.String, class java.lang.Object]</code>
	 * .
	 * </p>
	 * 
	 * @param clazz
	 *            The class to find the superclass-parameterization for.
	 * @return The list of types used to parameterize the first parameterized
	 *         super-class of the given class, or <code>null</code> if no
	 *         parameterized superclass can be found for the given class.
	 */
	public static List<Class<?>> getSuperClassTypeArguments(Class<?> clazz) {
		Class<?> checkClazz = clazz;
		while (checkClazz != Object.class && checkClazz != null) {
			Type genSuperclass = checkClazz.getGenericSuperclass();
			if (genSuperclass instanceof ParameterizedType) {
				ParameterizedType parameterizedType = (ParameterizedType) genSuperclass;
				Type[] typeArguments = parameterizedType
						.getActualTypeArguments();
				List<Class<?>> result = new ArrayList<Class<?>>(
						typeArguments.length);
				for (int j = 0; j < typeArguments.length; j++) {
					if (typeArguments[j] instanceof Class) {
						result.add((Class<?>) typeArguments[j]);
					}
				}
				return result;
			}
			checkClazz = checkClazz.getSuperclass();
		}
		return null;
	}

	/**
	 * 
	 * Collects the class-hierarchy (not including interfaces) for the given
	 * start-class until the given base-class is reached.
	 * 
	 * @param startClass
	 *            The class to start collecting the hierarchy for.
	 * @param baseClass
	 *            The class to stop collecting.
	 * @param includeBaseClass
	 *            Whether to include the base-class in the result.
	 * @return A list with the class-hierarchy of the given start-class.
	 */
	public static List<Class<?>> collectClassHierarchy(Class<?> startClass,
			Class<?> baseClass, boolean includeBaseClass) {
		Class<?> stopClass = baseClass;
		if (stopClass == null)
			stopClass = Object.class;

		List<Class<?>> result = new ArrayList<Class<?>>();
		Class<?> checkClass = startClass;
		while (checkClass != null && checkClass != stopClass) {
			result.add(checkClass);
			checkClass = checkClass.getSuperclass();
		}

		if (stopClass != Object.class && checkClass == Object.class)
			throw new IllegalStateException(
					"The given start class " + startClass + " was not a subclass of " + baseClass.getName()); //$NON-NLS-1$ //$NON-NLS-2$
		if (includeBaseClass) {
			result.add(stopClass);
		}
		Collections.reverse(result);
		return result;
	}

	/**
	 * Collects the type-hierarchy (including interfaces) for the given class.
	 * This method ensures that the type-hierarchy including interfaces is
	 * always in the same order.
	 * 
	 * @param clazz
	 *            The class to get the type hierarchy for.
	 * @return A list containing the type hierarchy of the given class
	 *         (containing the given class and all its direct and indirect
	 *         super-classes/interfaces)
	 */
	public static List<Class<?>> collectTypeHierarchy(Class<?> clazz) {
		List<Class<?>> classes = new ArrayList<Class<?>>();
		computeClassOrder(clazz, classes);
		return classes;
	}

	/**
	 * Internally used by {@link #collectClassHierarchy(Class, Class, boolean)}.
	 */
	private static void computeClassOrder(Class<?> clazz, List<Class<?>> classes) {
		Class<?> _clazz = clazz;
		Set<Class<?>> seen = new HashSet<Class<?>>();
		while (_clazz != null) {
			classes.add(_clazz);
			computeInterfaceOrder(_clazz.getInterfaces(), classes, seen);
			_clazz = _clazz.isInterface() ? Object.class : _clazz
					.getSuperclass();
		}
	}

	/**
	 * Internally used by {@link #collectClassHierarchy(Class, Class, boolean)}.
	 */
	private static void computeInterfaceOrder(Class<?>[] interfaces,
			Collection<Class<?>> classes, Set<Class<?>> seen) {
		List<Class<?>> newInterfaces = new ArrayList<Class<?>>(
				interfaces.length);
		for (int i = 0; i < interfaces.length; i++) {
			Class<?> iface = interfaces[i];
			if (seen.add(iface)) {
				// note we cannot recurse here without changing the resulting
				// interface order
				classes.add(iface);
				newInterfaces.add(iface);
			}
		}
		for (Class<?> newInterface : newInterfaces) {
			computeInterfaceOrder((newInterface).getInterfaces(), classes, seen);
		}
	}

}
