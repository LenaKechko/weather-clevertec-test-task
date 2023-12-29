package ru.clevertec.weathertesttask.entity.model;

import lombok.Builder;

@Builder
public record WeatherModel(
        Double temperature,
        Double feelsTemperature,
        String condition,
        Double windSpeed,
        Double windGust,
        String windDir,
        Integer pressureInMm) {
}
