package bbejeck.guava.chapter4.ordering;

import java.util.Comparator;

import bbejeck.guava.common.model.City;

import com.google.common.primitives.Ints;

/**
 * User: Bill Bejeck
 * Date: 4/12/13
 * Time: 7:50 AM
 */
public class CityByPopulation implements Comparator<City> {

    @Override
    public int compare(City city1, City city2) {
        return Ints.compare(city1.getPopulation(), city2.getPopulation());
    }
}
