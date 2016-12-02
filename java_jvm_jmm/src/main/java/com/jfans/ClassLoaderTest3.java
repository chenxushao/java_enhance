package com.jfans;

public class ClassLoaderTest3 {

	public static void main(String[] args) throws ClassNotFoundException {
		// α���Integer��ͨ�����룬����Զ�޷����У����������JDK���������˫��ί��ģ��
		// ���ֻ��Ʊ�֤��JDK������ⲻ�ᱻ�ƻ�
		Integer integer = new Integer("1234");
		System.out.println(integer + "--->"
				+ integer.getClass().getClassLoader());

	}

}
