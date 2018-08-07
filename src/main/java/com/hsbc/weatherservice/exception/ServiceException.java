package com.hsbc.weatherservice.exception;


public class ServiceException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	private final String message;

	public ServiceException(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

}
