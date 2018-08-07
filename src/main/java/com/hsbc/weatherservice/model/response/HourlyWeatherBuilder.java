package com.hsbc.weatherservice.model.response;

public class HourlyWeatherBuilder {

	private long seconds;
	private String hour;
	private String day;
	private String min;
	private String max;
	private String conditions;
	private String wind;
	
	public static HourlyWeatherBuilder aHourlyWeatherBuilder() {
		return new HourlyWeatherBuilder();
	}
	
	public HourlyWeatherBuilder withSeconds(long seconds) {
		this.seconds = seconds;
		return this;
	}
	
	public HourlyWeatherBuilder withHour(String hour) {
		this.hour = hour;
		return this;
	}
	
	public HourlyWeatherBuilder withDay(String day) {
		this.day = day;
		return this;
	}
	
	public HourlyWeatherBuilder withMin(String min) {
		this.min = min;
		return this;
	}
	
	public HourlyWeatherBuilder withMax(String max) {
		this.max = max;
		return this;
	}
	
	public HourlyWeatherBuilder withConditions(String conditions) {
		this.conditions = conditions;
		return this;
	}
	
	public HourlyWeatherBuilder withWind(String wind) {
		this.wind = wind;
		return this;
	}
	
	public HourlyWeather build() {
		return new HourlyWeather(seconds, hour, day, min, max, conditions, wind);
	}
}
