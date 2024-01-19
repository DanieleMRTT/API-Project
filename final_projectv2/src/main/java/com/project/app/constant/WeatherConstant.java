package com.project.app.constant;


import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public final class WeatherConstant {
   public static final Map<String, String> weatherConstantsConverter = initMap();

   private WeatherConstant() {

   }
   private static Map<String,String> initMap(){
      Map<String, String> weatherConstants = new HashMap<>();
      weatherConstants.put("0", "Clear sky");
      weatherConstants.put("1", "Mainly clear");
      weatherConstants.put("2", "Partly cloudy");
      weatherConstants.put("3", "Overcast");
      weatherConstants.put("45", "Fog");
      weatherConstants.put("48", "Depositing rime fog");
      weatherConstants.put("51", "Drizzle: Light");
      weatherConstants.put("53", "Drizzle: Moderate");
      weatherConstants.put("55", "Drizzle: Dense intensity");
      weatherConstants.put("56", "Freezing Drizzle: Light");
      weatherConstants.put("57", "Freezing Drizzle: Dense intensity");
      weatherConstants.put("61", "Rain: Slight");
      weatherConstants.put("63", "Rain: Moderate");
      weatherConstants.put("65", "Rain: Heavy intensity");
      weatherConstants.put("66", "Freezing Rain: Light");
      weatherConstants.put("67", "Freezing Rain: Heavy intensity");
      weatherConstants.put("71", "Snow fall: Slight");
      weatherConstants.put("73", "Snow fall: Moderate");
      weatherConstants.put("75", "Snow fall: Heavy intensity");
      weatherConstants.put("77", "Snow grains");
      weatherConstants.put("80", "Rain showers: Slight");
      weatherConstants.put("81", "Rain showers: Moderate");
      weatherConstants.put("82", "Rain showers: Violent");
      weatherConstants.put("85", "Snow showers: Slight");
      weatherConstants.put("86", "Snow showers: Heavy");
      weatherConstants.put("95", "Thunderstorm: Slight or moderate");
      weatherConstants.put("96", "Thunderstorm with hail: Slight");
      weatherConstants.put("99", "Thunderstorm with hail: Heavy");
      return Collections.unmodifiableMap(weatherConstants);
   }

   public static String convertWeatherCode(String weatherCode){
      return weatherConstantsConverter.get(weatherCode);
   }






}
