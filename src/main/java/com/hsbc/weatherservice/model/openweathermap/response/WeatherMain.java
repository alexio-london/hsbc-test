
package com.hsbc.weatherservice.model.openweathermap.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherMain {

	@JsonProperty("temp_min")
	private Double tempMin;
	
	@JsonProperty("temp_max")
	private Double tempMax;

	public WeatherMain(Double tempMin, Double tempMax) {
		this.tempMin = tempMin;
		this.tempMax = tempMax;
	}

	public WeatherMain() {}

	public Double getTempMin() {
		return tempMin;
	}

	public Double getTempMax() {
		return tempMax;
	}

}