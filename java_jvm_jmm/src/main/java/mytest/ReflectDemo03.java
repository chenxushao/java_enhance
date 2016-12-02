package mytest;

import java.lang.reflect.Array;

public class ReflectDemo03 {

	public static void main(String[] args) {
		int[] a = new int[10];

		for (int i = 0; i < a.length; i++)
			a[i] = i;
		for (int i = 0; i < a.length; i++)
			System.out.print(a[i] + " ");
		System.out.println("\n" + "a[9]��ֵΪ��" + Array.get(a, 9));
		System.out.println("��a[0]��ֵ�޸�Ϊ10�Ĳ������Ϊ��Array.setInt(a, 0,10)");
		Array.setInt(a, 0, 10);
		System.out.println("a[0]��ֵΪ��" + Array.get(a, 0));
		System.out.println("����a�ĳ���Ϊ: " + Array.getLength(a));
		System.out.println("�����Ƕ�̬���������ʾ��������һ����������b");
		int[] b = (int[]) Array.newInstance(int.class, 6);
		for (int i = 0; i < b.length; i++)
			System.out.print(b[i] + " ");
	}

}
