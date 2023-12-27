package ru.clevertec.weathertesttask.entity.model;

import lombok.Builder;

import java.time.LocalDateTime;

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
