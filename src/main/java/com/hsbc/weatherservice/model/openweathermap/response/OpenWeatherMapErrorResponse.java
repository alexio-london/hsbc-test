package com.hsbc.weatherservice.model.openweathermap.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OpenWeatherMapErrorResponse {

	@JsonProperty("cod")
	private Integer code;

	@JsonProperty("message")
	private String message;

	public OpenWeatherMapErrorResponse() {}

	public OpenWeatherMapErrorResponse(Integer code, String message) {
		this.code = code;
		this.message = message;
	}

	public Integer getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}
}
