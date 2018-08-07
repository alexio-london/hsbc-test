package com.hsbc.weatherservice.model.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class DailyWeather {

	protected long seconds;
	protected String day;
	protected String min;
	protected String max;
	protected String conditions;
	protected String wind;
	
	public DailyWeather(
			final long seconds,
			@JsonProperty("day") final String day, 
			@JsonProperty("min") final String min, 
			@JsonProperty("max") final String max, 
			@JsonProperty("conditions") final String conditions, 
			@JsonProperty("wind") final String wind) {
		this.seconds  = seconds;
		this.day = day;
		this.min = min;
		this.max = max;
		this.conditions = conditions;
		this.wind = wind;
	}
	
	@JsonIgnore
	public long getSeconds() {
		return seconds;
	}

	@JsonProperty("day")
	public String getDay() {return day;}

	@JsonProperty("min")
	public String getMin() {return min;}

	@JsonProperty("max")
	public String getMax() {return max;}

	@JsonProperty("conditions")
	public String getConditions() {return conditions;}

	@JsonProperty("wind")
	public String getWind() {return wind;}
	
}
