package com.hsbc.weatherservice.model.response;

public class DailyWeatherBuilder {

	private long seconds;
	private String day;
	private String min;
	private String max;
	private String conditions;
	private String wind;
	
	public static DailyWeatherBuilder aDailyWeatherBuilder() {
		return new DailyWeatherBuilder();
	}
	
	public DailyWeatherBuilder withSeconds(long seconds) {
		this.seconds = seconds;
		return this;
	}
	
	public DailyWeatherBuilder withDay(String day) {
		this.day = day;
		return this;
	}
	
	public DailyWeatherBuilder withMin(String min) {
		this.min = min;
		return this;
	}
	
	public DailyWeatherBuilder withMax(String max) {
		this.max = max;
		return this;
	}
	
	public DailyWeatherBuilder withConditions(String conditions) {
		this.conditions = conditions;
		return this;
	}
	
	public DailyWeatherBuilder withWind(String wind) {
		this.wind = wind;
		return this;
	}
	
	public DailyWeather build() {
		return new DailyWeather(seconds, day, min, max, conditions, wind);
	}

}
