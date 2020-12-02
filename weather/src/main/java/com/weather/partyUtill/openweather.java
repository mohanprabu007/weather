package com.weather.partyUtill;

import java.io.IOException;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.weather.model.weatherReq;
import com.weather.model.weatherResponse;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

@Service
public class openweather implements weatherdata{

	private static String MAIN_URL="http://api.openweathermap.org";
	private static String GET_WEATHER_END_POINT="/data/2.5/weather?q={cityname}&appid={APIkey}";
	private static String AUTH_KEY="793050bde8c1f719c7f146a9a40e6af3";
	
	public static void main(String[] a)
	{
		weatherReq r=new weatherReq();
		r.setCity("chennai");
		openweather o=new openweather(); 
		weatherResponse res=o.getweatherData(r);
		
		System.out.println(res);
	}
	
	public weatherResponse getweatherData(weatherReq req) {
		
		weatherResponse wr=new weatherResponse();
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
			System.out.println(MAIN_URL+parsedEndpoint);
			Request request = new Request.Builder()
					 .url(MAIN_URL+parsedEndpoint)
			  .get()
			  .addHeader("content-type", "application/json")
			  .addHeader("cache-control", "no-cache")
			  .build();

			Response response = client.newCall(request).execute();
			returnvalue=response.body().string();
			System.out.println("...."+returnvalue);
		} catch (IOException e) {}
		catch (Exception e) {}
		if(returnvalue.contains("<head>")) returnvalue="{}";
		if (returnvalue.equalsIgnoreCase("")) returnvalue="{}";
		return new JSONObject(returnvalue);
	}
	
}
