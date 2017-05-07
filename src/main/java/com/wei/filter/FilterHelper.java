package com.wei.filter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class FilterHelper {

	public static final String USER_TOKEN = "user_token";

	private static final String[] EXCLUDE_CONTAINER = { "/js/" };

	private static final String[] EXCLUDE_SUFFIX = { ".js", ".css", ".less", ".png", ".jpg" };

	private static final String[] EXCLUDE_URI = { "registry.html", "login.html", "registry_success.html", "registry",
			"login" };

	private FilterHelper() {

	}

	public static boolean excludeUri(String uri) {

		for (String str : EXCLUDE_CONTAINER) {
			if (uri.contains(str)) {
				return true;
			}
		}

		for (String str : EXCLUDE_SUFFIX) {
			if (uri.endsWith(str)) {
				return true;
			}
		}

		for (String str : EXCLUDE_URI) {
			if (uri.endsWith(str)) {
				return true;
			}
		}

		return false;

	}

	public static boolean isUserLogined(HttpServletRequest oHttpServletRequest) {

		HttpSession session = oHttpServletRequest.getSession();

		if (null == session) {
			return false;
		}

		String userToken = (String) session.getAttribute(USER_TOKEN);
		if (null == session.getAttribute(USER_TOKEN)) {
			return false;
		}

		Cookie[] cookies = oHttpServletRequest.getCookies();
		for (Cookie cookie : cookies) {
			if (cookie.getValue().equals(userToken)) {
				return true;
			}
		}

		return false;
	}

}
