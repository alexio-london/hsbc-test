package com.hsbc.weatherservice.transformer;

import static java.util.stream.Collectors.toList;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.Map.Entry;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import com.hsbc.weatherservice.model.openweathermap.response.WeatherElement;
import com.hsbc.weatherservice.model.response.DailyWeather;
import com.hsbc.weatherservice.model.response.DailyWeatherBuilder;
import com.hsbc.weatherservice.model.response.HourlyWeather;
import com.hsbc.weatherservice.model.response.HourlyWeatherBuilder;

/**
 * The class transforms the data beans coming from the external API 
 * into the json beans used for the endpoint response
 *
 */
public class WeatherTransformer {
	
	private static final double FROM_ms_TO_kmh_FACTOR = 3.6;
	private static final double FROM_ms_TO_mph_FACTOR = 2.2;
	private static final double FROM_CELSIUS_TO_FAHRENHEIT_FACTOR = 1.8;
	private static final double FAHRENHEIT_OFFSET = 32;
	private static final String NA = "NA";
	
	private static final DateTimeFormatter amPmHourFormatter = DateTimeFormatter.ofPattern("HHa");
	
    private static final Comparator<Entry<String, Long>> COMPARE_BY_VALUE = 
    		Comparator.comparing(Entry<String, Long>::getValue).reversed();

	public DailyWeather toDaily(List<WeatherElement> list, String day) {
		NumberFormat numberFormatter = new DecimalFormat("#0");
		
		// the minimum temperature for the day
		double tempMin = list.stream()
				.map(e -> e.getMain().getTempMin())
				.collect(Collectors.minBy(Comparator.naturalOrder()))
				.get().doubleValue();
		
		// the maximum temperature for the day
		double tempMax = list.stream()
				.map(e -> e.getMain().getTempMax())
				.collect(Collectors.maxBy(Comparator.naturalOrder()))
				.get().doubleValue();
		
		// the aggregate conditions description
		String conditions = list.stream()
				.map(e -> e.getWeather().get(0).getDescription())
				.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
				.entrySet().stream().sorted(COMPARE_BY_VALUE).limit(2)
				.map(e -> e.getKey())
				.collect(Collectors.joining(" or "));
		
		// the maximum wind speed for the day
		double windSpeed = list.stream()
				.map(e -> e.getWind().getSpeed())
				.collect(Collectors.maxBy(Comparator.comparingDouble(e -> e)))
				.get().doubleValue();

		// the average wind direction for the day
		double windDirection = AverageDirection.calculateAverageDirection(
				list.stream()
				.map(e -> e.getWind().getDeg())
				.collect(toList()));
		
		return DailyWeatherBuilder.aDailyWeatherBuilder()
				.withSeconds(list.get(0).getDt())
				.withDay(day)
				.withMin(handleExceptions(() -> formatTemperature(tempMin, numberFormatter)))
				.withMax(handleExceptions(() -> formatTemperature(tempMax, numberFormatter)))
				.withWind(handleExceptions(() -> formatWind(windSpeed, windDirection, numberFormatter)))
				.withConditions(handleExceptions(() -> conditions))
				.build();
	}


	public HourlyWeather toHourly(WeatherElement weatherElement, String day) {
		NumberFormat numberFormatter = new DecimalFormat("#0");
		OffsetDateTime dateTime = Instant.ofEpochSecond(weatherElement.getDt()).atOffset(ZoneOffset.UTC);
		return HourlyWeatherBuilder.aHourlyWeatherBuilder()
					.withDay(day)
					.withHour(handleExceptions(() -> dateTime.format(amPmHourFormatter)))
					.withMin(handleExceptions(() -> formatTemperature(weatherElement.getMain().getTempMin(), numberFormatter)))
					.withMax(handleExceptions(() -> formatTemperature(weatherElement.getMain().getTempMax(), numberFormatter)))
					.withWind(handleExceptions(() -> formatWind(weatherElement.getWind().getSpeed(), weatherElement.getWind().getDeg(), numberFormatter)))
					.withConditions(handleExceptions(() -> weatherElement.getWeather().get(0).getDescription()))
					.build();
	}

	private String formatWind(Double windSpeed, Double windDeg, NumberFormat numberFormatter) {
		return String.format("%skmh/%smph %s", 
			numberFormatter.format(windSpeed * FROM_ms_TO_kmh_FACTOR),
			numberFormatter.format(windSpeed * FROM_ms_TO_mph_FACTOR),
			CompassDirection.from(windDeg));
	}
	
	private String formatTemperature(Double temperature, NumberFormat numberFormatter) {
		return String.format("%s°C/%s°F", 
			numberFormatter.format(temperature),
			numberFormatter.format(temperature * FROM_CELSIUS_TO_FAHRENHEIT_FACTOR + FAHRENHEIT_OFFSET));
	}
	
	private String handleExceptions(Supplier<String> r) {
	    try {
	        return r.get();
	    }
	    catch(Exception ex) {
	        return NA;
	    }
	}
}
