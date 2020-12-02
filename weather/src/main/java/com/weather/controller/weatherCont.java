package com.weather.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.weather.configuration.ExceptionHandler.ApiException;
import com.weather.model.weatherReq;
import com.weather.model.weatherResponse;
import com.weather.partyUtill.openweather;
import com.weather.partyUtill.weatherdata;
@CrossOrigin(origins = "*", maxAge = 36000)
@RestController
@RequestMapping("/weather")
public class weatherCont {
	
@Autowired
weatherdata wd;
	
	@PostMapping
	public weatherResponse AddCategory(@RequestBody weatherReq req) throws Exception
	{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    String name = auth.getName();
	    
	  
	    weatherResponse res=wd.getweatherData(req);
	    if(res.isError())
	    {
	    	throw new ApiException(res.getErrormsg(),HttpStatus.CONFLICT.value());
		}
	    System.out.println("In get Weather data");
	
	    return res;
	}
	

}
