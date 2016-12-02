package com.chenxushao.guava.collections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;

public class ImmutableListDemo1 {

	public final static List<String> ResortHotelLocation = Arrays
			.asList(new String[] { "B01", "B02", "B03", "B04" });

	public final static List<String> PrivilegeHotelsLocation = Arrays
			.asList(new String[] { "A02", "A03", "A04", "A05", "A06", "A07" });

	public static void main(String[] args) {

		List<String> list = new ArrayList<String>();
		list.add("a");
		list.add("b");
		list.add("c");
		System.out.println(Joiner.on(',').join(list));

		List<String> toWillSelectLocation = Lists.newArrayList();
		toWillSelectLocation.add("B01");
		toWillSelectLocation.add("B02");
		toWillSelectLocation.add("B03");
		toWillSelectLocation.add("B04");

		List<String> s = new ArrayList<String>();
		s.add("B01");
		s.add("B02");

		ImmutableList<String> of = ImmutableList.of("B01", "B02", "B03", "B04");

		toWillSelectLocation.removeAll(s);

		System.out.println(toWillSelectLocation);
		System.out.println(s);
		System.out.println(of);

		/*
		 * ImmutableList<String> of = ImmutableList.of("B01","B02","B03","B04");
		 * System.out.println(of);
		 */

		System.out.println();
	}
}
