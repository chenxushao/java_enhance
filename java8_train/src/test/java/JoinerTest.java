import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.junit.Test;


public class JoinerTest {

	@Test
	public void joinerJava8() {
		List<String> strList = Arrays.asList("one", "two", "three", null);
		String csv = strList.stream()
				.filter(Objects::nonNull)
				.collect(Collectors.joining(","));
		assertEquals("one,two,three", csv);
	}
	
	@Test
	public void comparingJava8() {
        
    }
}
