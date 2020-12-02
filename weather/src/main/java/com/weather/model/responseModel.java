package com.weather.model;

public class responseModel {
	private boolean isError=false;
	private Object content;
	public String msg;
	public ErrorInfo errors;
	
	public responseModel() {}

	public boolean isError() {
		return isError;
	}

	public Object getContent() {
		return content;
	}

	public String getMsg() {
		return msg;
	}

	public ErrorInfo getErrors() {
		return errors;
	}

	public void setError(boolean isError) {
		this.isError = isError;
	}

	public void setContent(Object content) {
		this.content = content;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public void setErrors(ErrorInfo errors) {
		this.errors = errors;
	}
	
}
