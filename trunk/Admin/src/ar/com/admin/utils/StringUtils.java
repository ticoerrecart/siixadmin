package ar.com.admin.utils;

public abstract class StringUtils {

	public static String nullToBlank(String s) {
		return s == null ? "" : s;
	}
}
