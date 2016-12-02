
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import com.google.common.collect.Lists;

public class Jdk8OfCollection {

	List<Person> persons = Arrays.asList(new Person("Max", 18), new Person(
			"Peter", 23), new Person("Pamela", 23), new Person("David", 12));

	@Test
	public void test1() {
		List<Person> filtered = persons.stream()
				.filter(p -> p.getName().startsWith("P"))
				.collect(Collectors.toList());
		System.out.println(filtered); // [Peter, Pamela]
	}

	// 获取列表中其中一个元素
	@Test
	public void testxx() {
		Optional<Person> optional = persons.stream()
				.filter(p -> ("dAv2id".equalsIgnoreCase(p.getName())))
				.findFirst();
		System.out.println(optional.isPresent());

	}

	// 按照List中某个属性进行分组
	@Test
	public void test2() {
		Map<Integer, List<Person>> personsByAge = persons.stream().collect(
				Collectors.groupingBy(p -> p.getAge()));
		System.out.println(personsByAge);
	}

	// 抽取List中的某个属性
	@Test
	public void test3() {
		List<String> names = persons.parallelStream().map(p -> p.getName())
				.collect(Collectors.toList());
		System.out.println(names);
		Set<Integer> ages = persons.parallelStream().map(p -> p.getAge())
				.collect(Collectors.toSet());
		System.out.println(ages);
	}

	// 过滤null，并转化为大写字符
	@Test
	public void test4() {
		List<String> names = Arrays.asList("a", "b", "c", null);
		List<String> upperNames = names.stream()
				.filter(s -> StringUtils.isNotBlank(s))
				.map(s -> s.toUpperCase()).collect(Collectors.toList());
		// List<String> upperNames =
		// names.stream().filter(s->StringUtils.isNotBlank(s)).map(String::toUpperCase).collect(Collectors.toList());
		System.out.println(names);
		System.out.println(upperNames);
	}

	// 排序
	@Test
	public void test5() {
		List<String> myList = Arrays.asList("a1", "a2", "b1", "c2", "c1");

		List<String> newList = myList.stream().filter(s -> s.startsWith("c"))
				.map(String::toUpperCase).sorted().collect(Collectors.toList());// sorted

		System.out.println(newList);
	}

	// 过滤
	@Test
	public void test6() {
		List<String> list = Lists.newArrayList();
		list.add("a");
		list.add("A");
		list.add("b");
		System.out.println(list.parallelStream()
				.filter(s -> s.toLowerCase().equals("a"))
				.collect(Collectors.toList()));
		System.out.println(list);
	}

	// 过滤
	@Test
	public void test7() {
		List<String> list = Lists.newArrayList();
		list.add("a");
		list.add("A");
		list.add("b");
		list.add(null);
		System.out.println(list);
		list = list.stream().parallel().filter(s -> StringUtils.isNotBlank(s))
				.map(s -> s.toLowerCase()).collect(Collectors.toList());
		System.out.println(list);
	}

	// 去重
	@Test
	public void test8() {
		List<String> l = Stream.of("a", "b", "c", "b").distinct()
				.collect(Collectors.toList());
		System.out.println(l); // [a, b, c]
	}

	// collect
	@Test
	public void test9() {
		Collection<String> collection = Arrays.asList("abc", "cde", "efg");
		List<String> list = collection.stream().filter(x -> x.contains("c"))
				.collect(Collectors.toList());
		list.forEach(x -> System.out.println(x));
	}

	@Test
	public void test10() {
		P p1 = new P();
		p1.setLabs(Arrays.asList("1", "2", "a"));

		P p2 = new P();
		p2.setLabs(Arrays.asList("a", "b"));

		P p3 = new P();

		List<P> ps = Arrays.asList(p1, p2, p3);
		System.out.println(ps.stream().flatMap(p -> p.getLabs().stream())
				.distinct().collect(Collectors.toList()));

	}

	@Test
	public void test11() {
		List<Integer> costBeforeTax = Arrays.asList(100, 200, 300, 400, 500,
				null);
		// double total = 0;
		// for (Integer cost : costBeforeTax) {
		// double price = cost + 0.12*cost;
		// total = total + price;
		// }
		// System.out.println("Total : " + total);

		// New way:
		List<Integer> costBeforeTax1 = Arrays.asList(100, 200, 300, 400, 500,
				null);
		double bill = costBeforeTax1.stream().map(x -> x == null ? 0 : x)
				.map((cost) -> cost + 0.12 * cost)
				.reduce((sum, cost) -> sum + cost).get();
		System.out.println("Total : " + bill);

	}

	@Test
	public void test12() {

		Person p1 = new Person("cxs1", 1);
		Person p2 = new Person("cxs2", 2);
		Person p3 = new Person("cxs3", 3);
		Person p4 = new Person(null, null);

		List<Person> pp = Arrays.asList(p1, p2, p3, p4);
		Integer total = pp.stream().filter(p -> !(p.getAge() == null))
				.map(r -> r.getAge()).reduce((sum, amount) -> sum + amount)
				.get();

		System.out.println(pp);
		System.out.println(total);
	}

	@Test
	public void findFirst1() {
		System.out.println(Arrays.asList("a1", "a2", "a3").stream().findFirst()
				.get());
	}

	@Test
	public void findFirst2() {
		Stream.of("a1", "a2", "a3").findFirst().ifPresent(System.out::println);
	}

	private static class P {
		private List<String> labs = Lists.newArrayList();;

		public List<String> getLabs() {
			return labs;
		}

		public void setLabs(List<String> labs) {
			this.labs = labs;
		}
	}
}
