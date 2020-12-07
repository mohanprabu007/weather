package com.weather.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class WeatherResponse {

	private String city;
	
	@JsonIgnore
    private double tempBaseUnit;
	@JsonIgnore
    private String tempBaseUom;
	@JsonIgnore
    private double temp;
    @JsonIgnore
    private String tempUom;
    
    private String temperature;
    
    @JsonIgnore 
    private boolean isError;
    @JsonIgnore
    private String errormsg;
    
    
	public String getCity() {
		return city;
	}
	public double getTempBaseUnit() {
		return tempBaseUnit;
	}
	public String getTempBaseUom() {
		return tempBaseUom;
	}
	public double getTemp() {
		return temp;
	}
	public String getTempUom() {
		return tempUom;
	}
	public String getTemperature() {
	
		return temperature;
	}
	public boolean isError() {
		return isError;
	}
	public String getErrormsg() {
		return errormsg;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public void setTempBaseUnit(double tempBaseUnit) {
		this.tempBaseUnit = tempBaseUnit;
	}
	public void setTempBaseUom(String tempBaseUom) {
		this.tempBaseUom = tempBaseUom;
	}
	public void setTemp(double temp) {
		this.temp = temp;
	}
	public void setTempUom(String tempUom) {
		this.tempUom = tempUom;
	}
	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}
	public void setError(boolean isError) {
		this.isError = isError;
	}
	public void setErrormsg(String errormsg) {
		this.errormsg = errormsg;
	}
	public void updateTemp()
	{
		
		if(tempUom.equalsIgnoreCase("Celsius"))
			temperature=(tempBaseUnit-273.15) +" C";
		else if(tempUom.equalsIgnoreCase("Fahrenheit"))
			temperature=(((tempBaseUnit-273.15) *(9/5)) + 32)+" F";
		
	}
	
	@Override
	public String toString() {
		return "weatherResponse [city=" + city + ", tempBaseUnit=" + tempBaseUnit + ", tempBaseUom=" + tempBaseUom
				+ ", temp=" + temp + ", tempUom=" + tempUom + ", temperature=" + temperature + ", isError=" + isError
				+ ", errormsg=" + errormsg + "]";
	}




}