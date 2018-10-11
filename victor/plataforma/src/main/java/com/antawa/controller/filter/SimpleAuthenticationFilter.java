package com.antawa.controller.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
/**
 * 
 * @author Victor Quimbiamba <vicanall@gmail.com>
 *
 */
public class SimpleAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {

		System.err.print("-.----attemptAuthentication---------->>>");

		UsernamePasswordAuthenticationToken authRequest = getAuthRequest(request);
		setDetails(request, authRequest);

		return this.getAuthenticationManager().authenticate(authRequest);
	}

	private UsernamePasswordAuthenticationToken getAuthRequest(HttpServletRequest request) {

		String username = obtainUsername(request);
		String password = obtainPassword(request);

		System.err.println("username: " + username);

		System.err.println("password: " + password);
		// String domain = obtainDomain(request);

		// ...

		String usernameDomain = String.format("%s%s%s", username.trim(), String.valueOf(Character.LINE_SEPARATOR));
		return new UsernamePasswordAuthenticationToken(usernameDomain, password);
	}
}
