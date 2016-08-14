import java.io.IOException;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.NotFoundException;


public class Test {
	
	public static void main(String[] args) throws NotFoundException, IOException, Exception {
		ClassPool cp = ClassPool.getDefault();
		 
		CtClass ctClass = cp.getCtClass("User");
		CtClass ctClassSuper = cp.getCtClass("BaseModel");
	 
//		ctClass.setName("Person");
		ctClass.setInterfaces(new CtClass[]{ctClassSuper});;
		ctClass.writeFile("bin");
//		System.out.println(ctClass);
		
		System.out.println(User.class.getInterfaces()[0]);
//		Class clazz  = Class.forName("Person");
//		System.out.println("clazz: " + clazz);
//		System.out.println(Test.class.getClassLoader().loadClass("User").getInterfaces()[0]);
//		System.out.println(User.class.getInterfaces());
//		System.out.println(User.class.getSuperclass());
	}
}