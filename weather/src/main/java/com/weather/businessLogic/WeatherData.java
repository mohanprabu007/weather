package com.weather.businessLogic;

import com.weather.model.WeatherRequest;
import com.weather.model.WeatherResponse;

public interface WeatherData {
	
	public WeatherResponse getweatherData(WeatherRequest req);

}
