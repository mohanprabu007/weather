package com.weather.configuration.exceptionHandler;

import java.util.List;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.weather.controller.WeatherContoller;
import com.weather.model.ErrorInfo;
import com.weather.model.ResponseModel;




@RestController
@ControllerAdvice
public class ApiGlobalException  extends ResponseEntityExceptionHandler{
	 private static final Logger LOGGER = LogManager.getLogger(ApiGlobalException.class);

	@ExceptionHandler(ApiException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody ResponseModel handleAllException(HttpServletResponse response,HttpServletRequest req, Exception ex) throws IOException {
		ErrorInfo error=new ErrorInfo(HttpStatus.BAD_REQUEST.name(),""+HttpStatus.BAD_REQUEST.value(),ex.getMessage(),""+req.getRequestURL());
		ex.printStackTrace();
		List<ErrorInfo> errorList=new ArrayList<ErrorInfo>();
		errorList.add(error);
		ResponseModel res=new ResponseModel();
		 res.setErrors(errorList);
		 res.setError(true);
		
		return res;
    }
	
	@ExceptionHandler(UsernameNotFoundException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody ResponseModel handleUsernameNotFoundException(HttpServletResponse response,HttpServletRequest req, Exception ex) throws IOException {
		ErrorInfo error=new ErrorInfo(HttpStatus.BAD_REQUEST.name(),""+HttpStatus.BAD_REQUEST.value(),ex.getMessage(),""+req.getRequestURL());

		List<ErrorInfo> errorList=new ArrayList<ErrorInfo>();
		errorList.add(error);
		ResponseModel res=new ResponseModel();
		 res.setErrors(errorList);
		 res.setError(true);
		
		return res;
    }
		
	@ExceptionHandler(BadCredentialsException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody ResponseModel handleBadCredentialsExceptionHan(HttpServletResponse response,HttpServletRequest req, Exception ex) throws IOException {
		ErrorInfo error=new ErrorInfo(HttpStatus.BAD_REQUEST.name(),""+HttpStatus.BAD_REQUEST.value(),ex.getMessage(),""+req.getRequestURL());
		List<ErrorInfo> errorList=new ArrayList<ErrorInfo>();
		errorList.add(error);
		ResponseModel res=new ResponseModel();
		 res.setErrors(errorList);
		 res.setError(true);
		
		return res;
    }
	
	@ExceptionHandler(Exception.class)
    public @ResponseBody ResponseModel handleAllOtherExceptions(HttpServletResponse response,HttpServletRequest req, Exception ex) throws IOException {
		LOGGER.error(ex);
		
		ErrorInfo error=new ErrorInfo(HttpStatus.BAD_REQUEST.name(),""+HttpStatus.BAD_REQUEST.value(),ex.getMessage(),""+req.getRequestURL());

		List<ErrorInfo> errorList=new ArrayList<ErrorInfo>();
		errorList.add(error);
		ResponseModel res=new ResponseModel();
		 res.setErrors(errorList);
		 res.setError(true);
		
		return res;
    }
}
