package com.hsbc.weatherservice.model.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ErrorReponse {
	
	protected String message;
	
	@JsonCreator
	public ErrorReponse(String message) {
		
		this.message = message;
	}

	@JsonProperty
	public String getMessage() {
		return message;
	}
}
