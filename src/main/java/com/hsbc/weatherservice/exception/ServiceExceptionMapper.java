package com.hsbc.weatherservice.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

import com.hsbc.weatherservice.model.response.ErrorReponse;

import static javax.ws.rs.core.Response.Status.BAD_GATEWAY;

public class ServiceExceptionMapper implements ExceptionMapper<ServiceException> {

	@Override
	public Response toResponse(ServiceException exception) {
		return Response.status(BAD_GATEWAY).entity(new ErrorReponse(exception.getMessage())).build();
	}
}
