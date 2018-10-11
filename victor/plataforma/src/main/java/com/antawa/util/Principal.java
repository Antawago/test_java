package com.antawa.util;

import java.util.List;
/**
 * 
 * @author Victor Quimbiamba <vicanall@gmail.com>
 *
 */
public class Principal implements java.security.Principal {
	private java.security.Principal p;

	public java.security.Principal getP() {
		return p;
	}

	public void setP(java.security.Principal p) {
		this.p = p;
	}

	private List<String> accesos;

	public List<String> getAccesos() {
		return accesos;
	}

	public void setAccesos(List<String> accesos) {
		this.accesos = accesos;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return p.getName();
	}

	public Principal(java.security.Principal p) {
		this.p = p;
	}

}
