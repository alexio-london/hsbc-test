package com.hsbc.weatherservice.services;

import com.hsbc.weatherservice.model.response.DailyWeatherResponse;
import com.hsbc.weatherservice.model.response.HourlyWeatherResponse;

public interface WeatherService {

	DailyWeatherResponse buildDailyWeather(String city);

	HourlyWeatherResponse buildHourlyWeather(String city, String day);

}