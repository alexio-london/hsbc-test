package com.hsbc.weatherservice.services.openweathermap;

import com.hsbc.weatherservice.model.openweathermap.response.WeatherResponse;

public interface FiveDaysWeatherForecastService {

	WeatherResponse getWeatherResponse(String city);

}