package com.hsbc.weatherservice.model.openweathermap.request;

import java.util.HashMap;
import java.util.Map;

public class FiveDaysWeatherForecastRequest {

	private final Map<String, String> queryMap;
	
	public FiveDaysWeatherForecastRequest(String city, String appid) {
		
		queryMap = new HashMap<String, String>();
		queryMap.put("units", "metric");
		queryMap.put("type", "accurate");
		queryMap.put("mode", "json");
		queryMap.put("q", city);
		queryMap.put("appid", appid);
	}

	public Map<String, String> getQueryMap() {return queryMap;}
	
}
