import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
public class ListToMap2 {
	public static void main(String[] args) {
		List<P> list = new ArrayList<>();
		list.add(new P(100, "Mohan"));
		list.add(new P(200, "Sohan"));
		list.add(new P(300, "Mahesh"));
		Map<Integer, String> map = list.stream()
				.collect(Collectors.toMap(P::getId, P::getName));
		map.forEach((x, y) -> System.out.println("Key: " + x +", value: "+ y));
	}
} 