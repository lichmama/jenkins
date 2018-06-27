package com.lichmama.demo.common.util;

public final class StringUtil {

	private StringUtil() {
	}

	public static boolean isEmpty(String s) {
		return (s == null || s.length() == 0);
	}

	public static boolean isNotEmpty(String s) {
		return !isEmpty(s);
	}

	public static boolean isEqual(String s1, String s2) {
		if (s1 == null || s2 == null)
			return false;
		return s1.equals(s2);
	}

	public static boolean isNotEqual(String s1, String s2) {
		return !isEqual(s1, s2);
	}
}
