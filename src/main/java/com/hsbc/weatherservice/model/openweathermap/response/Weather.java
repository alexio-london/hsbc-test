
package com.hsbc.weatherservice.model.openweathermap.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Weather {

	@JsonProperty("description")
	private String description;

	public Weather() {}

	public Weather(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

}