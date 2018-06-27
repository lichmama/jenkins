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

	public static String join(Object[] objects, String delimeter) {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < objects.length; i++) {
			if (i > 0)
				builder.append(delimeter);
			builder.append(String.valueOf(objects[i]));
		}
		return builder.toString();
	}
}
