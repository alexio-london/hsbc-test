# HSBC OpenWeatherMap Tech Test

This application calls a weather API, filters and aggregate the data, 
then returns a json to show the weather forecast for a specific location/day.

## The API key

The API key to access the external API is stored in the configuration file ``/config/configuration.yml``


## Initial assumptions

The project is entirely backend, it has two endpoints implemented with dropwizard framework.

* ``GET /hsbc/weather/daily``
  returns the daily data for the next 5 days

* ``GET /hsbc/weather/3hourly``
  returns the 3-hourly data for a selected day

An assumption has been made about the city name, the user input is such that the city is uniquely retrieved.

Once the data is retrieved for a city then it is stored in a cache and used to handle the subsequent calls.

The following assumptions have been made about the expected behaviour of the daily endpoint.
The weather data is aggregate from the hourly data following the rules:
* the weather conditions description is the concatenation of the 2 most frequent conditions
* the wind speed is the maximum of the wind speed
* the wind direction is the average of the wind directions


## Pre-requisites

To compile and use the application, a Java8 compatible JDK and Gradle must be present.


## Usage


### Run the tests

``gradle test``

Will run the tests.


### Build the application

``gradle build``

Will create a jar file containing the application code. This will be generated in the ``target\distributions`` directory.


### Execute

``gradle run``

Will start the application server listening on the port 8080.


### Example

call the endpoints:

``http://localhost:8080/hsbc/weather/3hourly?city=London&day=Friday``

``http://localhost:8080/hsbc/weather/daily?city=London``

