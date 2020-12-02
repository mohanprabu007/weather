package com.weather.configuration.ExceptionHandler;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.weather.model.ErrorInfo;
import com.weather.model.responseModel;



@RestController
@ControllerAdvice
public class ApiGlobalException  extends ResponseEntityExceptionHandler{

	@ExceptionHandler(ApiException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody responseModel ospringHandleNotFound(HttpServletResponse response,HttpServletRequest req, Exception ex) throws IOException {
		ErrorInfo error=new ErrorInfo(HttpStatus.BAD_REQUEST.name(),""+HttpStatus.BAD_REQUEST.value(),ex.getMessage(),""+req.getRequestURL());
		ex.printStackTrace();
		 responseModel res=new responseModel();
		 res.setErrors(error);
		 res.setError(true);
		
		return res;
    }
	
	@ExceptionHandler(UsernameNotFoundException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody responseModel ospringUsernameNotFoundException(HttpServletResponse response,HttpServletRequest req, Exception ex) throws IOException {
		ErrorInfo error=new ErrorInfo(HttpStatus.BAD_REQUEST.name(),""+HttpStatus.BAD_REQUEST.value(),ex.getMessage(),""+req.getRequestURL());

		 responseModel res=new responseModel();
		 res.setErrors(error);
		 res.setError(true);
		
		return res;
    }
		
	@ExceptionHandler(BadCredentialsException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody responseModel BadCredentialsExceptionHan(HttpServletResponse response,HttpServletRequest req, Exception ex) throws IOException {
		ErrorInfo error=new ErrorInfo(HttpStatus.BAD_REQUEST.name(),""+HttpStatus.BAD_REQUEST.value(),ex.getMessage(),""+req.getRequestURL());

		 responseModel res=new responseModel();
		 res.setErrors(error);
		 res.setError(true);
		
		return res;
    }
	
	@ExceptionHandler(Exception.class)
    public @ResponseBody responseModel OtherHandleNotFound(HttpServletResponse response,HttpServletRequest req, Exception ex) throws IOException {
		ex.printStackTrace();
		System.out.println("inn common error");
		ErrorInfo error=new ErrorInfo(HttpStatus.BAD_REQUEST.name(),""+HttpStatus.BAD_REQUEST.value(),ex.getMessage(),""+req.getRequestURL());

		 responseModel res=new responseModel();
		 res.setErrors(error);
		 res.setError(true);
		
		return res;
    }
}
