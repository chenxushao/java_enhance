package bbejeck.guava.chapter3.function;

import java.util.Date;

import com.google.common.base.Function;

/**
 * User: Bill Bejeck Date: 8/2/13 Time: 10:35 PM
 */
public class StringToDateFunction implements
		Function<String, Function<Date, String>> {

	@Override
	public Function<Date, String> apply(String input) {
		return new DateFormatFunction(input);
	}
}
