package com.chenxushao.java.concurrent.juc.tools.timeunit;

import java.util.concurrent.TimeUnit;

public class TimeUnitDemo1 {

	public static void main(String[] args) throws Exception{
		TimeUnit minutesTimeUnit = TimeUnit.MINUTES;

		TimeUnit hoursTimeUnit = TimeUnit.HOURS;

		TimeUnit secondsTimeUnit = TimeUnit.SECONDS;

		TimeUnit.SECONDS.sleep(2L);

	}
}
