package com.hsbc.weatherservice.rest;

import java.util.Map;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

public class GetClient {
	
	/**
	 * The method calls a GET endpoint with parameters from the queryMap provided
	 * 
	 * @param client the client used to make the call
	 * @param endpoint the endpoint to call
	 * @param queryMap a map with the parameters to pass to the GET call
	 * @return the response
	 */
	public static Response callGetEndpoint(Client client, String endpoint, Map<String, String> queryMap) {
				
		UriBuilder uriBuilder = UriBuilder.fromUri(endpoint);
		queryMap.entrySet().forEach(e -> uriBuilder.queryParam(e.getKey(), e.getValue()));
		WebTarget webTarget = client.target(uriBuilder);
        Invocation.Builder invocationBuilder =  webTarget.request(MediaType.APPLICATION_JSON);
 
        Response response = invocationBuilder.get();
		return response;
	}
}
