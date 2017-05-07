package com.wei.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wei.utils.HttpUtils;

@WebFilter(urlPatterns = { "/*" })
public class CustomFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest oHttpServletRequest = (HttpServletRequest) request;
		String uri = oHttpServletRequest.getRequestURI();
		if (FilterHelper.excludeUri(uri)) {
			chain.doFilter(request, response);
			return;
		}

		if (FilterHelper.isUserLogined(oHttpServletRequest)) {
			chain.doFilter(request, response);
			return;
		}

		HttpServletResponse oHttpServletResponse = (HttpServletResponse) response;
		if (HttpUtils.isAjaxRequest(oHttpServletRequest)) {
			oHttpServletResponse.setContentType("text/html");
			oHttpServletResponse.setHeader("url", oHttpServletRequest.getContextPath() + "/html/login.html");
		} else {
			oHttpServletResponse.sendRedirect(oHttpServletRequest.getContextPath() + "/html/login.html");
		}

	}

	@Override
	public void destroy() {

	}

}
