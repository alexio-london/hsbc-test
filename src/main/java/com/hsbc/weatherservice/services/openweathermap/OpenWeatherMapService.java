package com.hsbc.weatherservice.services.openweathermap;

import static java.util.stream.Collectors.toList;
import static javax.ws.rs.core.Response.Status.OK;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.TextStyle;
import java.util.Comparator;
import java.util.Locale;
import java.util.stream.Collectors;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.hsbc.weatherservice.exception.ServiceException;
import com.hsbc.weatherservice.model.openweathermap.response.WeatherElement;
import com.hsbc.weatherservice.model.openweathermap.response.WeatherResponse;
import com.hsbc.weatherservice.model.response.DailyWeatherResponse;
import com.hsbc.weatherservice.model.response.HourlyWeatherResponse;
import com.hsbc.weatherservice.services.WeatherService;
import com.hsbc.weatherservice.transformer.WeatherTransformer;

/**
 * 
 * Calls the FiveDaysWeatherForecastService 
 * and uses the WeatherTransformer to create the required response 
 *
 */
@Singleton
public class OpenWeatherMapService implements WeatherService {

    private FiveDaysWeatherForecastService fiveDaysWeatherForecastService;

    @Inject
    public OpenWeatherMapService(FiveDaysWeatherForecastService fiveDaysWeatherForecastService) {
        this.fiveDaysWeatherForecastService = fiveDaysWeatherForecastService;
    }

	@Override
	public DailyWeatherResponse buildDailyWeather(String city) {
		WeatherResponse weatherResponse = fiveDaysWeatherForecastService.getWeatherResponse(city);
		if(!weatherResponse.getCode().equals(OK.getStatusCode())) {
			throw new ServiceException(weatherResponse.getMessage());
		}
		return new DailyWeatherResponse(
			weatherResponse.getList().stream()
			.collect(Collectors.groupingBy(e -> getDayOfTheWeek(e))).entrySet().stream()
			.map(e -> new WeatherTransformer().toDaily(e.getValue(), e.getKey()))
			.sorted(Comparator.comparing(e -> e.getSeconds()))
			.collect(toList()));
	}

	@Override
	public HourlyWeatherResponse buildHourlyWeather(String city, String day) {
		WeatherResponse weatherResponse = fiveDaysWeatherForecastService.getWeatherResponse(city);
		if(!weatherResponse.getCode().equals(OK.getStatusCode())) {
			throw new ServiceException(weatherResponse.getMessage());
		}
		return new HourlyWeatherResponse(
			weatherResponse.getList().stream()
			.filter(element -> getDayOfTheWeek(element).equalsIgnoreCase(day))
			.map(r -> new WeatherTransformer().toHourly(r, day))
			.collect(toList()));
	}

	private String getDayOfTheWeek(WeatherElement weatherElement) {
		OffsetDateTime dateTime = Instant.ofEpochSecond(weatherElement.getDt()).atOffset(ZoneOffset.UTC);
		return dateTime.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.UK);
	}
}
