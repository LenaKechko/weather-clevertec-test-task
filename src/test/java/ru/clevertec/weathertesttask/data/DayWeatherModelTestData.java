package ru.clevertec.weathertesttask.data;

import lombok.Builder;
import ru.clevertec.weathertesttask.entity.model.DayWeatherModel;

@Builder(setterPrefix = "with", toBuilder = true)
public class DayWeatherModelTestData {

    @Builder.Default
    private Double minTemperature = Constants.MIN_TEMPERATURE;
    @Builder.Default
    Double avgTemperature = Constants.AVG_TEMPERATURE;
    @Builder.Default
    Double maxTemperature = Constants.MAX_TEMPERATURE;
    @Builder.Default
    Double feelsTemperature = Constants.FEELS_TEMPERATURE;
    @Builder.Default
    Double windSpeed = Constants.WIND_SPEED;
    @Builder.Default
    Double windGust = Constants.WIND_GUST;
    @Builder.Default
    String windDir = Constants.WIND_DIR;
    @Builder.Default
    Integer pressureInMm = Constants.PRESSURE_IN_MM;
    @Builder.Default
    String condition = Constants.CONDITION;

    public DayWeatherModel buildDayWeatherModel() {
        return new DayWeatherModel(minTemperature,
                avgTemperature,
                maxTemperature,
                feelsTemperature,
                windSpeed,
                windGust,
                windDir,
                pressureInMm,
                condition);
    }
}
