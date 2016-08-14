package com.chenxushao.java;

/*
 * һ���Ƚϴ�Ķ����������������ã������ú������õ��ڴ�������
 */
public class Grocery {
	private static final int SIZE = 10000;
	//����dʹ��ÿGrocery����ռ�ýϴ��ڴ�ռ䣬��80k����
	private double[] d = new double[SIZE];
	private String id;
	
	public Grocery(String id){
		this.id = id;
	}
	
	@Override
	public String toString(){
		return id;
	}

	@Override
	protected void finalize() throws Throwable {
		// TODO Auto-generated method stub
		super.finalize();
		System.out.println("Finalizing: " + id);
	} 
	
	
}
