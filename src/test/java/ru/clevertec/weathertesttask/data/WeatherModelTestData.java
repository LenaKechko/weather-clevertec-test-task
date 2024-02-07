package ru.clevertec.weathertesttask.data;

import lombok.Builder;
import lombok.Getter;
import ru.clevertec.weathertesttask.entity.model.WeatherModel;

@Getter
@Builder(setterPrefix = "with", toBuilder = true)
public class WeatherModelTestData {

    @Builder.Default
    private Double temperature = 4.0;
    @Builder.Default
    private Double feelsTemperature = 0.0;
    @Builder.Default
    private String condition = "cloudy";
    @Builder.Default
    private Double windSpeed = 2.6;
    @Builder.Default
    private Double windGust = 4.3;
    @Builder.Default
    private String windDir = "ne";
    @Builder.Default
    private Integer pressureInMm = 567;

    public WeatherModel buildWeatherModel() {
        return new WeatherModel(temperature, feelsTemperature, condition, windSpeed, windGust, windDir, pressureInMm);
    }

}
