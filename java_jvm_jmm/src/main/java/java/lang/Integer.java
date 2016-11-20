package java.lang;

//一个伪造的Integer
public class Integer {

	public Integer(String value){
		
	}
	
	public Integer(double value){
		Math.abs(value);
	}
	
	
	public String toString(){
		return "这是一个伪造的Integer类.";
	}
}
