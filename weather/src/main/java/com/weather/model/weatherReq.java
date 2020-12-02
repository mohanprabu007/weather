package com.weather.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class weatherReq {
    @NotBlank
    @Size(min=3, max = 60)
    private String city;

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