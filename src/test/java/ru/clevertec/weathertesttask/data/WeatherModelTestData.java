package ru.clevertec.weathertesttask.data;

import lombok.Builder;
import lombok.Getter;
import ru.clevertec.weathertesttask.entity.model.WeatherModel;

@Getter
@Builder(setterPrefix = "with", toBuilder = true)
public class WeatherModelTestData {

    @Builder.Default
    private Double temperature = Constants.TEMPERATURE;
    @Builder.Default
    private Double feelsTemperature = Constants.FEELS_TEMPERATURE;
    @Builder.Default
    private String condition = Constants.CONDITION;
    @Builder.Default
    private Double windSpeed = Constants.WIND_SPEED;
    @Builder.Default
    private Double windGust = Constants.WIND_GUST;
    @Builder.Default
    private String windDir = Constants.WIND_DIR;
    @Builder.Default
    private Integer pressureInMm = Constants.PRESSURE_IN_MM;

    public WeatherModel buildWeatherModel() {
        return new WeatherModel(temperature, feelsTemperature, condition, windSpeed, windGust, windDir, pressureInMm);
    }

}
