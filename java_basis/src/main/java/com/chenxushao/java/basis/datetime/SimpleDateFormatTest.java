package com.chenxushao.java.basis.datetime;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SimpleDateFormatTest {

	public static void main(String[] args) {
	     SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
	     try {
	    	 //parse方法，将时间字符串转化为Date,格式一定要吻合,是两位的MM就一定要是两位
			Date date = sdf.parse("20090914");
			System.out.println(new SimpleDateFormat("yyyy年M月d日").format(date));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
