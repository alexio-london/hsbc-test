
package com.hsbc.weatherservice.model.openweathermap.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Wind {

	@JsonProperty("speed")
	private Double speed;

	@JsonProperty("deg")
	private Double deg;

	public Wind(){}

	public Wind(Double speed, Double deg) {
		this.speed = speed;
		this.deg = deg;
	}

	public Double getSpeed() {
		return speed;
	}

	public Double getDeg() {
		return deg;
	}
}
