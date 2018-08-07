package com.hsbc.weatherservice.services.openweathermap;
import java.util.Map;

import javax.ws.rs.client.Client;
import javax.ws.rs.core.Response;

import com.google.inject.Singleton;
import com.hsbc.weatherservice.WeatherConfiguration;
import com.hsbc.weatherservice.model.openweathermap.request.FiveDaysWeatherForecastRequest;
import com.hsbc.weatherservice.model.openweathermap.response.WeatherResponse;
import com.hsbc.weatherservice.rest.GetClient;

/**
 * 
 * Implements the FiveDaysWeatherForecastService API using the client provided.
 *
 */
@Singleton
public class FiveDaysWeatherForecastServiceImpl implements FiveDaysWeatherForecastService {

	private WeatherConfiguration configuration;
	private Client client;
	
	public FiveDaysWeatherForecastServiceImpl(Client client, WeatherConfiguration configuration) {
		this.configuration = configuration;
		this.client = client;
	}
	
	@Override
	public WeatherResponse getWeatherResponse(String city) {

		Map<String, String> queryMap = new FiveDaysWeatherForecastRequest(city, configuration.getOpenWeatherMapKey()).getQueryMap();
		Response response = GetClient.callGetEndpoint(client, configuration.getOpenWeatherMapEndPoint(), queryMap);
        WeatherResponse weatherResponse = response.readEntity(WeatherResponse.class);
        
        return weatherResponse;
	}
}
