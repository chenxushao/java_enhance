package mytest;

public class Person implements Cloneable {// һ����ʶ�Խӿ�
	public String name;
	private int age;

	public Person() {
		this.name = "";
		this.age = 18;
	}

	public Person(String name, int age) {
		this.name = name;
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public Object clone() {// ��д
		Object o = null;
		try {
			o = super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return o;
	}

	@Override
	public boolean equals(Object o) // �÷����д�����
	{
		Person p = (Person) o;
		if (o == null)
			return false;
		if (p.getName().equals(this.name) && p.getAge() == this.age)
			return true;
		return false;
	}

	@Override
	public int hashCode() {// ��д
		return name.hashCode() + age * 1000;
	}

	@Override
	public String toString() {// ��д
		return "����: " + this.name + " , " + "���� :" + this.age;
	}

}