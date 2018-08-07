
package com.hsbc.weatherservice.model.openweathermap.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherResponse extends OpenWeatherMapErrorResponse {

	@JsonProperty("list")
	private java.util.List<WeatherElement> list = null;

	public WeatherResponse() {
		super();
	}

	public WeatherResponse(List<WeatherElement> list) {
		super();
		this.list = list;
	}

	public java.util.List<WeatherElement> getList() {
		return list;
	}
	
}