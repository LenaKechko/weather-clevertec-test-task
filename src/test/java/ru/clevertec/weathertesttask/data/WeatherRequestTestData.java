package ru.clevertec.weathertesttask.data;

import lombok.Builder;
import lombok.Getter;
import ru.clevertec.weathertesttask.model.WeatherRequest;

@Getter
@Builder(setterPrefix = "with", toBuilder = true)
public class WeatherRequestTestData {

    @Builder.Default
    private Double longitude = Constants.LONGITUDE;

    @Builder.Default
    private Double latitude = Constants.LATITUDE;

    @Builder.Default
    private Integer limit = Constants.LIMIT;

    public WeatherRequest buildWeatherRequest() {
        return new WeatherRequest(longitude, latitude, limit);
    }

}

