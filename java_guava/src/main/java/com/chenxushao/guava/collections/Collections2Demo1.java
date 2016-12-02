package com.chenxushao.guava.collections;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Test;

import pl.tomaszdziurko.guava.geo.Continent;
import pl.tomaszdziurko.guava.geo.Country;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;

/**
 * Collections2 showcase
 */
public class Collections2Demo1 {

	@Test
	public void shouldTransformCollection() throws Exception {

		// given
		ArrayList<Country> countries = Lists.newArrayList(Country.POLAND,
				Country.BELGIUM, Country.ENGLAND);

		// when
		Collection<String> capitalCities = Collections2.transform(countries,
				new Function<Country, String>() {

					@Override
					public String apply(/* @Nullable */Country country) {
						return country.getCapitalCity();
					}
				});

		// then
		assertThat(capitalCities).containsOnly("Warsaw", "Brussels", "London");
	}

	@Test
	public void shouldFilterCountriesOnlyFromAfrica() throws Exception {

		// given
		ArrayList<Country> countries = Lists.newArrayList(Country.POLAND,
				Country.BELGIUM, Country.SOUTH_AFRICA);

		// when
		Collection<Country> countriesFromAfrica = Collections2.filter(
				countries, new Predicate<Country>() {

					@Override
					public boolean apply(/* @Nullable */Country country) {
						return Continent.AFRICA.equals(country.getContinent());
					}
				});

		// then
		assertThat(countriesFromAfrica).containsOnly(Country.SOUTH_AFRICA);
	}

	@Test
	public void shouldShowThatResultIsOnlyAView() throws Exception {

		// given
		ArrayList<Country> countries = Lists.newArrayList(Country.POLAND,
				Country.BELGIUM, Country.ENGLAND);

		// when
		Collection<String> capitalCities = Collections2.transform(countries,
				new Function<Country, String>() {

					@Override
					public String apply(/* @Nullable */Country country) {
						return country.getCapitalCity();
					}
				});

		// then
		assertThat(capitalCities).containsOnly("Warsaw", "Brussels", "London");
		// assertThat(capitalCities).excludes("Pretoria");

		countries.add(Country.SOUTH_AFRICA);

		assertThat(capitalCities).contains("Pretoria");
	}
}
