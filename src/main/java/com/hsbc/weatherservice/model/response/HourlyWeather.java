package com.hsbc.weatherservice.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class HourlyWeather extends DailyWeather {

	protected String hour;
	
	public HourlyWeather(
			final long seconds,
			@JsonProperty("hour") final String hour, 
			@JsonProperty("day") final String day, 
			@JsonProperty("min") final String min, 
			@JsonProperty("max") final String max, 
			@JsonProperty("conditions") final String conditions, 
			@JsonProperty("wind") final String wind) {
		super(seconds, day, min, max, conditions, wind);
		this.hour = hour;
	}

	@JsonProperty("hour")
	public String getHour() {return hour;}

}
