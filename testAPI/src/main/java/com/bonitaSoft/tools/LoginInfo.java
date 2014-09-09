package com.bonitaSoft.tools;

import java.util.Date;

public class LoginInfo {
	private String user;
	
	private String pass;
	
	private Object session;

	private Date startDateSession;
	
	private String key;
	
	private Integer Ttl = 3600;
	
	public LoginInfo(String user, String pass, Object session, Date startDateSession, String key) {
		this.user = user;
		this.pass = pass;
		this.session = session;
		this.startDateSession = startDateSession;
		this.key = key;
	} 

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public Integer getTtl() {
		return Ttl;
	}

	public void setTtl(Integer ttl) {
		Ttl = ttl;
	}
	
	public Object getSession() {
		return session;
	}

	public void setSession(Object session) {
		this.session = session;
	}

	public Date getStartDateSession() {
		return startDateSession;
	}

	public void setStartDateSession(Date startDateSession) {
		this.startDateSession = startDateSession;
	}
}
