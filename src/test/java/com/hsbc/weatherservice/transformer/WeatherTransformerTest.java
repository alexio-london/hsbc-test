package com.hsbc.weatherservice.transformer;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.hsbc.weatherservice.model.openweathermap.response.Weather;
import com.hsbc.weatherservice.model.openweathermap.response.WeatherElement;
import com.hsbc.weatherservice.model.openweathermap.response.WeatherMain;
import com.hsbc.weatherservice.model.openweathermap.response.Wind;
import com.hsbc.weatherservice.model.response.DailyWeather;
import com.hsbc.weatherservice.model.response.HourlyWeather;

public class WeatherTransformerTest {
	

	WeatherTransformer weatherTransformer = new WeatherTransformer();
	
	@Test
	public void testHourlyTransform_AM_N() {

		//given
		WeatherElement weatherElement = new WeatherElement(
				1533081600l,
				new WeatherMain(12.0, 15.0),
				Arrays.asList(new Weather("clear sky")),
				new Wind(3.0, 10.0));
		String day = "Tuesday";
		
		//when
		HourlyWeather hourlyWeather = weatherTransformer.toHourly(weatherElement, day);
		
		//then
		assertThat(hourlyWeather.getDay(), equalTo("Tuesday"));
		assertThat(hourlyWeather.getHour(), equalTo("00AM"));
		assertThat(hourlyWeather.getMin(), equalTo("12°C/54°F"));
		assertThat(hourlyWeather.getMax(), equalTo("15°C/59°F"));
		assertThat(hourlyWeather.getConditions(), equalTo("clear sky"));
		assertThat(hourlyWeather.getWind(), equalTo("11kmh/7mph N"));	
	}	
	
	@Test
	public void testHourlyTransform_PM_S() {

		//given
		String day = "Tuesday";
		WeatherElement weatherElement = new WeatherElement(
				1533146400l,
				new WeatherMain(12.0, 15.0),
				Arrays.asList(new Weather("clear sky")),
				new Wind(6.0, 0.0));
		
		//when
		HourlyWeather hourlyWeather = weatherTransformer.toHourly(weatherElement, day);
		
		//then
		assertThat(hourlyWeather.getDay(), equalTo("Tuesday"));
		assertThat(hourlyWeather.getHour(), equalTo("18PM"));
		assertThat(hourlyWeather.getMin(), equalTo("12°C/54°F"));
		assertThat(hourlyWeather.getMax(), equalTo("15°C/59°F"));
		assertThat(hourlyWeather.getConditions(), equalTo("clear sky"));
		assertThat(hourlyWeather.getWind(), equalTo("22kmh/13mph N"));	
	}	
	
	@Test
	public void testDailyTransformFrom1record() {

		//given
		String day = "Tuesday";
		WeatherElement we1 = new WeatherElement(
				1533146400l,
				new WeatherMain(12.0, 15.0),
				Arrays.asList(new Weather("clear sky")),
				new Wind(3.0, 10.0));

		List<WeatherElement> weatherElementList = Arrays.asList(we1);

		//when
		DailyWeather dailyWeather = weatherTransformer.toDaily(weatherElementList, day);
		
		//then
		assertThat(dailyWeather.getDay(), equalTo("Tuesday"));
		assertThat(dailyWeather.getMin(), equalTo("12°C/54°F"));
		assertThat(dailyWeather.getMax(), equalTo("15°C/59°F"));
		assertThat(dailyWeather.getConditions(), equalTo("clear sky"));
		assertThat(dailyWeather.getWind(), equalTo("11kmh/7mph N"));	
	}	
	
	@Test
	public void testDailyTransformFrom3records() {

		//given
		String day = "Tuesday";
		WeatherElement we1 = new WeatherElement(
				1533146400l,
				new WeatherMain(12.0, 15.0),
				Arrays.asList(new Weather("clear sky")),
				new Wind(3.0, 10.0));
		WeatherElement we2 = new WeatherElement(
				1533103200l,
				new WeatherMain(16.0, 22.0),
				Arrays.asList(new Weather("clear sky")),
				new Wind(4.0, 50.0));
		WeatherElement we3 = new WeatherElement(
				1533081600l,
				new WeatherMain(21.0, 33.0),
				Arrays.asList(new Weather("rainy")),
				new Wind(5.0, 90.0));
		List<WeatherElement> weatherElementList = Arrays.asList(we1, we2, we3);

		//when
		DailyWeather dailyWeather = weatherTransformer.toDaily(weatherElementList, day);
		
		//then
		assertThat(dailyWeather.getDay(), equalTo("Tuesday"));
		assertThat(dailyWeather.getMin(), equalTo("12°C/54°F"));
		assertThat(dailyWeather.getMax(), equalTo("33°C/91°F"));
		assertThat(dailyWeather.getConditions(), equalTo("clear sky or rainy"));
		assertThat(dailyWeather.getWind(), equalTo("18kmh/11mph NE"));	
	}	
	
	@Test
	public void testDailyTransformFrom6records() {

		//given
		String day = "Tuesday";
		WeatherElement we1 = new WeatherElement(
				1533081600l,
				new WeatherMain(12.0, 13.0),
				Arrays.asList(new Weather("clear sky")),
				new Wind(3.0, 190.0));
		WeatherElement we2 = new WeatherElement(
				1533092400l,
				new WeatherMain(14.0, 15.0),
				Arrays.asList(new Weather("clear sky")),
				new Wind(4.0, 230.0));
		WeatherElement we3 = new WeatherElement(
				1533103200l,
				new WeatherMain(16.0, 18.0),
				Arrays.asList(new Weather("clear sky")),
				new Wind(5.0, 270.0));
		WeatherElement we4 = new WeatherElement(
				1533103200l,
				new WeatherMain(21.0, 33.0),
				Arrays.asList(new Weather("rainy")),
				new Wind(3.0, 190.0));
		WeatherElement we5 = new WeatherElement(
				1533124800l,
				new WeatherMain(16.0, 22.0),
				Arrays.asList(new Weather("rainy")),
				new Wind(4.0, 230.0));
		WeatherElement we6 = new WeatherElement(
				1533135600l,
				new WeatherMain(16.0, 18.0),
				Arrays.asList(new Weather("foggy")),
				new Wind(5.0, 270.0));
		List<WeatherElement> weatherElementList = Arrays.asList(we1, we2, we3, we4, we5, we6);

		//when
		DailyWeather dailyWeather = weatherTransformer.toDaily(weatherElementList, day);
		
		//then
		assertThat(dailyWeather.getDay(), equalTo("Tuesday"));
		assertThat(dailyWeather.getMin(), equalTo("12°C/54°F"));
		assertThat(dailyWeather.getMax(), equalTo("33°C/91°F"));
		assertThat(dailyWeather.getConditions(), equalTo("clear sky or rainy"));
		assertThat(dailyWeather.getWind(), equalTo("18kmh/11mph SW"));	
	}	
	

}
