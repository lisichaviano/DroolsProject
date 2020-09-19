package com.sample;

import java.util.HashMap;

public class Log {

	private String username;
	private String timeRequest;
	private int codeStatusHTTP;
	private String ip;
	//private String url;
	private Object urlAndEntropy [];
	private boolean incurrent;
	
	
	//*** CONSTRUCTOR
	public Log(String username, String timeRequest, int codeStatusHTTP, String ip, Object urlEntropy [], boolean incurrent) {
		super();
		this.username = username;
		this.timeRequest = timeRequest;
		this.codeStatusHTTP = codeStatusHTTP;
		this.ip = ip;
		this.urlAndEntropy = urlEntropy;
		this.incurrent = incurrent;
	}

	
	//*** GETTERS and SETTERS
	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getTimeRequest() {
		return timeRequest;
	}


	public void setTimeRequest(String timeRequest) {
		this.timeRequest = timeRequest;
	}


	public int getCodeStatusHTTP() {
		return codeStatusHTTP;
	}


	public void setCodeStatusHTTP(int codeStatusHTTP) {
		this.codeStatusHTTP = codeStatusHTTP;
	}


	public String getIp() {
		return ip;
	}


	public void setIp(String ip) {
		this.ip = ip;
	}


	public boolean isIncurrent() {
		return incurrent;
	}


	public void setIncurrent(boolean incurrent) {
		this.incurrent = incurrent;
	}


	public Object[] getUrlAndEntropy() {
		return urlAndEntropy;
	}


	public void setUrlAndEntropy(Object[] urlAndEntropy) {
		this.urlAndEntropy = urlAndEntropy;
	}
}
