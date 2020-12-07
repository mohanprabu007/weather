package com.weather.model;

import java.util.List;

public class ResponseModel {
	private boolean isError=false;
	private Object content;
	public String msg;
	public List<ErrorInfo> errors;
	
	public ResponseModel() {}

	public boolean isError() {
		return isError;
	}

	public Object getContent() {
		return content;
	}

	public String getMsg() {
		return msg;
	}

	public List<ErrorInfo> getErrors() {
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

	public void setErrors(List<ErrorInfo> errors) {
		this.errors = errors;
	}
	
}
