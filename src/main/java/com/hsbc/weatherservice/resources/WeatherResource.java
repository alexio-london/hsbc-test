package com.hsbc.weatherservice.resources;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.Response.Status.OK;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import com.google.inject.Inject;
import com.hsbc.weatherservice.model.response.DailyWeatherResponse;
import com.hsbc.weatherservice.model.response.HourlyWeatherResponse;
import com.hsbc.weatherservice.services.WeatherService;

@Path("/hsbc/weather")
public class WeatherResource {

    private final WeatherService weatherService;
    
    @Inject
    public WeatherResource(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GET
    @Path("/daily")
    @Produces(APPLICATION_JSON)
    public Response getDailyWeather(@QueryParam(value = "city")  String city) throws Exception {
    	DailyWeatherResponse dailyWeather = weatherService.buildDailyWeather(city);
        return Response.status(OK).entity(dailyWeather).build();
    }
    
    @GET
    @Path("/3hourly")
    @Produces(APPLICATION_JSON)
    public Response getHourlyWeather(@QueryParam(value = "city")  String city,
    		                         @QueryParam(value = "day")  String day) throws Exception {
    	HourlyWeatherResponse hourlyWeather = weatherService.buildHourlyWeather(city, day);
        return Response.status(OK).entity(hourlyWeather).build();
    }

}
