
package com.hsbc.weatherservice.model.openweathermap.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherElement {

	@JsonProperty("dt")
	private Long dt;
	
	@JsonProperty("main")
	private WeatherMain main;
	
	@JsonProperty("weather")
	private java.util.List<Weather> weather = null;
	
	@JsonProperty("wind")
	private Wind wind;
	
	public WeatherElement() {}	
	
	public WeatherElement(Long dt, WeatherMain main, List<Weather> weather, Wind wind) {
		this.dt = dt;
		this.main = main;
		this.weather = weather;
		this.wind = wind;
	}

	public Long getDt() {
		return dt;
	}

	public WeatherMain getMain() {
		return main;
	}

	public java.util.List<Weather> getWeather() {
		return weather;
	}

	public Wind getWind() {
		return wind;
	}

}