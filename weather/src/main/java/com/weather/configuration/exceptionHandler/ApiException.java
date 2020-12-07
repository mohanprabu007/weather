package com.weather.configuration.exceptionHandler;

public class ApiException extends RuntimeException{
	
	 public ApiException(String msg,int code) {
	        super(msg);
	    }


}
