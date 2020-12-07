package com.weather.businessLogic;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.weather.model.WeatherRequest;
import com.weather.model.WeatherResponse;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

@Service
@Qualifier("OpenWeather")
public class OpenWeatherImpl implements WeatherData{

	private static final Logger LOGGER = LogManager.getLogger(OpenWeatherImpl.class);
	private static String MAIN_URL="http://api.openweathermap.org";
	private static String GET_WEATHER_END_POINT="/data/2.5/weather?q={cityname}&appid={APIkey}";
	private static String AUTH_KEY="793050bde8c1f719c7f146a9a40e6af3";
	
	public static void main(String[] a)
	{
		WeatherRequest req=new WeatherRequest();
		req.setCity("chennai");
		OpenWeatherImpl o=new OpenWeatherImpl(); 
		WeatherResponse res=o.getweatherData(req);
		
		LOGGER.info(res);
	}
	
	public WeatherResponse getweatherData(WeatherRequest req) {
		
		WeatherResponse wr=new WeatherResponse();
		JSONObject obj=GetWeatherDataFromAPI(req.getCity());
		wr.setCity(req.getCity());
		wr.setTempUom(req.getUom());
		int code=obj.has("cod")?obj.getInt("cod"):0;
		
		if(obj.has("main") && code==200)
		{
			wr.setTempBaseUom("Kelvin");
			wr.setTempBaseUnit(obj.getJSONObject("main").getDouble("temp"));
		}
		else if(code==401)
		{
			wr.setError(true);
			wr.setErrormsg(obj.has("message")?obj.getString("message"):"Invalid API key");
		}
		else if(code==404)
		{
			wr.setError(true);
			wr.setErrormsg(obj.has("message")?obj.getString("message"):"city not found");
		}	
		else
		{
			wr.setError(true);
			wr.setErrormsg("Invalid data");
		}
		wr.updateTemp();
		return wr;
	}
	
	private  JSONObject GetWeatherDataFromAPI(String cityname)
	{
		String returnvalue="";

		try {
			OkHttpClient client = new OkHttpClient();
			String parsedEndpoint=GET_WEATHER_END_POINT;
			parsedEndpoint=parsedEndpoint.replace("{cityname}", cityname);
			parsedEndpoint=parsedEndpoint.replace("{APIkey}", AUTH_KEY);
			LOGGER.info(MAIN_URL+parsedEndpoint);
			Request request = new Request.Builder()
					 .url(MAIN_URL+parsedEndpoint)
			  .get()
			  .addHeader("content-type", "application/json")
			  .addHeader("cache-control", "no-cache")
			  .build();

			Response response = client.newCall(request).execute();
			returnvalue=response.body().string();
			LOGGER.info("..ssss.."+returnvalue);
		} catch (IOException e) {}
		catch (Exception e) {}
		if(returnvalue.contains("<head>")) returnvalue="{}";
		if (returnvalue.equalsIgnoreCase("")) returnvalue="{}";
		return new JSONObject(returnvalue);
	}
	}
