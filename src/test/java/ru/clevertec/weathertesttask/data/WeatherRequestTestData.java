package ru.clevertec.weathertesttask.data;

import lombok.experimental.UtilityClass;
import ru.clevertec.weathertesttask.model.WeatherRequest;

@UtilityClass
public class WeatherRequestTestData {

    private final Double longitude = 52.4345;
    private final Double latitude = 30.9754;
    private final Integer limit = 1;

    public static WeatherRequest buildWeatherRequest() {
        return new WeatherRequest(longitude, latitude, limit);
    }

}

