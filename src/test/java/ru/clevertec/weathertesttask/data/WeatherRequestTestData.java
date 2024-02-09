package ru.clevertec.weathertesttask.data;

import lombok.Builder;
import lombok.Getter;
import ru.clevertec.weathertesttask.model.WeatherRequest;

@Getter
@Builder(setterPrefix = "with", toBuilder = true)
public class WeatherRequestTestData {

    @Builder.Default
    private Double longitude = 52.4345;

    @Builder.Default
    private Double latitude = 30.9754;

    @Builder.Default
    private Integer limit = 1;

    public WeatherRequest buildWeatherRequest() {
        return new WeatherRequest(longitude, latitude, limit);
    }

}

