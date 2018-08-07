package com.hsbc.weatherservice.model.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class HourlyWeatherResponse {

	protected List<HourlyWeather> hourlyWeather;
	
	@JsonCreator
	public HourlyWeatherResponse(List<HourlyWeather> hourlyWeather) {
		
		this.hourlyWeather = hourlyWeather;
	}

	@JsonProperty
	public List<HourlyWeather> getHourlyWeather() {
		return hourlyWeather;
	}
}
