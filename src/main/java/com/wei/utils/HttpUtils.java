package com.wei.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class HttpUtils {

	private HttpUtils() {

	}

	public static HttpServletRequest getHttpServletRequest() {
		ServletRequestAttributes attribute = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		return attribute.getRequest();
	}

	public static HttpServletResponse getHttpServletResponse() {
		ServletRequestAttributes attribute = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		return attribute.getResponse();

	}

	public static boolean isAjaxRequest(HttpServletRequest request) {
		String reqType = request.getHeader("X-Requested-With");
		if ("XMLHttpRequest".equals(reqType)) {
			return true;
		}
		return false;
	}

}
