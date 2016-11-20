package mytest;
import java.lang.reflect.*;

public class ReflectDemo01 
{

	public static void main(String[] args) 
	{
		 User u=new User("zhangsan",20);
		 ReflectDemo01 ref=new ReflectDemo01();
		 
		 System.out.println(u);
		 Field fd=null;
		 Class c=null;
		 Object  value=null;
		 Class[] cArray=null;
		 try {
			   c=mytest.User.class;
			   fd=c.getField("name");
			 
				  value=fd.get(u);
				System.out.println(value);
				fd=c.getField("age");
				fd.setInt(u,18);
				value=fd.getInt(u);
				System.out.println(value);
				cArray=new Class[]{String.class,int.class};
				Method md=c.getMethod("setAll",cArray);
				value=md.invoke(u,"lisi",22);
				System.out.println(u);
		 }
			 catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			   } catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		 catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
	}

}
