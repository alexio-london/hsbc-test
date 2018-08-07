package com.hsbc.weatherservice.model.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class DailyWeatherResponse {

	protected List<DailyWeather> dailyWeather;
	
	@JsonCreator
	public DailyWeatherResponse(List<DailyWeather> dailyWeather) {
		
		this.dailyWeather = dailyWeather;
	}

	@JsonProperty
	public List<DailyWeather> getDailyWeather() {
		return dailyWeather;
	}
}
