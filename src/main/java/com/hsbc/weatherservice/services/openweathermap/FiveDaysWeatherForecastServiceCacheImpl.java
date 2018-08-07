package com.hsbc.weatherservice.services.openweathermap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.inject.Singleton;
import com.hsbc.weatherservice.exception.ServiceException;
import com.hsbc.weatherservice.model.openweathermap.response.WeatherResponse;

/**
 * 
 * Implements the FiveDaysWeatherForecastService API with a cache,
 * the actual call is delegated to FiveDaysWeatherForecastServiceImpl
 *
 */
@Singleton
public class FiveDaysWeatherForecastServiceCacheImpl implements FiveDaysWeatherForecastService {

	private final LoadingCache<String, WeatherResponse> weatherResposeCache;

	public FiveDaysWeatherForecastServiceCacheImpl(final FiveDaysWeatherForecastServiceImpl delegate, int cacheSize, int cacheExpire) {
		weatherResposeCache = buildCache(delegate, cacheSize, cacheExpire);
	}

	private LoadingCache<String, WeatherResponse> buildCache(final FiveDaysWeatherForecastServiceImpl delegate,
			int cacheSize, int cacheExpire) {
		return CacheBuilder.newBuilder()
	       .maximumSize(cacheSize)
	       .expireAfterWrite(cacheExpire, TimeUnit.SECONDS)
	       .build(
    		   new CacheLoader<String, WeatherResponse>(){
    			   @Override
    			   public WeatherResponse load(String city) throws Exception {
    				   return delegate.getWeatherResponse(city);
    			   }
    		   }
	       );
	}

	@Override
	public WeatherResponse getWeatherResponse(String city) {
		
		try {
			return weatherResposeCache.get(city);
		} catch (ExecutionException e) {
			throw new ServiceException(e.getCause().getMessage());
		}
	}

}
