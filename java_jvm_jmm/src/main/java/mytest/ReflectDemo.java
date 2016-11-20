package mytest;

import java.lang.reflect.*;
import java.io.*;

public class ReflectDemo
{

	public static void main(String[] args)  
	{ 
		Class cDemo=null;
		Class[] cArray=null;
		Class cTemp=null;
		String className="";
		BufferedReader brd=null;
	    System.out.println("请输入类名：");
		try{
			   
			  brd=new BufferedReader(new InputStreamReader(System.in));
			  className=brd.readLine();
			  if(className.equals(""))
			  { 
				  System.out.println("输入的类型为空.");
				  return;
			  }
			  
			  //cDemo=Class.forName("mytest."+className);
			  cDemo=Class.forName(className);
			  System.out.println("mytest."+className);
			  int mod=cDemo.getModifiers();
			  System.out.println("\t修饰符："+Modifier.toString(mod));
			  System.out.println("\t包名："+cDemo.getPackage());
			  System.out.println("\t类名: "+cDemo.getName());
			  System.out.println("\t父类："+cDemo.getSuperclass());
			  System.out.print("\t所实现的接口：");
			  
			  cArray=cDemo.getInterfaces();
			  if (cArray.length==0)
			  {
				  System.out.println("无");
			  }
			  else
			  {
			  for(int i=0; i<cArray.length; i++)
			  {
				    cTemp=cArray[i];
				    if (i!=0)
				    	System.out.print(",");
				    System.out.print(cTemp.toString());
			  }
			  }
			  System.out.println();
			  Field[] fds=cDemo.getDeclaredFields();
			  
			  System.out.println("\n"+"----------------------"+className+"类声明了如下字段："+"----------------------");
			 
			  for(int i=0; i<fds.length; i++)
			  {    
				  if(i!=0)
				  System.out.println("----------------------"+"----------------------"+"----------------------");
				  System.out.println(fds[i].toString());
				  System.out.println("\t属性名："+fds[i].getName());
				  System.out.println("\t修饰符："+Modifier.toString(mod));
				  System.out.println("\t数据类型："+fds[i].getType());
				 
			  }
			  
			  Method[] mds=cDemo.getDeclaredMethods();
			  System.out.println("\n"+"----------------------"+className+"类声明了如下方法："+"----------------------");
			  
			  for(int i=0; i<mds.length; i++)
			  {    
				  if(i!=0)
				  System.out.println("----------------------"+"----------------------"+"----------------------");
				  System.out.println(mds[i].toString());
				  System.out.println("\t方法名："+mds[i].getName());
				  System.out.println("\t修饰符："+Modifier.toString(mds[i].getModifiers()));
				  System.out.println("\t返回值类型："+mds[i].getReturnType());
				  System.out.print("\t形参列表：");
				  cArray=mds[i].getParameterTypes();
				  if(cArray.length==0)
					  System.out.println("无");
				  else{
				  for(int j=0; j<cArray.length; j++)
				  {    
					  cTemp=cArray[j];
					  if(j!=0)
						  System.out.print(",");
					  System.out.print(cTemp.toString());
				  }
				  System.out.println();
				  }
				 
				  
			  }
			  
			  System.out.println("\n"+"----------------------"+className+"类声明了如下构造方法："+"----------------------");
			  Constructor[] con=cDemo.getDeclaredConstructors();
			  for (int i=0; i<con.length; i++)
			  {
				  if(i!=0)
					  System.out.println("----------------------"+"----------------------"+"----------------------");
				  System.out.println(con[i].toString());
				  System.out.println("\t方法名："+con[i].getName());
				  System.out.println("\t修饰符："+Modifier.toString(con[i].getModifiers()));
				 
				  System.out.print("\t形参列表：");
				  cArray=con[i].getParameterTypes();
				  if(cArray.length==0)
					  System.out.println("无");
				  else{
				  for(int j=0; j<cArray.length; j++)
				  {    
					  cTemp=cArray[j];
					  if(j!=0)
						  System.out.print(",");
					  System.out.print(cTemp.toString());
				  }
				  System.out.println();
				  }
			  }
		}catch(IOException e){
			e.printStackTrace();
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
        
		
          
	}

}
