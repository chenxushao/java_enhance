package mytest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class ReflectDemo {

	public static void main(String[] args) {
		Class cDemo = null;
		Class[] cArray = null;
		Class cTemp = null;
		String className = "";
		BufferedReader brd = null;
		System.out.println("������������");
		try {

			brd = new BufferedReader(new InputStreamReader(System.in));
			className = brd.readLine();
			if (className.equals("")) {
				System.out.println("���������Ϊ��.");
				return;
			}

			// cDemo=Class.forName("mytest."+className);
			cDemo = Class.forName(className);
			System.out.println("mytest." + className);
			int mod = cDemo.getModifiers();
			System.out.println("\t���η���" + Modifier.toString(mod));
			System.out.println("\t������" + cDemo.getPackage());
			System.out.println("\t����: " + cDemo.getName());
			System.out.println("\t���ࣺ" + cDemo.getSuperclass());
			System.out.print("\t��ʵ�ֵĽӿڣ�");

			cArray = cDemo.getInterfaces();
			if (cArray.length == 0) {
				System.out.println("��");
			} else {
				for (int i = 0; i < cArray.length; i++) {
					cTemp = cArray[i];
					if (i != 0)
						System.out.print(",");
					System.out.print(cTemp.toString());
				}
			}
			System.out.println();
			Field[] fds = cDemo.getDeclaredFields();

			System.out.println("\n" + "----------------------" + className
					+ "�������������ֶΣ�" + "----------------------");

			for (int i = 0; i < fds.length; i++) {
				if (i != 0)
					System.out.println("----------------------"
							+ "----------------------"
							+ "----------------------");
				System.out.println(fds[i].toString());
				System.out.println("\t��������" + fds[i].getName());
				System.out.println("\t���η���" + Modifier.toString(mod));
				System.out.println("\t�������ͣ�" + fds[i].getType());

			}

			Method[] mds = cDemo.getDeclaredMethods();
			System.out.println("\n" + "----------------------" + className
					+ "�����������·�����" + "----------------------");

			for (int i = 0; i < mds.length; i++) {
				if (i != 0)
					System.out.println("----------------------"
							+ "----------------------"
							+ "----------------------");
				System.out.println(mds[i].toString());
				System.out.println("\t��������" + mds[i].getName());
				System.out.println("\t���η���"
						+ Modifier.toString(mds[i].getModifiers()));
				System.out.println("\t����ֵ���ͣ�" + mds[i].getReturnType());
				System.out.print("\t�β��б�");
				cArray = mds[i].getParameterTypes();
				if (cArray.length == 0)
					System.out.println("��");
				else {
					for (int j = 0; j < cArray.length; j++) {
						cTemp = cArray[j];
						if (j != 0)
							System.out.print(",");
						System.out.print(cTemp.toString());
					}
					System.out.println();
				}

			}

			System.out.println("\n" + "----------------------" + className
					+ "�����������¹��췽����" + "----------------------");
			Constructor[] con = cDemo.getDeclaredConstructors();
			for (int i = 0; i < con.length; i++) {
				if (i != 0)
					System.out.println("----------------------"
							+ "----------------------"
							+ "----------------------");
				System.out.println(con[i].toString());
				System.out.println("\t��������" + con[i].getName());
				System.out.println("\t���η���"
						+ Modifier.toString(con[i].getModifiers()));

				System.out.print("\t�β��б�");
				cArray = con[i].getParameterTypes();
				if (cArray.length == 0)
					System.out.println("��");
				else {
					for (int j = 0; j < cArray.length; j++) {
						cTemp = cArray[j];
						if (j != 0)
							System.out.print(",");
						System.out.print(cTemp.toString());
					}
					System.out.println();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

}
