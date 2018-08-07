package com.hsbc.weatherservice;

import javax.ws.rs.client.Client;

import com.hsbc.weatherservice.exception.ServiceExceptionMapper;
import com.hsbc.weatherservice.resources.WeatherResource;
import com.hsbc.weatherservice.services.WeatherService;
import com.hsbc.weatherservice.services.openweathermap.FiveDaysWeatherForecastService;
import com.hsbc.weatherservice.services.openweathermap.FiveDaysWeatherForecastServiceCacheImpl;
import com.hsbc.weatherservice.services.openweathermap.FiveDaysWeatherForecastServiceImpl;
import com.hsbc.weatherservice.services.openweathermap.OpenWeatherMapService;

import io.dropwizard.Application;
import io.dropwizard.client.JerseyClientBuilder;
import io.dropwizard.client.JerseyClientConfiguration;
import io.dropwizard.setup.Environment;

public class WeatherApplication extends Application<WeatherConfiguration> {

    public static void main(String[] args) throws Exception {
        new WeatherApplication().run(args);
    }
    
    @Override
    public void run(WeatherConfiguration configuration, Environment environment) throws Exception {
        
    	// Register the exception mapper
    	environment.jersey().register(new ServiceExceptionMapper());
    	
    	// Create the jersey client
        JerseyClientConfiguration jerseyClient = new JerseyClientConfiguration();
        final Client client = new JerseyClientBuilder(environment).using(jerseyClient).build(getName());
		
        // Create the external API service
        FiveDaysWeatherForecastService fiveDaysWeatherForecastService = 
        		new FiveDaysWeatherForecastServiceCacheImpl(
        			new FiveDaysWeatherForecastServiceImpl(client, configuration), 
        			configuration.getCacheSize(), configuration.getCacheExpiry());
        
        // Create the weather service
        WeatherService weatherService = new OpenWeatherMapService(fiveDaysWeatherForecastService);
        
        // Register the resource with the endpoints definition
        environment.jersey().register(new WeatherResource(weatherService)); 
           
    }
}
