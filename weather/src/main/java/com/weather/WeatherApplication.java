package com.weather;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WeatherApplication {

	 private static final Logger LOGGER = LogManager.getLogger(WeatherApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(WeatherApplication.class, args);
		LOGGER.info("weather Application Started...");
	}

}
