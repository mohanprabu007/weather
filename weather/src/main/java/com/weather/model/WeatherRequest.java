package com.weather.model;



import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Pattern.Flag;
import javax.validation.constraints.Size;

public class WeatherRequest {
    @NotBlank
    @Size(min=3, max = 60)
    private String city;

    @Pattern(regexp = "|Celsius|Fahrenheit", flags =Flag.CASE_INSENSITIVE)
    private String uom="Celsius";
    
	public String getUom() {
		return uom;
	}

	public void setUom(String uom) {
		this.uom = uom;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

   



}