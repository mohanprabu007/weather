package com.weather.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.weather.businessLogic.WeatherData;
import com.weather.configuration.exceptionHandler.ApiException;
import com.weather.model.WeatherRequest;
import com.weather.model.WeatherResponse;

@CrossOrigin(origins = "*", maxAge = 36000)
@RestController

@RequestMapping("/weather")
public class WeatherContoller {
	 private static final Logger LOGGER = LogManager.getLogger(WeatherContoller.class);

@Autowired
@Qualifier("OpenWeather2")
WeatherData wd;
	


	@PostMapping
	public WeatherResponse getWeather(@Valid @RequestBody WeatherRequest req,BindingResult bindingResult) throws Exception
	{

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	   if (!bindingResult.hasErrors())
		{
	  
		WeatherResponse res=wd.getweatherData(req);
	    if(res.isError())
	    {
	    	throw new ApiException(res.getErrormsg(),HttpStatus.CONFLICT.value());
		}
	  
	    LOGGER.info("In get Weather data");
	    return res;
		}
		else
		{
			List<String> allErrorMsg=new ArrayList<String>();
		    
			bindingResult.getAllErrors().forEach((error) -> {
		        String fieldName = ((FieldError) error).getField();
		        String errorMessage = error.getDefaultMessage();
		        allErrorMsg.add(fieldName+ "::"+errorMessage);
		       
		       });
			  throw new ApiException(allErrorMsg.toString(),HttpStatus.BAD_REQUEST.value());
		}
	}
	

}
