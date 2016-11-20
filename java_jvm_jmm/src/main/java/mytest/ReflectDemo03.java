package mytest;
import java.lang.reflect.*;

public class ReflectDemo03 
{


	public static void main(String[] args) 
	{
		  int[] a=new int[10];
		  
		  for (int i=0; i<a.length; i++)
			  a[i]=i;
		  for (int i=0; i<a.length; i++)
		  System.out.print(a[i]+" ");
          System.out.println("\n"+"a[9]的值为："+Array.get(a,9));
          System.out.println("将a[0]的值修改为10的操作语句为：Array.setInt(a, 0,10)");
          Array.setInt(a, 0,10);
          System.out.println("a[0]的值为："+Array.get(a,0));
          System.out.println("数组a的长度为: "+Array.getLength(a));
          System.out.println("现在是动态创建数组的示例：创建一个整型数组b");
          int[] b=(int[]) Array.newInstance(int.class,6);
          for(int i=0; i<b.length; i++)
          System.out.print(b[i]+" ");
	}

}
