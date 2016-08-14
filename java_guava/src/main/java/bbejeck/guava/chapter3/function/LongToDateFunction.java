package bbejeck.guava.chapter3.function;

import java.util.Date;

import com.google.common.base.Function;

/**
 * User: Bill Bejeck
 * Date: 3/31/13
 * Time: 11:16 PM
 */
public class LongToDateFunction implements Function<Long, Date> {

    @Override
    public Date apply(Long input) {
        Date date = new Date();
        date.setTime(input);
        return date;
    }
}
