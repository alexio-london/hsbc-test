package com.hsbc.weatherservice;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import io.dropwizard.Configuration;

public class WeatherConfiguration extends Configuration {

    @NotEmpty
    private String openWeatherMapEndPoint;

    @NotEmpty
    private String openWeatherMapKey;
    
    @NotNull
    private Integer cacheSize;
    
    @NotNull
    private Integer cacheExpiry;
    
	public String getOpenWeatherMapEndPoint() {
		return openWeatherMapEndPoint;
	}
	
	public String getOpenWeatherMapKey() {
		return openWeatherMapKey;
	}
	
	public int getCacheSize() {
		return cacheSize;
	}
	
	public int getCacheExpiry() {
		return cacheExpiry;
	}

}