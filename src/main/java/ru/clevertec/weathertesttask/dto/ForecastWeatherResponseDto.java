package ru.clevertec.weathertesttask.dto;

import ru.clevertec.weathertesttask.entity.YandexResponse;

import java.time.LocalDate;
import java.util.Map;

public record ForecastWeatherResponseDto(LocalDate date,
                                         Map<String, YandexResponse.DayWeatherModel> forecastOfDay) {
    public record ForecastWeatherModel(
            Double minTemperature,
            Double avgTemperature,
            Double maxTemperature,
            Double feelsTemperature,
            Double windSpeed,
            Double windGust,
            String windDir,
            Integer pressureInMm,
            String condition) {
    }
}

