package java.lang1;

//自定义的Integer
public class Integer {

	public Integer(String value) {

	}

	public Integer(double value) {
		Math.abs(value);
	}

	public String toString() {
		return "我是自定义的Integer";
	}
}
