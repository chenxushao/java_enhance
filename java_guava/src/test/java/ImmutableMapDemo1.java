import com.google.common.collect.ImmutableMap;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Immutable Map showcase
 */
public class ImmutableMapDemo1 {

	@Test
	public void shouldUseMapBuilder() throws Exception {

		// when
		ImmutableMap<String, Integer> numbersMap = new ImmutableMap.Builder<String, Integer>()
				.put("one", 1).put("two", 2).put("three", 3).put("four", 4)
				.build();

		// then
		assertThat(numbersMap.get("two")).isEqualTo(2);
	}

	@Test
	public void shouldUseQuickMapCreator() throws Exception {

		// when
		ImmutableMap<String, Integer> numbersMap = ImmutableMap.of("one", 1,
				"two", 2, "three", 3, "four", 4, "five", 5);

		assertThat(numbersMap.get("two")).isEqualTo(2);
	}
}
