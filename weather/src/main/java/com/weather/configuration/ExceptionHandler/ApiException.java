package com.weather.configuration.ExceptionHandler;

public class ApiException extends RuntimeException{
	
	 public ApiException(String msg,int code) {
	        super(msg);
	    }


}
