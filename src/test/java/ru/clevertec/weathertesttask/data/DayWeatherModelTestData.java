package ru.clevertec.weathertesttask.data;

import lombok.Builder;
import ru.clevertec.weathertesttask.entity.model.DayWeatherModel;

@Builder(setterPrefix = "with", toBuilder = true)
public class DayWeatherModelTestData {

    @Builder.Default
    private Double minTemperature = -1.0;
    @Builder.Default
    Double avgTemperature = 0.0;
    @Builder.Default
    Double maxTemperature = 1.0;
    @Builder.Default
    Double feelsTemperature = 0.0;
    @Builder.Default
    Double windSpeed = 5.1;
    @Builder.Default
    Double windGust = 3.0;
    @Builder.Default
    String windDir = "Some direction";
    @Builder.Default
    Integer pressureInMm = 733;
    @Builder.Default
    String condition = "Some condition";

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
