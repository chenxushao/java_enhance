import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
public class ListToMapWithBinaryOperator {
	public static void main(String[] args) {
		List<P> list = new ArrayList<>();
		list.add(new P(100, "Mohan"));
		list.add(new P(100, "Sohan"));
		list.add(new P(300, "Mahesh"));
		Map<Integer, String> map = list.stream()
				.collect(Collectors.toMap(P::getId, P::getName, (x, y) -> x+", "+ y));
		map.forEach((x, y) -> System.out.println("Key: " + x +", value: "+ y));
	}
} 