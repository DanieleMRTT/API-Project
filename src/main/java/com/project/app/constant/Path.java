package com.project.app.constant;

public final class Path {

    public static final String CITY = "/v1.0/cities";
    public static final String GEO_COORDINATE = "/v1.0/coordinates";

    public static final String CITY_WITH_INHABITANTS_GREATER_THAN = "/v1.0/city-inhabitants";
    public static final String CITY_WITH_INHABITANTS_GREATER_THAN_IN_REGION = "/v1.0/city-inhabitants-region";
    public static final String CITY_WITH_INHABITANTS_GREATER_THAN_IN_PROVINCE = "/v1.0/city-inhabitants-province";

    public static final String WEATHER_FORECAST = "/v1.0/weather-forecast";
    public static final String DAILY_AVERAGE_TEMPERATURE_CITY = "/v1.0/daily-average-temperature";

    public static final String PROVINCE_AVERAGE_TEMPERATURE = "/v1.0/province-average-temperature";

    public static final String API_FORECAST = "https://api.open-meteo.com/v1/forecast";

    private Path() {
    }
}
