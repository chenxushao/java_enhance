

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Test;

import com.google.common.collect.Lists;

public class Jdk8Optional {

	List<Person> persons =
		    Arrays.asList(
		        new Person("Max", 18),
		        new Person("Peter", 23),
		        new Person("Pamela", 23),
		        new Person("David", 12));
	
	@Test
	public void test1(){
		
		Optional<Person> optional = Optional.ofNullable(null);
		System.out.println(optional.empty());
		System.out.println(optional.map(x->x.getName()).orElse("x"));
	}
	
	private static class P{
		private List<String> labs =  Lists.newArrayList();;

		public List<String> getLabs() {
			return labs;
		}

		public void setLabs(List<String> labs) {
			this.labs = labs;
		}
	}
}
