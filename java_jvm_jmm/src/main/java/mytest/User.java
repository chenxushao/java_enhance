package mytest;

public class User implements Comparable {
	public String name;
	public int age;
	
	public static int total = 0;

	public User() {
		name = "";
		age = 18;
		total++;

	}

	public User(String name, int age) {

		this.name = name;
		this.age = age;
		total++;
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

	 public void setAll(String name,int age)
	 {
		   this.name=name;
		   this.age=age;
	 }
	 
	public boolean equals(Object obj) {
		boolean flag = true;
		User u = (User) obj;
		if (obj == null)
			flag = false;
		else if (obj.getClass() != this.getClass())
			flag = false;
		else if (u.getName().equals(this.name) && u.getAge() == this.age)
			flag = true;
		else
			flag = false;
		return flag;

	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return this.name.hashCode() + this.age * 1000;
	}

	@Override
	public String toString() {
		return "ÐÕÃû£º" + this.name + "," + "ÄêÁä£º" + this.age;
	}

	public int compareTo(Object obj) {
		User u = (User) obj;
		int temp = 0;
		if (u.getAge() == this.getAge())
			temp = 0;
		else if (this.getAge() > u.getAge())
			temp = 1;
		else
			temp = -1;
		return temp;
	}

	public static int getTotal() {
		return total;
	}
}
