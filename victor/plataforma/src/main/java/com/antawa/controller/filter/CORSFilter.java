package com.antawa.controller.filter;

import java.io.IOException;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.GenericFilterBean;

public class CORSFilter extends GenericFilterBean {
	@Override
	public void doFilter(ServletRequest request, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse response = (HttpServletResponse) res;
		response.addHeader("Access-Control-Allow-Origin", "*");
		response.addHeader("Access-Control-Allow-Methods", "POST, PUT, GET, OPTIONS, DELETE");
		response.addHeader("Access-Control-Max-Age", "3600");
		//response.addHeader("Access-Control-Allow-Headers", "X-Requested-With, X-Auth-Token");
		response.addHeader("Access-Control-Allow-Headers", "Content-Type");
		
		response.addHeader("Access-Control-Allow-Credentials", "true");
		System.err.println("CORSFilter::::::::::::::::::::::::::::::::::." + request.getAttributeNames());

		for (Map.Entry<String, String[]> entry : request.getParameterMap().entrySet()) {
			System.out.println(entry.getKey());
			System.out.println(Arrays.toString(entry.getValue()));
		}
		System.err.println("CORSFilter::::::::::::::::::::::::::::::::::.");
		if (req.getMethod().equalsIgnoreCase("options")) {
			return;
		}
		chain.doFilter(request, response);
	}
}
