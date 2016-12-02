package mytest;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class ReflectDemo02 {

	public static void main(String[] args) {
		Constructor con = null;
		Class c = null;
		Object obj = null;
		Class[] cArray = null;
		User u = null;
		c = mytest.User.class;

		cArray = new Class[] { String.class, int.class };
		try {
			con = c.getConstructor(cArray);
			u = (User) con.newInstance("lisi", 24);
			System.out.println(u);
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
