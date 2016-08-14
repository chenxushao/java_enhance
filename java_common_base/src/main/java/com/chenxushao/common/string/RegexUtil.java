package com.chenxushao.common.string;

/**
 * @author Khaled - khaled[at]nightlabs[dot]de
 * @version $Version$ - $Date$
 */

public class RegexUtil {

	public static boolean isValidEmailAddress(String email) {
		String regex = "^[^@]+@([0-9a-zA-Z\\-]+\\.)+[a-zA-Z]+$";
		return email.matches(regex);
	}
	
}
