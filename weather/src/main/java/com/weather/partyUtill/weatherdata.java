package com.weather.partyUtill;

import org.springframework.stereotype.Service;

import com.weather.model.weatherReq;
import com.weather.model.weatherResponse;

@Service
public interface weatherdata {
	
	public weatherResponse getweatherData(weatherReq req);

}
