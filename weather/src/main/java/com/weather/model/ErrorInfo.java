package com.weather.model;

import java.util.Date;

public class ErrorInfo {
	public final String error;
	public final String code;
	public final Date timestamp=new Date();
	public final String message;
	public final String path;
	public ErrorInfo(String error, String code, String message, String path) {
		super();
		this.error = error;
		this.code = code;
		this.message = message;
		this.path = path;
	}
	
	
}
